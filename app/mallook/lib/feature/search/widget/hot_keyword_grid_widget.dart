import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';

class HotKeywordGridWidget extends StatelessWidget {
  final List<String> hotKeywords;
  final Function addKeyword;

  const HotKeywordGridWidget({
    super.key,
    required this.hotKeywords,
    required this.addKeyword,
  });

  @override
  Widget build(BuildContext context) {
    return GridView.builder(
      gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 2,
        mainAxisExtent: Sizes.size32,
        mainAxisSpacing: Sizes.size10,
        crossAxisSpacing: Sizes.size10,
      ),
      itemCount: hotKeywords.length,
      itemBuilder: (context, index) => GestureDetector(
        onTap: () => addKeyword(hotKeywords[index]),
        child: Row(
          children: [
            Flexible(
              flex: 1,
              child: Text(
                '${index + 1}.'.padLeft(3, '  '),
                style: const TextStyle(
                  color: Colors.black,
                  fontSize: Sizes.size12,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            Gaps.h8,
            // FaIcon(
            //   hotKeywords[index].change == 0
            //       ? FontAwesomeIcons.minus
            //       : hotKeywords[index].change > 0
            //           ? FontAwesomeIcons.caretUp
            //           : FontAwesomeIcons.caretDown,
            //   color: hotKeywords[index].change == 0
            //       ? Colors.black
            //       : hotKeywords[index].change > 0
            //           ? Colors.red
            //           : Colors.blue,
            //   size: Sizes.size18,
            //   shadows: [
            //     BoxShadow(
            //       color: Colors.grey.shade400,
            //       blurRadius: Sizes.size3,
            //       offset: const Offset(Sizes.size1, Sizes.size1),
            //     )
            //   ],
            // ),
            // Gaps.h5,
            // Text(
            //   '${hotKeywords[index].change.abs()}'.padLeft(3, '  '),
            //   style: const TextStyle(
            //     color: Colors.black,
            //     fontSize: Sizes.size12,
            //     fontWeight: FontWeight.bold,
            //   ),
            // ),
            Gaps.h12,
            Text(
              hotKeywords[index],
              overflow: TextOverflow.ellipsis,
              style: const TextStyle(
                color: Colors.black,
                fontSize: Sizes.size14,
                fontWeight: FontWeight.bold,
              ),
            )
          ],
        ),
      ),
    );
  }
}
