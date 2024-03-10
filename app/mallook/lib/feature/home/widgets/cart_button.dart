import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';

class CartButton extends StatelessWidget {
  const CartButton({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: Colors.white.withOpacity(0.6),
        shape: BoxShape.circle,
        border: Border.all(
          color: Theme.of(context).primaryColorLight,
          width: 0.5,
        ),
      ),
      child: const Padding(
        padding: EdgeInsets.only(
          top: Sizes.size6,
          bottom: Sizes.size2,
          left: Sizes.size4,
          right: Sizes.size4,
        ),
        child: Icon(
          Icons.add_shopping_cart_rounded,
          size: Sizes.size24,
        ),
      ),
    );
  }
}
