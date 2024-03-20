import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';
import 'package:mallook/feature/product/widget/order_sheet.dart';
import 'package:mallook/feature/product/widget/product_img_widget.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';

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
  final ScrollController _storeController = ScrollController();
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
              FontAwesomeIcons.house,
              size: Sizes.size24,
              color: Colors.black,
            ),
          ),
          const CartIconButton(),
          Gaps.h20,
        ],
      ),
      body: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            ProductImgWidget(
              product: widget.product,
            ),
            Gaps.v10,
            Padding(
              padding: const EdgeInsets.symmetric(
                vertical: Sizes.size8,
                horizontal: Sizes.size32,
              ),
              child: Column(
                children: [
                  Text(
                    widget.product.name,
                    maxLines: 5,
                    style: const TextStyle(
                      color: Colors.black,
                      fontWeight: FontWeight.bold,
                      fontSize: Sizes.size18,
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
