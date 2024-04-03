import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:kakao_flutter_sdk_user/kakao_flutter_sdk_user.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/login/api/login_api_servcie.dart';
import 'package:mallook/feature/login/models/auth_token_model.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';
import 'package:mallook/feature/sign_up/sign_up_screen.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  final storage = const FlutterSecureStorage();

  @override
  void initState() {
    super.initState();
    _checkTokenAndNavigate();
  }

  Future<void> _checkTokenAndNavigate() async {
    final storageToken = await storage.read(key: "token");
    if (storageToken == null) {
      return;
    }
    final token = AuthTokenModel.fromJson(jsonDecode(storageToken));
    if (token.accessToken != null) {
      Navigator.of(context).pushAndRemoveUntil(
          MaterialPageRoute(
            builder: (context) => const MainNavigationScreen(),
          ),
          (route) => false);
    }
  }

  void _onLoginSuccess(BuildContext context, AuthTokenModel token) {
    // TODO: 유저 정보 불러오기
    if (token.roles!.contains("BASIC_USER")) {
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
    await storage.write(key: 'token', value: jsonEncode(tokenModel.toJson()));
    return tokenModel;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.black,
      body: GestureDetector(
        onTap: () => _kakaoLogin().then(
          (value) => _onLoginSuccess(context, value),
        ),
        child: Center(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              Container(
                width: 180,
                padding: const EdgeInsets.all(
                  Sizes.size36,
                ),
                decoration: const BoxDecoration(
                  color: Colors.white,
                  shape: BoxShape.circle,
                ),
                child: Image.asset(
                  "assets/images/app_logo/logo_sm.png",
                ),
              ),
              Gaps.v10,
              const Text(
                'Mallook',
                style: TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                  fontSize: Sizes.size32,
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    '나를 위한 쇼핑몰',
                    style: TextStyle(
                      color: Colors.grey.shade100,
                      fontWeight: FontWeight.bold,
                      fontSize: Sizes.size16,
                    ),
                  ),
                  Gaps.h10,
                  FaIcon(
                    FontAwesomeIcons.store,
                    color: Colors.grey.shade100,
                  ),
                ],
              ),
              Gaps.v20,
              Image.asset(
                "assets/images/kakao_login_large.png",
                height: 70,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
