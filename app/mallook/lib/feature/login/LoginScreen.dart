import 'package:flutter/material.dart';

class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  void _onTap() {
    print('kakao_login');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: GestureDetector(
        onTap: () => _onTap(),
        child: Center(
          child: Image.asset("assets/images/kakao_login_large.png"),
        ),
      ),
    );
  }
}
