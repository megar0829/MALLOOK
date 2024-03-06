import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/home-my-screen.dart';
import 'package:mallook/feature/home/home-others-screen.dart';

final tabs = ['my', 'others'];

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: tabs.length,
      child: Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          toolbarHeight: Sizes.size36,
          elevation: 1,
          toolbarOpacity: 0,
          backgroundColor: Colors.white,
          surfaceTintColor: Colors.white,
          title: TabBar(
            padding: const EdgeInsets.symmetric(
              horizontal: Sizes.size4,
            ),
            splashFactory: NoSplash.splashFactory,
            isScrollable: true,
            labelColor: Colors.black,
            unselectedLabelColor: Colors.grey.shade500,
            labelStyle: const TextStyle(
              fontSize: Sizes.size16,
              fontWeight: FontWeight.bold,
            ),
            // indicator: BoxDecoration(),
            indicatorColor: Colors.black,
            indicatorWeight: 0.1,
            tabs: [
              for (var tab in tabs)
                Tab(
                  height: Sizes.size28,
                  text: tab,
                ),
            ],
          ),
        ),
        body: const TabBarView(
          children: [
            HomeMyScreen(),
            HomeOthersScreen(),
          ],
        ),
      ),
    );
  }
}
