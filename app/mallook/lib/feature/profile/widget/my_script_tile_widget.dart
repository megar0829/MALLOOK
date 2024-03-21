import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/sizes.dart';

class MyScriptTileWidget extends StatelessWidget {
  const MyScriptTileWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return ListTile(
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
        FontAwesomeIcons.scroll,
        color: Colors.teal,
        size: Sizes.size24,
      ),
      title: const Text(
        "스크립트",
      ),
      titleTextStyle: const TextStyle(
        color: Colors.black,
        fontWeight: FontWeight.bold,
        fontSize: Sizes.size20,
      ),
    );
  }
}
