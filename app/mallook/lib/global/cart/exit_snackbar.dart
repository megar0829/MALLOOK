import 'package:flutter/material.dart';

SnackBar exitSnackBar() {
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
    content: const Text(
      '한번 더 누르면 앱이 종료됩니다.',
      style: TextStyle(
        color: Colors.white,
        fontWeight: FontWeight.bold,
      ),
    ),
  );
}
