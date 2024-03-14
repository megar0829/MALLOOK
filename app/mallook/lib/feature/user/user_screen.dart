import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/config/global_functions.dart';
import 'package:mallook/constants/sizes.dart';

class UserScreen extends StatelessWidget {
  const UserScreen({super.key});

  void _onLogoutBtnPressed() async {
    const storage = FlutterSecureStorage();
    await storage.deleteAll();
    moveToLoginScreen();
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: Colors.white,
        body: Center(
          child: Column(
            children: [
              Image.asset("assets/images/mypage_sample_page.jpg"),
              const Divider(),
              ListTile(
                trailing: const FaIcon(
                  FontAwesomeIcons.angleRight,
                  color: Colors.black,
                  size: Sizes.size24,
                ),
                contentPadding: const EdgeInsets.symmetric(
                  horizontal: Sizes.size28,
                ),
                onTap: () {},
                leading: const FaIcon(
                  FontAwesomeIcons.trophy,
                  color: Colors.black,
                  size: Sizes.size24,
                ),
                title: const Text(
                  "패션 월드컵",
                ),
                titleTextStyle: const TextStyle(
                  color: Colors.black,
                  fontWeight: FontWeight.bold,
                  fontSize: Sizes.size20,
                ),
              ),
              const Divider(),

              // GestureDetector(
              //   onTap: _onLogoutBtnPressed,
              //   child: const Text("my page"),
              // ),
            ],
          ),
        ),
      ),
    );
  }
}
