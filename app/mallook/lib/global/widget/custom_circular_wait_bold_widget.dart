import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';

class CustomCircularWaitBoldWidget extends StatelessWidget {
  const CustomCircularWaitBoldWidget({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(
        vertical: Sizes.size32,
      ),
      child: Center(
        child: Column(
          children: [
            CircularProgressIndicator(
              color: Theme.of(context).primaryColorDark,
            ),
            Gaps.v10,
            Text(
              '잠시만 기다료~',
              style: TextStyle(
                fontSize: Sizes.size16,
                color: Theme.of(context).primaryColorDark,
                fontWeight: FontWeight.bold,
              ),
            )
          ],
        ),
      ),
    );
  }
}
