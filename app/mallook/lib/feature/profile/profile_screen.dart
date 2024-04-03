import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:mallook/config/global_functions.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/profile/model/member_detail.dart';
import 'package:mallook/feature/profile/widget/coupon_icon_button.dart';
import 'package:mallook/feature/profile/widget/fashion_tile_widget.dart';
import 'package:mallook/feature/profile/widget/my_profile_controller_widget.dart';
import 'package:mallook/feature/profile/widget/my_profile_widget.dart';
import 'package:mallook/feature/profile/widget/my_script_tile_widget.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';

class ProfileScreen extends StatelessWidget {
  final Future<MemberDetail> member;

  const ProfileScreen({super.key, required this.member});

  void _onLogoutBtnPressed(BuildContext context) async {
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
          automaticallyImplyLeading: false,
          centerTitle: true,
          backgroundColor: Colors.white,
          surfaceTintColor: Colors.white,
          shadowColor: Colors.grey.shade600,
          elevation: 1,
          titleTextStyle: const TextStyle(
            color: Colors.black,
            fontSize: Sizes.size18,
            fontWeight: FontWeight.bold,
          ),
          leading: Padding(
            padding: const EdgeInsets.only(
              left: Sizes.size20,
            ),
            child: Image.asset(
              "assets/images/app_logo/logo_sm.png",
            ),
          ),
          title: const Text(
            "프로필",
            style: TextStyle(
              color: Colors.black,
              fontSize: Sizes.size20,
            ),
          ),
          actions: [
            // 쿠폰 버튼
            FutureBuilder(
              future: member,
              builder:
                  (BuildContext context, AsyncSnapshot<MemberDetail> snapshot) {
                if (snapshot.hasData) {
                  return CouponIconButton(
                    couponCnt: snapshot.data!.coupon!,
                  );
                }
                return const CouponIconButton(couponCnt: 0);
              },
            ),
            Gaps.h8,
            const CartIconButton(),
            Gaps.h24,
          ],
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(
            vertical: Sizes.size12,
            horizontal: Sizes.size18,
          ),
          child: Center(
            child: Column(
              children: [
                Gaps.v20,
                FutureBuilder(
                  future: member,
                  builder: (context, snapshot) {
                    if (snapshot.hasData) {
                      return MyProfileWidget(
                        nickname: snapshot.data!.nickname!,
                        nicknameTag: snapshot.data!.nicknameTag!,
                        level: 1,
                        exp: snapshot.data!.exp!,
                        expRange: snapshot.data!.expRange!,
                      );
                    }
                    return const SizedBox.shrink();
                  },
                ),
                Gaps.v12,
                FutureBuilder(
                  future: member,
                  builder: (BuildContext context,
                      AsyncSnapshot<MemberDetail> snapshot) {
                    if (snapshot.hasData) {
                      return MyProfileControllerWidget(
                        orders: snapshot.data!.orders!,
                        coupon: snapshot.data!.orders!,
                        point: snapshot.data!.point!,
                      );
                    }
                    return const SizedBox.shrink();
                  },
                ),
                Gaps.v20,
                const FashionTileWidget(),
                const Divider(),
                const MyScriptTileWidget(),
                const Divider(),
                TextButton(
                  onPressed: () => _onLogoutBtnPressed(context),
                  child: Text(
                    '로그아웃',
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                      fontSize: Sizes.size14,
                      color: Colors.grey.shade500,
                    ),
                  ),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
