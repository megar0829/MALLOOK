import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/worldcup/worldcup_screen.dart';

class FashionTileWidget extends StatelessWidget {
  const FashionTileWidget({super.key});

  void _moveToWorldcupScreen(BuildContext context) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const WorldcupScreen(),
      ),
    );
  }

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
      onTap: () => _moveToWorldcupScreen(context),
      leading: const FaIcon(
        FontAwesomeIcons.trophy,
        color: Colors.orange,
        size: Sizes.size24,
      ),
      title: const Text(
        "패션 월드컵",
      ),
      titleTextStyle: const TextStyle(
        color: Colors.black,
        fontWeight: FontWeight.bold,
        fontSize: Sizes.size20,
      ),
    );
  }
}
