import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/global/cart/cart_controller.dart';
import 'package:mallook/global/widget/home_icon_button.dart';

class OrderScreen extends StatefulWidget {
  final List<CartItem>? carItem;

  const OrderScreen({super.key, this.carItem});

  @override
  State<OrderScreen> createState() => _OrderScreenState();
}

class _OrderScreenState extends State<OrderScreen> {
  late List<CartItem> _cartItems;
  final CartController cartController = Get.put(CartController());
  bool _selectAllItem = true;

  @override
  void initState() {
    super.initState();
    _cartItems = widget.carItem ?? cartController.items;
    for (var item in _cartItems) {
      if (!item.selected) {
        _selectAllItem = false;
        break;
      }
    }
    // if (_cartItems.isEmpty) {
    //   print('empty cart');
    //   goBack(context);
    // }
  }

  void goBack(BuildContext context) {
    Navigator.of(context).pop();
  }

  void _toggleAllItem(bool? value) {
    _selectAllItem = value!;
    if (_selectAllItem) {
      for (var element in _cartItems) {
        element.selected = true;
      }
    } else {
      for (var element in _cartItems) {
        element.selected = false;
      }
    }
    setState(() {});
  }

  // 선택되어 있는 상품 삭제
  void _clearSelectedItem() {
    List<CartItem> removeItems = [];

    for (var item in _cartItems) {
      if (item.selected) {
        removeItems.add(item);
      }
    }

    for (var item in removeItems) {
      _cartItems.remove(item);
    }
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        surfaceTintColor: Colors.white,
        elevation: 1,
        shadowColor: Colors.grey.shade400,
        centerTitle: true,
        title: const Text('장바구니'),
        titleTextStyle: const TextStyle(
          color: Colors.black,
          fontWeight: FontWeight.bold,
          fontSize: Sizes.size18,
        ),
        actions: const [
          HomeIconButton(),
          Gaps.h20,
        ],
        bottom: PreferredSize(
          preferredSize: const Size.fromHeight(
            Sizes.size56,
          ),
          child: Padding(
            padding: const EdgeInsets.symmetric(
              horizontal: Sizes.size18,
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Row(
                  children: [
                    Transform.scale(
                      scale: 1.4,
                      child: Checkbox(
                        checkColor: Theme.of(context).primaryColorDark,
                        fillColor: const MaterialStatePropertyAll(Colors.white),
                        shape: const CircleBorder(
                          side: BorderSide(
                            color: Colors.black,
                          ),
                        ),
                        materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
                        visualDensity: const VisualDensity(
                          horizontal: VisualDensity.minimumDensity,
                        ),
                        value: _selectAllItem,
                        onChanged: (value) => _toggleAllItem(value),
                      ),
                    ),
                    Gaps.h8,
                    const Text(
                      '전체선택',
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: Sizes.size16,
                      ),
                    )
                  ],
                ),
                GestureDetector(
                  onTap: _clearSelectedItem,
                  child: Text(
                    '상품삭제',
                    style: TextStyle(
                      color: Colors.grey.shade500,
                      fontSize: Sizes.size16,
                    ),
                  ),
                )
              ],
            ),
          ),
        ),
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            ListView.builder(
              shrinkWrap: true,
              physics: const NeverScrollableScrollPhysics(),
              itemCount: _cartItems.length,
              itemBuilder: (context, index) => Text(
                _cartItems[index].toString(),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
