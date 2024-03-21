import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';

class ProfileSelectorWidget extends StatelessWidget {
  final IconData icon;
  final String title;
  final int count;
  final Function onTap;

  const ProfileSelectorWidget({
    super.key,
    required this.icon,
    required this.title,
    required this.count,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => onTap,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          FaIcon(
            icon,
            color: Theme.of(context).primaryColorDark,
            size: Sizes.size28,
          ),
          Gaps.v4,
          Text(
            title,
            style: const TextStyle(
              color: Colors.black,
              fontSize: Sizes.size14,
              fontWeight: FontWeight.bold,
            ),
          ),
          Gaps.v4,
          Text(
            '$count',
            style: TextStyle(
              color: Theme.of(context).primaryColorDark,
              fontSize: Sizes.size14,
              fontWeight: FontWeight.bold,
            ),
          )
        ],
      ),
    );
  }
}
