import 'package:awesome_bottom_bar/awesome_bottom_bar.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/category/category_screen.dart';
import 'package:mallook/feature/home/home_screen.dart';
import 'package:mallook/feature/profile/profile_screen.dart';
import 'package:mallook/feature/search/search_screen.dart';
import 'package:mallook/feature/style/style_screen.dart';
import 'package:mallook/global/mallook_snackbar.dart';

import '../profile/api/profile_api_service.dart';

const List<TabItem> items = [
  TabItem(
    icon: FontAwesomeIcons.solidStar,
    title: '추천',
  ),
  TabItem(
    icon: FontAwesomeIcons.bars,
    title: '상품',
  ),
  TabItem(
    icon: FontAwesomeIcons.magnifyingGlass,
    title: '검색',
  ),
  TabItem(
    icon: FontAwesomeIcons.shirt,
    title: '몰룩북',
  ),
  TabItem(
    icon: FontAwesomeIcons.solidUser,
    title: '프로필',
  ),
];

class MainNavigationScreen extends StatefulWidget {
  const MainNavigationScreen({super.key});

  @override
  State<MainNavigationScreen> createState() => _MainNavigationScreenState();
}

class _MainNavigationScreenState extends State<MainNavigationScreen> {
  var i = ProfileApiService.getMemberDetail();
  DateTime? _currentBackPressTime;
  int _selectedIndex = 0;

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

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
            mallookSnackBar(title: '한번 더 누르면 앱이 종료됩니다.'),
          );
          return;
        }
        SystemNavigator.pop();
      },
      child: Scaffold(
        backgroundColor: Colors.white,
        resizeToAvoidBottomInset: false,
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
              child: const SearchScreen(),
            ),
            Offstage(
              offstage: _selectedIndex != 3,
              child: const StyleScreen(),
            ),
            Offstage(
              offstage: _selectedIndex != 4,
              child: ProfileScreen(),
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
          onTap: (index) => _onItemTapped(index),
        ),
      ),
    );
  }
}
