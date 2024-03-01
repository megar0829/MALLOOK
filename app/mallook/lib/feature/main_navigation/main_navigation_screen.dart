import 'package:awesome_bottom_bar/awesome_bottom_bar.dart';
import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';

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
  int _visit = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Center(
          child: Text(
            "MALLOOK",
            style: TextStyle(
              fontWeight: FontWeight.w600,
              fontSize: Sizes.size24,
              color: Colors.black87,
            ),
          ),
        ),
      ),
      body: const Center(
        child: FaIcon(FontAwesomeIcons.magnifyingGlass),
      ),
      bottomNavigationBar: Container(
        clipBehavior: Clip.hardEdge,
        decoration: const BoxDecoration(
            borderRadius: BorderRadius.only(
          topLeft: Radius.circular(
            Sizes.size24,
          ),
          topRight: Radius.circular(
            Sizes.size24,
          ),
        )),
        child: BottomBarDefault(
          color: Colors.white,
          colorSelected: Colors.deepPurpleAccent,
          backgroundColor: Colors.black,
          indexSelected: _visit,
          items: items,
          paddingVertical: Sizes.size12,
          onTap: (index) => setState(() {
            _visit = index;
            print(_visit);
          }),
        ),
      ),
    );
  }
}
