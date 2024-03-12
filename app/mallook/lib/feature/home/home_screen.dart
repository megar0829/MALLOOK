import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/home_my_screen.dart';
import 'package:mallook/feature/home/home_others_screen.dart';
import 'package:mallook/global/cart/cart_controller.dart';

final tabs = ['my', 'others'];

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final CartController cartController = Get.put(CartController());

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
                          child: Text(
                            "MALLOOK",
                            style: TextStyle(
                              overflow: TextOverflow.visible,
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
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              const Icon(
                                Icons.search_rounded,
                                size: Sizes.size32,
                              ),
                              Gaps.h6,
                              Stack(
                                children: [
                                  Obx(
                                    () => Padding(
                                      padding: EdgeInsets.only(
                                        left: Sizes.size6,
                                        top: cartController
                                                    .totalQuantity.value ==
                                                0
                                            ? 0
                                            : Sizes.size2,
                                      ),
                                      child: Icon(
                                        Icons.shopping_bag_outlined,
                                        size: Sizes.size32,
                                        color: cartController
                                                    .totalQuantity.value ==
                                                0
                                            ? Colors.black
                                            : Colors.black87,
                                      ),
                                    ),
                                  ),
                                  Obx(() {
                                    // totalQuantity가 0보다 큰 경우에만 Container를 표시합니다.
                                    if (cartController.totalQuantity.value >
                                        0) {
                                      return Container(
                                        decoration: BoxDecoration(
                                          color: Theme.of(context)
                                              .primaryColorDark,
                                          shape: BoxShape.circle, // 원 모양 설정
                                          border: Border.all(
                                            color:
                                                Theme.of(context).primaryColor,
                                            // 테마의 primaryColor 사용
                                            width: 0.5,
                                          ), // 테두리 설정
                                        ),
                                        width: 20,
                                        height: 20,
                                        child: Center(
                                          child: Text(
                                            '${cartController.totalQuantity}',
                                            // totalQuantity 값을 표시합니다.
                                            style: const TextStyle(
                                              color: Colors.white,
                                              // 테마의 primaryColor 사용
                                              fontSize: Sizes.size12,
                                              // Sizes.size14 대신 고정값을 사용하였습니다.
                                              fontWeight: FontWeight.w800,
                                            ),
                                          ),
                                        ),
                                      );
                                    } else {
                                      return const SizedBox
                                          .shrink(); // totalQuantity가 0 이하일 경우 아무것도 표시하지 않습니다.
                                    }
                                  }),
                                ],
                              ),
                            ],
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
