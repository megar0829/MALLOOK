import 'package:awesome_bottom_bar/awesome_bottom_bar.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/category/category_screen.dart';
import 'package:mallook/feature/home/home_screen.dart';
import 'package:mallook/feature/style/style_screen.dart';
import 'package:mallook/feature/user/user_screen.dart';
import 'package:mallook/feature/worldcup/wordcup_screen.dart';

const List<TabItem> items = [
  TabItem(
    icon: FontAwesomeIcons.houseChimney,
    title: '홈',
  ),
  TabItem(
    icon: FontAwesomeIcons.bars,
    title: '카테고리',
  ),
  TabItem(
    icon: FontAwesomeIcons.shirt,
    title: '코디',
  ),
  TabItem(
    icon: FontAwesomeIcons.trophy,
    title: '월드컵',
  ),
  TabItem(
    icon: FontAwesomeIcons.solidUser,
    title: '마이페이지',
  ),
];

class MainNavigationScreen extends StatefulWidget {
  const MainNavigationScreen({super.key});

  @override
  State<MainNavigationScreen> createState() => _MainNavigationScreenState();
}

class _MainNavigationScreenState extends State<MainNavigationScreen> {
  int _selectedIndex = 0;
  DateTime? _currentBackPressTime;

  @override
  Widget build(BuildContext context) {
    return PopScope(
      canPop: false,
      onPopInvoked: (didPop) {
        DateTime now = DateTime.now();
        if (_currentBackPressTime == null ||
            now.difference(_currentBackPressTime!) >
                const Duration(seconds: 2)) {
          _currentBackPressTime = now;
          ScaffoldMessenger.of(context).showSnackBar(
            SnackBar(
              behavior: SnackBarBehavior.floating,
              elevation: 0.0,
              shape: const StadiumBorder(
                side: BorderSide(
                  style: BorderStyle.none,
                ),
              ),
              duration: const Duration(seconds: 2),
              backgroundColor: Colors.grey.shade100,
              content: const Text(
                '한번 더 누르면 앱이 종료됩니다.',
                style: TextStyle(
                  color: Colors.black87,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          );
          return;
        }
        SystemNavigator.pop();
      },
      child: Scaffold(
        backgroundColor: Colors.white,
        resizeToAvoidBottomInset: false,
        appBar: AppBar(
          backgroundColor: Colors.white,
          surfaceTintColor: Colors.white,
          title: Center(
            child: Text(
              "MALLOOK",
              style: TextStyle(
                fontWeight: FontWeight.w600,
                fontSize: Sizes.size24,
                color: Theme.of(context).primaryColor,
              ),
            ),
          ),
        ),
        body: Stack(
          children: [
            Offstage(
              offstage: _selectedIndex != 0,
              child: const HomeScreen(),
            ),
            Offstage(
              offstage: _selectedIndex != 1,
              child: const CategoryScreen(),
            ),
            Offstage(
              offstage: _selectedIndex != 2,
              child: const StyleScreen(),
            ),
            Offstage(
              offstage: _selectedIndex != 3,
              child: const WorldCupScreen(),
            ),
            Offstage(
              offstage: _selectedIndex != 4,
              child: const UserScreen(),
            ),
          ],
        ),
        bottomNavigationBar: BottomBarDefault(
          colorSelected: Theme.of(context).primaryColor,
          color: Colors.white,
          backgroundColor: Colors.black87,
          indexSelected: _selectedIndex,
          items: items,
          paddingVertical: Sizes.size12,
          iconSize: Sizes.size20,
          borderRadius: const BorderRadius.only(
            topLeft: Radius.circular(
              Sizes.size24,
            ),
            topRight: Radius.circular(
              Sizes.size24,
            ),
          ),
          onTap: (index) => setState(() {
            _selectedIndex = index;
          }),
        ),
      ),
    );
  }

  Future<void> _onBackBtnPressed(BuildContext context) {
    {
      DateTime now = DateTime.now();
      if (_currentBackPressTime == null ||
          now.difference(_currentBackPressTime!) > const Duration(seconds: 2)) {
        _currentBackPressTime = now;
        ScaffoldMessenger.of(context).showSnackBar(
          SnackBar(
            behavior: SnackBarBehavior.floating,
            elevation: 0.0,
            shape: const StadiumBorder(
              side: BorderSide(
                style: BorderStyle.none,
              ),
            ),
            duration: const Duration(seconds: 2),
            backgroundColor: Colors.grey.shade100,
            content: const Text(
              '한번 더 누르면 앱이 종료됩니다.',
              style: TextStyle(
                color: Colors.black87,
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
        );
        return Future.value(false);
      }
      return Future.value(true);
    }
  }
}
