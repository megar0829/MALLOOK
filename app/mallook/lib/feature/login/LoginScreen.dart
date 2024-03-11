import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:kakao_flutter_sdk_user/kakao_flutter_sdk_user.dart';
import 'package:mallook/constants/member_role.dart';
import 'package:mallook/feature/login/api/login_api_servcie.dart';
import 'package:mallook/feature/login/models/auth_token_model.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';
import 'package:mallook/feature/sign_up/sign_up_screen.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});
  final storage = const FlutterSecureStorage();

  void _onLoginSuccess(BuildContext context, AuthTokenModel token) {
    if (token.roles!.contains(MemberRole.basicUser)) {
      _signUpUser(context);
      return;
    }
    _loginUser(context);
  }

  void _signUpUser(BuildContext context) {
    Navigator.of(context).pushAndRemoveUntil(
      PageRouteBuilder(
        pageBuilder: (context, animation, secondaryAnimation) =>
            const SignUpScreen(),
        transitionsBuilder: (context, animation, secondaryAnimation, child) {
          var begin = const Offset(1.0, 0.0);
          var end = Offset.zero;
          var curve = Curves.ease;

          var tween =
              Tween(begin: begin, end: end).chain(CurveTween(curve: curve));

          return SlideTransition(
            position: animation.drive(tween),
            child: child,
          );
        },
        transitionDuration: const Duration(milliseconds: 500),
      ),
      (route) => false,
    );
  }

  void _loginUser(BuildContext context) {
    Navigator.of(context).pushAndRemoveUntil(
      PageRouteBuilder(
        pageBuilder: (context, animation, secondaryAnimation) =>
            const MainNavigationScreen(),
        transitionsBuilder: (context, animation, secondaryAnimation, child) {
          var begin = const Offset(1.0, 0.0);
          var end = Offset.zero;
          var curve = Curves.ease;

          var tween =
              Tween(begin: begin, end: end).chain(CurveTween(curve: curve));

          return SlideTransition(
            position: animation.drive(tween),
            child: child,
          );
        },
        transitionDuration: const Duration(milliseconds: 500),
      ),
      (route) => false,
    );
  }

  Future<AuthTokenModel> _kakaoLogin() async {
    bool talkInstalled = await isKakaoTalkInstalled();
    // print("KAKAO SDK: ${await KakaoSdk.origin}");
    OAuthToken? token;
    if (talkInstalled) {
      try {
        token = await UserApi.instance.loginWithKakaoTalk();
      } catch (error) {
        if (error is PlatformException && error.code == 'CANCELED') {
          throw Error();
        }

        try {
          token = await UserApi.instance.loginWithKakaoAccount();
        } catch (error) {
          throw Error();
        }
      }
    } else {
      try {
        token = await UserApi.instance.loginWithKakaoAccount();
      } catch (error) {
        throw Error();
      }
    }
    AuthTokenModel tokenModel = await LoginApiService.getAuthToken(token);
    await storage.write(key: 'access-token', value: tokenModel.accessToken);
    await storage.write(key: 'refresh-token', value: tokenModel.refreshToken);
    return tokenModel;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: GestureDetector(
        onTap: () => _kakaoLogin().then(
          (value) => _onLoginSuccess(context, value),
        ),
        child: Center(
          child: Image.asset("assets/images/kakao_login_large.png"),
        ),
      ),
    );
  }
}
