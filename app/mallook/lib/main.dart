import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:kakao_flutter_sdk_user/kakao_flutter_sdk_user.dart';
import 'package:mallook/feature/login/LoginScreen.dart';
import 'package:mallook/feature/sign_up/sign_up_screen.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  KakaoSdk.init(
    nativeAppKey: 'a03e556f0284d3583b06a666cf4ff030',
  );

  runApp(const Mallook());
}

const seedColor = Color(0xffddc8ff);

class Mallook extends StatelessWidget {
  const Mallook({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mallook',
      theme: ThemeData(
        primaryColor: const Color(0xff9452d5),
        primaryColorLight: const Color(0xffe5d7ff),
        primaryColorDark: const Color(0xff5a1ca6),
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
      // home: const SignUpScreen(),
      home: const LoginScreen(),
    );
  }
}
