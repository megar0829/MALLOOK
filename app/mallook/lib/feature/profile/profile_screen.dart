import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/config/global_functions.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/profile/widget/fashion_tile_widget.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({super.key});

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
        appBar: AppBar(
          backgroundColor: Colors.white,
          surfaceTintColor: Colors.white,
          shadowColor: Colors.grey.shade600,
          elevation: 1,
          titleTextStyle: const TextStyle(
            color: Colors.black,
            fontSize: Sizes.size24,
            fontWeight: FontWeight.bold,
          ),
          title: const Text("프로필"),
          actions: [
            Stack(
              children: [
                const Padding(
                  padding: EdgeInsets.only(
                    left: Sizes.size10,
                    top: Sizes.size4,
                  ),
                  child: FaIcon(
                    FontAwesomeIcons.ticket,
                    size: Sizes.size24,
                    color: Colors.black,
                  ),
                ),
                Container(
                  width: Sizes.size20,
                  height: Sizes.size20,
                  decoration: BoxDecoration(
                    color: Theme.of(context).primaryColorDark,
                    shape: BoxShape.circle,
                    border: Border.all(
                      color: Theme.of(context).primaryColor,
                      width: 0.5,
                    ),
                  ),
                  child: const Center(
                    child: Text(
                      '2',
                      style: TextStyle(
                        color: Colors.white,
                        // 테마의 primaryColor 사용
                        fontSize: Sizes.size12,
                        // Sizes.size14 대신 고정값을 사용하였습니다.
                        fontWeight: FontWeight.w800,
                      ),
                    ),
                  ),
                )
              ],
            ),
            Gaps.h8,
            CartIconButton(),
            Gaps.h20,
          ],
        ),
        body: Center(
          child: Column(
            children: [
              Image.asset("assets/images/mypage_sample_page.jpg"),
              const Divider(),
              const FashionTileWidget(),
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
