import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/coupon/issue_coupon_screen.dart';
import 'package:mallook/feature/coupon/my_coupon_screen.dart';
import 'package:mallook/feature/order/ordered_list_screen.dart';
import 'package:mallook/feature/profile/widget/profile_selector_widget.dart';

class MyProfileControllerWidget extends StatelessWidget {
  final int orders;
  final int coupon;
  final int point;

  const MyProfileControllerWidget({
    super.key,
    required this.orders,
    required this.coupon,
    required this.point,
  });

  void _moveToIssueCouponScreen(BuildContext context) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const MyCouponScreen(),
      ),
    );
  }

  void _moveToOrderedListScreen(BuildContext context) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const OrderedListScreen(),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 140,
      clipBehavior: Clip.hardEdge,
      padding: const EdgeInsets.symmetric(
        vertical: Sizes.size12,
        horizontal: Sizes.size12,
      ),
      decoration: BoxDecoration(
        border: Border.all(
          color: Colors.grey.shade300,
          width: Sizes.size2,
        ),
        borderRadius: BorderRadius.circular(
          Sizes.size20,
        ),
      ),
      child: GridView(
        physics: const NeverScrollableScrollPhysics(), // 스크롤 금지
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 3,
        ),
        children: [
          ProfileSelectorWidget(
            icon: FontAwesomeIcons.boxArchive,
            title: "주문",
            count: orders,
            onTap: () => _moveToOrderedListScreen(context),
          ),
          ProfileSelectorWidget(
            icon: FontAwesomeIcons.ticket,
            title: "쿠폰",
            count: coupon,
            onTap: () => _moveToIssueCouponScreen(context),
          ),
          ProfileSelectorWidget(
            icon: FontAwesomeIcons.coins,
            title: "포인트",
            count: point,
            onTap: () {},
          ),
        ],
      ),
    );
  }
}
