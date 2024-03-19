import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';

class ItemButton extends StatelessWidget {
  final IconData icon;

  const ItemButton({super.key, required this.icon});

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
      child: Padding(
        padding: const EdgeInsets.only(
          top: Sizes.size6,
          bottom: Sizes.size2,
          left: Sizes.size4,
          right: Sizes.size4,
        ),
        child: Icon(
          icon,
          size: Sizes.size24,
        ),
      ),
    );
  }
}
