import 'package:awesome_bottom_bar/awesome_bottom_bar.dart';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
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
  int _cartItemCount = 6;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        backgroundColor: Colors.white,
        surfaceTintColor: Colors.white,
        title: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Expanded(
              child: Container(),
            ),
            Expanded(
              child: Align(
                alignment: Alignment.center,
                child: Text(
                  "MALLOOK",
                  style: TextStyle(
                    fontWeight: FontWeight.w800,
                    fontSize: Sizes.size28,
                    color: Theme.of(context).primaryColorDark,
                  ),
                ),
              ),
            ),
            Expanded(
              child: Padding(
                padding: const EdgeInsets.only(
                  right: Sizes.size4,
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    const Icon(
                      Icons.search_rounded,
                      size: Sizes.size32,
                    ),
                    Gaps.h6,
                    Stack(
                      children: [
                        Padding(
                          padding: EdgeInsets.only(
                            left: Sizes.size6,
                            top: _cartItemCount == 0 ? 0 : Sizes.size2,
                          ),
                          child: Icon(
                            Icons.shopping_bag_outlined,
                            size: Sizes.size32,
                            color: _cartItemCount == 0
                                ? Colors.black
                                : Colors.black87,
                          ),
                        ),
                        if (_cartItemCount > 0)
                          Container(
                            decoration: BoxDecoration(
                              color: Colors.white,
                              shape: BoxShape.circle, // 원 모양 설정
                              border: Border.all(
                                color: Theme.of(context).primaryColor,
                                width: 0.5,
                              ), // 테두리 설정
                            ),
                            width: 20,
                            height: 20,
                            child: Center(
                              child: Text(
                                '$_cartItemCount',
                                style: TextStyle(
                                  color: Theme.of(context).primaryColor,
                                  fontSize: Sizes.size14,
                                  fontWeight: FontWeight.w800,
                                ),
                              ),
                            ),
                          ),
                      ],
                    ),
                  ],
                ),
              ),
            ),
          ],
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
        onTap: (index) => setState(
          () {
            _selectedIndex = index;
          },
        ),
      ),
    );
  }
}
