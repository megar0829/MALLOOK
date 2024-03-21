import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';

class MyScriptScreen extends StatelessWidget {
  const MyScriptScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: const Text(
          "내 스크립트",
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
