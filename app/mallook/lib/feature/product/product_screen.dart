import 'dart:math';

import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:get/get.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';
import 'package:mallook/feature/product/widget/order_sheet.dart';
import 'package:mallook/feature/search/search_screen.dart';
import 'package:mallook/global/cart/cart_controller.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';

class ProductScreen extends StatefulWidget {
  final Product product;

  const ProductScreen({
    super.key,
    required this.product,
  });

  @override
  State<ProductScreen> createState() => _ProductScreenState();
}

class _ProductScreenState extends State<ProductScreen> {
  final PageController _imageController = PageController();
  final ScrollController _storeController = ScrollController();
  final int _pageLength = Random().nextInt(5) + 3;
  int _currentPageIndex = 0;

  final List<String> sizes = [
    'XS',
    'S',
    'M',
    'L',
    'XL',
  ];

  final List<String> colors = [
    'red',
    'yellow',
    'blue',
    'green',
    'orange',
  ];

  @override
  void dispose() {
    super.dispose();
    _imageController.dispose();
    _storeController.dispose();
  }

  void _onClosePressed() {
    Navigator.of(context).pop();
  }

  void _clickHeartIcon() {}

  void _moveToHomeScreen() {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const MainNavigationScreen(),
      ),
    );
  }

  void _showOrderBottomSheet() async {
    await showModalBottomSheet(
      backgroundColor: Colors.transparent,
      isScrollControlled: true,
      context: context,
      builder: (context) => OrderSheet(
        product: widget.product,
        title: widget.product.name,
        sizes: sizes,
        colors: colors,
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.grey.shade50,
        surfaceTintColor: Colors.grey.shade50,
        elevation: 1,
        shadowColor: Colors.grey.shade300,
        actions: [
          IconButton(
            onPressed: _moveToHomeScreen,
            icon: const FaIcon(
              Icons.home,
              size: Sizes.size28,
            ),
          ),
          const CartIconButton(),
          Gaps.h20,
        ],
      ),
      body: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.end,
          children: [
            Padding(
              padding: const EdgeInsets.only(
                top: Sizes.size10,
                left: Sizes.size24,
                right: Sizes.size24,
                bottom: Sizes.size6,
              ),
              child: Container(
                clipBehavior: Clip.hardEdge,
                decoration: BoxDecoration(
                  color: Colors.grey.shade300,
                  borderRadius: BorderRadius.circular(
                    Sizes.size20,
                  ),
                  border: Border.all(
                    color: Colors.grey.shade200,
                    width: 0.5,
                  ),
                ),
                child: AspectRatio(
                  aspectRatio: 1,
                  child: Stack(
                    alignment: Alignment.topRight,
                    children: [
                      PageView.builder(
                        controller: _imageController,
                        itemBuilder: (context, index) => Image.network(
                          widget.product.image!,
                          fit: BoxFit.cover,
                        ),
                        itemCount: _pageLength,
                        onPageChanged: (value) {
                          setState(() {
                            _currentPageIndex = value;
                          });
                        },
                      ),
                      Container(
                        padding: const EdgeInsets.symmetric(
                          vertical: Sizes.size4,
                          horizontal: Sizes.size12,
                        ),
                        margin: const EdgeInsets.all(
                          Sizes.size10,
                        ),
                        decoration: BoxDecoration(
                          color: Colors.black.withOpacity(0.8),
                          borderRadius: BorderRadius.circular(Sizes.size52),
                        ),
                        child: Text(
                          '${_currentPageIndex + 1}/$_pageLength',
                          style: const TextStyle(
                            color: Colors.white,
                          ),
                        ),
                      )
                    ],
                  ),
                ),
              ),
            ),
            Gaps.v6,
            Container(
              height: Sizes.size24,
              padding: const EdgeInsets.symmetric(
                horizontal: Sizes.size32,
              ),
              child: Stack(
                alignment: Alignment.center,
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.end,
                    children: [
                      GestureDetector(
                        onTap: _clickHeartIcon,
                        child: const FaIcon(
                          FontAwesomeIcons.heart,
                          size: Sizes.size24,
                        ),
                      )
                    ],
                  ),
                  SmoothPageIndicator(
                    controller: _imageController,
                    count: _pageLength,
                    effect: ScrollingDotsEffect(
                      dotHeight: Sizes.size12,
                      dotWidth: Sizes.size12,
                      activeDotScale: 1.4,
                      dotColor: Theme.of(context).primaryColorLight,
                      activeDotColor: Theme.of(context).primaryColor,
                    ),
                  ),
                ],
              ),
            ),
            Gaps.v10,
            Padding(
              padding: const EdgeInsets.symmetric(
                vertical: Sizes.size8,
                horizontal: Sizes.size32,
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Text(
                    widget.product.name,
                    maxLines: 5,
                    style: const TextStyle(
                      color: Colors.black,
                      fontWeight: FontWeight.bold,
                      fontSize: Sizes.size16,
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
      bottomNavigationBar: BottomAppBar(
        padding: const EdgeInsets.symmetric(
            horizontal: Sizes.size32, vertical: Sizes.size10),
        color: Colors.white,
        surfaceTintColor: Colors.white,
        elevation: 1,
        child: Row(
          children: [
            Expanded(
              child: ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: Theme.of(context).primaryColorDark,
                  padding: const EdgeInsets.symmetric(
                    vertical: Sizes.size12,
                  ),
                ),
                onPressed: _showOrderBottomSheet,
                child: const Text(
                  '구매하기',
                  style: TextStyle(
                    fontSize: Sizes.size20,
                    fontWeight: FontWeight.bold,
                    color: Colors.white,
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
