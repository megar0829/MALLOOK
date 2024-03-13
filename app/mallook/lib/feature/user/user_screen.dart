import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:mallook/config/global_functions.dart';

class UserScreen extends StatelessWidget {
  const UserScreen({super.key});

  void _onLogoutBtnPressed() async {
    const storage = FlutterSecureStorage();
    await storage.deleteAll();
    moveToLoginScreen();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Center(
        child: GestureDetector(
          onTap: _onLogoutBtnPressed,
          child: const Text("my page"),
        ),
      ),
    );
  }
}
