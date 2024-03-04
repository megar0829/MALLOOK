import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:mallook/feature/login/LoginScreen.dart';

void main() {
  runApp(const Mallook());
}

const seedColor = Color(0xff6750a4);

class Mallook extends StatelessWidget {
  const Mallook({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mallook',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(
          seedColor: seedColor,
          brightness: Brightness.light,
        ),
        // textTheme: GoogleFonts.nanumGothicTextTheme(),
        textTheme: GoogleFonts.notoSansKrTextTheme(
          Theme.of(context).textTheme,
        ),
      ),
      // home: const SignUpScreen(),
      localizationsDelegates: const [
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: const [
        Locale('ko', 'KR'),
      ],
      // home: const MainNavigationScreen(),
      home: const LoginScreen(),
    );
  }
}
