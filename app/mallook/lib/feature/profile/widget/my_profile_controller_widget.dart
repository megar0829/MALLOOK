import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/profile/widget/profile_selector_widget.dart';

class MyProfileControllerWidget extends StatelessWidget {
  final int order;
  final int deliver;
  final int coupon;
  final int point;

  const MyProfileControllerWidget({
    super.key,
    required this.order,
    required this.deliver,
    required this.coupon,
    required this.point,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 120,
      padding: const EdgeInsets.symmetric(
        vertical: Sizes.size16,
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
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 4,
        ),
        children: [
          ProfileSelectorWidget(
            icon: FontAwesomeIcons.boxArchive,
            title: "주문",
            count: order,
            onTap: () {},
          ),
          ProfileSelectorWidget(
            icon: FontAwesomeIcons.truckFast,
            title: "배송",
            count: deliver,
            onTap: () {},
          ),
          ProfileSelectorWidget(
            icon: FontAwesomeIcons.ticket,
            title: "쿠폰",
            count: coupon,
            onTap: () {},
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
