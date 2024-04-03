import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/coupon/issue_coupon_screen.dart';
import 'package:mallook/feature/coupon/my_coupon_screen.dart';

class CouponIconButton extends StatelessWidget {
  const CouponIconButton({
    super.key,
    required this.couponCnt,
  });

  final int couponCnt;

  void _moveToMyCouponScreen(BuildContext context) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const IssueCouponScreen(),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => _moveToMyCouponScreen(context),
      child: Stack(
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
            child: Center(
              child: Text(
                '$couponCnt',
                style: const TextStyle(
                  color: Colors.white,
                  fontSize: Sizes.size12,
                  fontWeight: FontWeight.w800,
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}
