import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';

class WorldcupScreen extends StatelessWidget {
  const WorldcupScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: const Text(
          "내 옷 취향 찾기",
          style: TextStyle(
            color: Colors.black,
            fontSize: Sizes.size18,
            fontWeight: FontWeight.bold,
          ),
        ),
      ),
    );
  }
}
