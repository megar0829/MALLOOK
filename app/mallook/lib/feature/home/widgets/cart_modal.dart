import 'dart:math';

import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:get/get.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/home/widgets/option_selector.dart';
import 'package:mallook/global/cart/cart_controller.dart';
import 'package:smooth_page_indicator/smooth_page_indicator.dart';

class CartModal extends StatefulWidget {
  final Product product;

  const CartModal({
    super.key,
    required this.product,
  });

  @override
  State<CartModal> createState() => _CartModalState();
}

class _CartModalState extends State<CartModal> {
  final CartController cartController = Get.put(CartController());
  final PageController _imageController = PageController();
  final ScrollController _storeController = ScrollController();
  final int _pageLength = Random().nextInt(5) + 3;
  int _currentPageIndex = 0;

  final List<String> items = [
    'Item1',
    'Item2',
    'Item3',
    'Item4',
    'Item5',
    'Item6',
    'Item7',
    'Item8',
  ];
  String? selectedValue;

  @override
  void dispose() {
    super.dispose();
    _imageController.dispose();
    _storeController.dispose();
  }

  void _onClosePressed() {
    Navigator.of(context).pop();
  }

  void _clickHeartIcon() {
    cartController.addItem(
      widget.product.name!,
      widget.product,
      3,
    );
  }

  void _updateValue(String? newValue) {
    setState(() {
      selectedValue = newValue;
      print(selectedValue);
    });
  }

  @override
  Widget build(BuildContext context) {
    final deviceSize = MediaQuery.of(context).size;
    return Container(
      height: deviceSize.height * 0.9,
      clipBehavior: Clip.hardEdge,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(
          Sizes.size14,
        ),
      ),
      child: Scaffold(
        backgroundColor: Colors.grey.shade50,
        appBar: AppBar(
          backgroundColor: Colors.grey.shade50,
          surfaceTintColor: Colors.grey.shade50,
          automaticallyImplyLeading: false,
          elevation: 1,
          shadowColor: Colors.grey.shade300,
          title: Container(
            padding: const EdgeInsets.symmetric(
              horizontal: Sizes.size16,
            ),
            child: Center(
              child: Text(
                widget.product.name!,
                overflow: TextOverflow.fade,
                style: const TextStyle(
                  color: Colors.black,
                  fontWeight: FontWeight.bold,
                  fontSize: Sizes.size16,
                ),
              ),
            ),
          ),
          actions: [
            IconButton(
              onPressed: _onClosePressed,
              icon: const FaIcon(
                FontAwesomeIcons.xmark,
              ),
            )
          ],
        ),
        body: Column(
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
            Expanded(
              child: Padding(
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size6,
                  horizontal: Sizes.size20,
                ),
                child: ListView.builder(
                  scrollDirection: Axis.horizontal,
                  itemCount: 6,
                  itemBuilder: (context, index) {
                    return Row(
                      children: [
                        Container(
                          width: deviceSize.width / 3,
                          height: Sizes.size48,
                          decoration: BoxDecoration(
                            border: Border.all(
                              color: Colors.grey.shade400,
                            ),
                          ),
                          child: Center(
                            child: Text('$index'),
                          ),
                        ),
                        Gaps.h10,
                      ],
                    );
                  },
                ),
              ),
            ),
            OptionSelector(
              items: items,
              onChanged: _updateValue,
              selectedItem: selectedValue,
            )
          ],
        ),
      ),
    );
  }
}
