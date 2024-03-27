import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/config/global_functions.dart';
import 'package:mallook/constants/sizes.dart';

class HomeIconButton extends StatelessWidget {
  const HomeIconButton({super.key});

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: () => moveToNavigationScreen(),
      child: const FaIcon(
        FontAwesomeIcons.house,
        size: Sizes.size24,
        color: Colors.black,
      ),
    );
  }
}
