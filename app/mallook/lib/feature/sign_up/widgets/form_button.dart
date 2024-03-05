import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';

class FormButton extends StatelessWidget {
  const FormButton({
    super.key,
    required this.disabled,
    required this.onTap,
    required this.text,
  });

  final String text;
  final bool disabled;
  final void Function() onTap;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => onTap(),
      child: FractionallySizedBox(
        widthFactor: 1,
        child: AnimatedContainer(
          // MEMO: Container의 변화에 애니메이션 효과 적용
          padding: const EdgeInsets.symmetric(
            vertical: Sizes.size12,
          ),
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(Sizes.size5),
            color: disabled
                ? Theme.of(context).colorScheme.primary
                : Theme.of(context).colorScheme.primaryContainer,
          ),
          duration: const Duration(milliseconds: 300),
          child: AnimatedDefaultTextStyle(
            style: TextStyle(
              color: disabled
                  ? Theme.of(context).colorScheme.onPrimary
                  : Theme.of(context).colorScheme.primary,
              fontWeight: FontWeight.w600,
              fontSize: Sizes.size16,
              letterSpacing: Sizes.size4,
            ),
            duration: const Duration(milliseconds: 300),
            child: Text(
              text,
              textAlign: TextAlign.center,
            ),
          ),
        ),
      ),
    );
  }
}
