import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/coupon/my_coupon_screen.dart';

class CouponIconButton extends StatelessWidget {
  const CouponIconButton({
    super.key,
  });

  void _moveToMyCouponScreen(BuildContext context) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const MyCouponScreen(),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Stack(
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
        GestureDetector(
          onTap: () => _moveToMyCouponScreen(context),
          child: Container(
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
          ),
        )
      ],
    );
  }
}
