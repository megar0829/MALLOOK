import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/global/widget/home_icon_button.dart';

class OrderedScreen extends StatelessWidget {
  final num orderId;

  const OrderedScreen({super.key, required this.orderId});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        surfaceTintColor: Colors.white,
        elevation: 1,
        shadowColor: Colors.grey.shade400,
        title: const Text('주문 내역'),
        centerTitle: true,
        titleTextStyle: const TextStyle(
          color: Colors.black,
          fontSize: Sizes.size18,
          fontWeight: FontWeight.bold,
        ),
        actions: const [
          HomeIconButton(),
          Gaps.h20,
        ],
      ),
      body: Center(
        child: Text('ordered screen $orderId'),
      ),
    );
  }
}
