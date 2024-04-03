import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/script/model/script.dart';

class MyScriptListBox extends StatelessWidget {
  final Script script;

  const MyScriptListBox({super.key, required this.script});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 320,
      clipBehavior: Clip.hardEdge,
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(
          Sizes.size20,
        ),
        border: Border.all(
          color: Colors.grey.shade300,
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
            child: FadeInImage.assetNetwork(
              placeholder: "assets/images/app_logo/logo_sm.png",
              image: script.imageUrl!,
              fit: BoxFit.cover,
              filterQuality: FilterQuality.low,
              fadeOutDuration: const Duration(
                milliseconds: 100,
              ),
              fadeInDuration: const Duration(
                milliseconds: 100,
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(
              horizontal: Sizes.size18,
              vertical: Sizes.size24,
            ),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    Column(
                      children: [
                        const FaIcon(
                          FontAwesomeIcons.solidHeart,
                          size: Sizes.size20,
                          color: Colors.redAccent,
                        ),
                        Text(
                          "${script.heartCount}",
                          style: const TextStyle(
                            color: Colors.white,
                            fontWeight: FontWeight.bold,
                            fontSize: Sizes.size16,
                          ),
                        )
                      ],
                    )
                  ],
                ),
                Text(
                  script.name!,
                  maxLines: 5,
                  overflow: TextOverflow.ellipsis,
                  style: const TextStyle(
                    color: Colors.white,
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
