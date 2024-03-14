import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/home_my_screen.dart';
import 'package:mallook/feature/home/home_others_screen.dart';
import 'package:mallook/global/cart/cart_controller.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';

final tabs = ['My', 'Popular'];

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
      child: SafeArea(
        child: Scaffold(
          backgroundColor: Colors.white,
          appBar: AppBar(
            toolbarHeight: Sizes.size40,
            elevation: 1,
            toolbarOpacity: 0,
            backgroundColor: Colors.white,
            surfaceTintColor: Colors.white,
            bottom: PreferredSize(
              preferredSize: const Size.fromHeight(Sizes.size48),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Expanded(
                        child: Container(),
                      ),
                      Expanded(
                        child: Align(
                          alignment: Alignment.center,
                          child: Row(
                            children: [
                              const Text(
                                "MAL",
                                style: TextStyle(
                                  overflow: TextOverflow.visible,
                                  fontWeight: FontWeight.w800,
                                  fontSize: Sizes.size28,
                                  color: Colors.black,
                                ),
                              ),
                              Text(
                                "LOOK",
                                style: TextStyle(
                                  overflow: TextOverflow.visible,
                                  fontWeight: FontWeight.w800,
                                  fontSize: Sizes.size28,
                                  color: Theme.of(context).primaryColorDark,
                                ),
                              ),
                            ],
                          ),
                        ),
                      ),
                      const Expanded(
                        child: Padding(
                          padding: EdgeInsets.only(
                            right: Sizes.size20,
                          ),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.end,
                            children: [Gaps.h6, CartIconButton()],
                          ),
                        ),
                      ),
                    ],
                  ),
                  Gaps.v8,
                  TabBar(
                    padding: const EdgeInsets.symmetric(
                      horizontal: Sizes.size4,
                    ),
                    splashFactory: NoSplash.splashFactory,
                    isScrollable: true,
                    labelColor: Theme.of(context).primaryColorDark,
                    unselectedLabelColor: Colors.grey.shade500,
                    labelStyle: const TextStyle(
                      fontSize: Sizes.size16,
                      fontWeight: FontWeight.bold,
                    ),
                    indicatorColor: Theme.of(context).primaryColorDark,
                    indicatorWeight: 0.1,
                    tabAlignment: TabAlignment.center,
                    tabs: [
                      for (var tab in tabs)
                        Tab(
                          height: Sizes.size28,
                          text: tab,
                        ),
                    ],
                  ),
                ],
              ),
            ),
          ),
          body: const TabBarView(
            physics: NeverScrollableScrollPhysics(),
            children: [
              HomeMyScreen(),
              HomeOthersScreen(),
            ],
          ),
        ),
      ),
    );
  }
}
