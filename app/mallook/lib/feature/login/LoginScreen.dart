import 'package:flutter/material.dart';
import 'package:mallook/feature/sign_up/sign_up_screen.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  void _onTap(BuildContext context) {
    print('kakao_login');

    Navigator.of(context).pushAndRemoveUntil(
      MaterialPageRoute(
        builder: (context) => const SignUpScreen(),
      ),
      (route) => false,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: GestureDetector(
        onTap: () => _onTap(context),
        child: Center(
          child: Image.asset("assets/images/kakao_login_large.png"),
        ),
      ),
    );
  }
}
