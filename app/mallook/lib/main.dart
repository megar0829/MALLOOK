import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';
import 'package:mallook/feature/sign_up/sign_up_screen.dart';

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
        primaryColor: Colors.purple,
        disabledColor: Colors.grey.shade300,
        hintColor: Colors.black54,
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const SignUpScreen(),
      // home: const MainNavigationScreen(),
      localizationsDelegates: const [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: const [
        Locale('ko', 'KR'),
      ],
    );
  }
}
