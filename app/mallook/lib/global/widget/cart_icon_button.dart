import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/order/order_screen.dart';
import 'package:mallook/global/cart/cart_controller.dart';

class CartIconButton extends StatefulWidget {
  const CartIconButton({super.key});

  @override
  State<CartIconButton> createState() => _CartIconButtonState();
}

class _CartIconButtonState extends State<CartIconButton> {
  final CartController cartController = Get.put(CartController());

  void _moveToOrderScreen() {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => const OrderScreen(),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => _moveToOrderScreen(),
      child: Stack(
        children: [
          Obx(
            () => Padding(
              padding: EdgeInsets.only(
                left: Sizes.size6,
                top: cartController.totalQuantity.value == 0 ? 0 : Sizes.size2,
              ),
              child: Icon(
                Icons.shopping_bag_outlined,
                size: Sizes.size32,
                color: cartController.totalQuantity.value == 0
                    ? Colors.black
                    : Colors.black87,
              ),
            ),
          ),
          Obx(() {
            if (cartController.totalQuantity.value > 0) {
              return Container(
                decoration: BoxDecoration(
                  color: Theme.of(context).primaryColorDark,
                  shape: BoxShape.circle,
                  border: Border.all(
                    color: Theme.of(context).primaryColor,
                    width: 0.5,
                  ),
                ),
                width: 20,
                height: 20,
                child: Center(
                  child: Text(
                    '${cartController.totalQuantity}',
                    style: const TextStyle(
                      color: Colors.white,
                      fontSize: Sizes.size12,
                      fontWeight: FontWeight.w800,
                    ),
                  ),
                ),
              );
            } else {
              return const SizedBox.shrink();
            }
          }),
        ],
      ),
    );
  }
}
