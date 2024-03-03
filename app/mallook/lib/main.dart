import 'package:flutter/material.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';

void main() {
  runApp(const Mallook());
}

class Mallook extends StatelessWidget {
  const Mallook({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mallook',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MainNavigationScreen(),
    );
  }
}
