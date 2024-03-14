import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/global/cart/cart_controller.dart';

class CartIconButton extends StatefulWidget {
  const CartIconButton({super.key});

  @override
  State<CartIconButton> createState() => _CartIconButtonState();
}

class _CartIconButtonState extends State<CartIconButton> {
  final CartController cartController = Get.put(CartController());
  @override
  Widget build(BuildContext context) {
    return Stack(
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
                shape: BoxShape.circle, // 원 모양 설정
                border: Border.all(
                  color: Theme.of(context).primaryColor,
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
            return const SizedBox.shrink();
          }
        }),
      ],
    );
  }
}
