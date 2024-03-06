import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/models/script.dart';

class MyScriptBox extends StatelessWidget {
  final Script script;

  const MyScriptBox({super.key, required this.script});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 200,
      clipBehavior: Clip.hardEdge,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(
          Sizes.size20,
        ),
        border: Border.all(
          color: Colors.black,
          width: Sizes.size1,
        ),
      ),
      child: Stack(
        fit: StackFit.expand,
        children: [
          ColorFiltered(
            colorFilter: ColorFilter.mode(
              Colors.black.withOpacity(0.3),
              // 이미지에 적용할 투명도 및 어두운 정도 조절
              BlendMode.srcOver,
            ),
            child: Image.network(
              script.imageUrl,
              fit: BoxFit.cover,
              filterQuality: FilterQuality.low,
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(
              horizontal: Sizes.size12,
              vertical: Sizes.size16,
            ),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.end,
              children: [
                Text(
                  script.name,
                  style: TextStyle(
                    color: Colors.grey.shade200,
                    fontSize: Sizes.size28,
                    fontWeight: FontWeight.w500,
                  ),
                )
              ],
            ),
          )
        ],
      ),
    );
  }
}
