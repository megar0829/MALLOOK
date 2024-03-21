import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/profile/widget/fashion_tile_widget.dart';
import 'package:mallook/feature/profile/widget/my_profile_controller_widget.dart';
import 'package:mallook/feature/profile/widget/my_profile_widget.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';

class ProfileScreen extends StatelessWidget {
  final String username = '정우현';
  final String hashcode = "O12AB2";
  final String level = '3';
  final percentage = 82.3;

  const ProfileScreen({super.key});

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
            // 쿠폰 버튼
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
                        fontSize: Sizes.size12,
                        fontWeight: FontWeight.w800,
                      ),
                    ),
                  ),
                )
              ],
            ),
            Gaps.h8,
            const CartIconButton(),
            Gaps.h20,
          ],
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(
            vertical: Sizes.size12,
            horizontal: Sizes.size18,
          ),
          child: Column(
            children: [
              MyProfileWidget(
                username: username,
                hashcode: hashcode,
                level: level,
                percentage: percentage,
              ),
              Gaps.v12,
              const MyProfileControllerWidget(
                order: 7,
                deliver: 3,
                coupon: 12,
                point: 2633,
              ),
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
