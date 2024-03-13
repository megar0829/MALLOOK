import 'package:flutter/material.dart';

SnackBar mallookSnackBar(String title) {
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
    content: Text(
      title,
      style: const TextStyle(
        color: Colors.white,
        fontWeight: FontWeight.bold,
      ),
    ),
  );
}
