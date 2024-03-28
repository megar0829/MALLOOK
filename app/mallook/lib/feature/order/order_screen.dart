import 'package:flutter/material.dart';
import 'package:mallook/global/cart/cart_controller.dart';

class OrderScreen extends StatelessWidget {
  final List<CartItem> cartItem;

  const OrderScreen({super.key, required this.cartItem});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
    );
  }
}
