import 'package:flutter/material.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';

void main() {
  runApp(const Mallook());
}

class Mallook extends StatelessWidget {
  const Mallook({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mallook',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const MainNavigationScreen(),
    );
  }
}
