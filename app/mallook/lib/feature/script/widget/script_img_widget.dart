import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/models/script.dart';

class ScriptImgWidget extends StatelessWidget {
  final Script script;

  const ScriptImgWidget({super.key, required this.script});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
          clipBehavior: Clip.hardEdge,
          decoration: BoxDecoration(
            color: Colors.grey.shade300,
            borderRadius: BorderRadius.circular(
              Sizes.size20,
            ),
            border: Border.all(
              color: Colors.grey.shade200,
              width: 0.5,
            ),
          ),
          child: AspectRatio(
            aspectRatio: 1,
            child: Image.network(
              script.imageUrl!,
              filterQuality: FilterQuality.high,
              fit: BoxFit.cover,
            ),
          ),
        ),
      ],
    );
  }
}
