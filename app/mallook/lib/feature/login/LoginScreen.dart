import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:kakao_flutter_sdk_user/kakao_flutter_sdk_user.dart';
import 'package:mallook/feature/login/api/login_api_servcie.dart';
import 'package:mallook/feature/login/models/auth_token_model.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  void _kakaoLogin() async {
    bool talkInstalled = await isKakaoTalkInstalled();
    OAuthToken? token;
    if (talkInstalled) {
      try {
        token = await UserApi.instance.loginWithKakaoTalk();
        // token = await AuthCodeClient.instance.authorizeWithTalk(
        //     redirectUri: "http://localhost:8080/login/oauth2/code/kakao");
      } catch (error) {
        if (error is PlatformException && error.code == 'CANCELED') {
          return;
        }

        try {
          token = await UserApi.instance.loginWithKakaoAccount();
          // token = await AuthCodeClient.instance.authorize(
          //     redirectUri: "http://localhost:8080/login/oauth2/code/kakao");
          print('카카오 로그인 성공');
        } catch (error) {
          print('카카오 로그인 실패 $error');
          // print(await KakaoSdk.origin);
        }
      }
    } else {
      try {
        token = await UserApi.instance.loginWithKakaoAccount();
        // token = await AuthCodeClient.instance.authorize(
        //     redirectUri: "http://localhost:8080/login/oauth2/code/kakao");
      } catch (error) {
        print('카카오 로그인 실패 $error');
        // print(await KakaoSdk.origin);
      }
    }

    print("=================================================================");
    print("=================================================================");
    print(token);
    print("=================================================================");
    print("=================================================================");
    if (token == null) {
      throw Error();
    }
    AuthTokenModel tokenModel = await LoginApiService.getAuthToken(token);
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.setString("access-token", tokenModel.accessToken);
    prefs.setString("refresh-token", tokenModel.refreshToken);

    print(tokenModel);
    print("=================================================================");
    print("=================================================================");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: GestureDetector(
        onTap: () => _kakaoLogin(),
        child: Center(
          child: Image.asset("assets/images/kakao_login_large.png"),
        ),
      ),
    );
  }
}
