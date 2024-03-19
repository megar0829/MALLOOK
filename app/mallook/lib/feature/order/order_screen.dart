import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mallook/global/cart/cart_controller.dart';

class OrderScreen extends StatefulWidget {
  final List<CartItem>? carItem;

  const OrderScreen({super.key, this.carItem});

  @override
  State<OrderScreen> createState() => _OrderScreenState();
}

class _OrderScreenState extends State<OrderScreen> {
  late List<CartItem> _cartItems;
  final CartController cartController = Get.put(CartController());

  @override
  void initState() {
    super.initState();
    _cartItems = widget.carItem ?? cartController.items;
    if (_cartItems.isEmpty) {
      goBack(context);
    }
  }

  void goBack(BuildContext context) {
    Navigator.of(context).pop();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ListView.builder(
        itemCount: _cartItems.length,
        itemBuilder: (context, index) => Text(
          _cartItems[index].toString(),
        ),
      ),
    );
  }
}
