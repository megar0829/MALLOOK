import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';

class CustomCircularWaitWidget extends StatelessWidget {
  const CustomCircularWaitWidget({
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
              color: Theme.of(context).primaryColorLight,
            ),
            Gaps.v10,
            Text(
              '쪼매 기다리쇼 금방 돼여',
              style: TextStyle(
                fontSize: Sizes.size14,
                color: Theme.of(context).primaryColor,
                fontWeight: FontWeight.bold,
              ),
            )
          ],
        ),
      ),
    );
  }
}
