import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';

class GenderRadioButton extends StatelessWidget {
  final String text;
  final bool isSelected;
  final Function onTap;

  const GenderRadioButton({
    super.key,
    required this.text,
    required this.isSelected,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => onTap(),
      child: Container(
        padding: const EdgeInsets.symmetric(
          vertical: Sizes.size8,
          horizontal: Sizes.size24,
        ),
        decoration: BoxDecoration(
            color: isSelected
                ? Theme.of(context).colorScheme.tertiaryContainer
                : Colors.grey.shade300,
            border: Border.all(
              color: isSelected
                  ? Theme.of(context).colorScheme.tertiary.withOpacity(0.7)
                  : Theme.of(context).colorScheme.secondary.withOpacity(0.5),
            ),
            borderRadius: BorderRadius.circular(
              Sizes.size12,
            ),
            boxShadow: [
              BoxShadow(
                color: isSelected
                    ? Theme.of(context).colorScheme.tertiary.withOpacity(0.7)
                    : Theme.of(context).colorScheme.secondary.withOpacity(0.5),
                spreadRadius: 0.1,
                blurRadius: 2,
                offset: const Offset(1, 3),
              )
            ]),
        child: Text(
          text,
          textAlign: TextAlign.center,
          style: TextStyle(
            color: isSelected
                ? Theme.of(context).colorScheme.onSurface
                : Theme.of(context).colorScheme.secondary,
            fontSize: Sizes.size18,
            fontWeight: FontWeight.w600,
          ),
        ),
      ),
    );
  }
}
