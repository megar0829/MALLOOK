import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';

SnackBar mallookSnackBar({
  required String title,
  IconData? icon,
  void Function()? onTap, // 선택적 매개변수로 함수를 받음
}) {
  return SnackBar(
    behavior: SnackBarBehavior.floating,
    elevation: 0.0,
    shape: const StadiumBorder(
      side: BorderSide(
        style: BorderStyle.none,
      ),
    ),
    duration: const Duration(seconds: 2),
    backgroundColor: Colors.black54.withOpacity(0.6),
    content: Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Text(
          title,
          style: const TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.bold,
          ),
        ),
        if (icon != null)
          GestureDetector(
            onTap: onTap,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                const Text(
                  '보러가기',
                  style: TextStyle(
                    color: Colors.lightBlueAccent,
                    fontSize: Sizes.size14,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                Icon(
                  icon,
                  size: Sizes.size16,
                  color: Colors.lightBlueAccent,
                ),
              ],
            ),
          ),
      ],
    ),
  );
}
