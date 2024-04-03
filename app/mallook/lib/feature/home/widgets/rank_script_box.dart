import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:get/get.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/script/model/script.dart';

class RankScriptBox extends StatelessWidget {
  final Script script;

  const RankScriptBox({super.key, required this.script});

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
              Colors.black.withOpacity(0.4),
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
              horizontal: Sizes.size12,
              vertical: Sizes.size16,
            ),
            child: Padding(
              padding: const EdgeInsets.symmetric(
                vertical: Sizes.size8,
                horizontal: Sizes.size10,
              ),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Row(
                        children: [
                          CircleAvatar(
                            backgroundColor: Colors.white.withOpacity(0.7),
                            child: FaIcon(
                              FontAwesomeIcons.solidUser,
                              color: Theme.of(context).primaryColorDark,
                              size: Sizes.size24,
                            ),
                          ),
                          Gaps.h12,
                          Text(
                            script.nickname != null
                                ? script.nickname!.trim().replaceAll('"', '')
                                : "",
                            maxLines: 2,
                            style: TextStyle(
                              color: Colors.white.withOpacity(0.8),
                              fontSize: Sizes.size18,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ],
                      ),
                      Row(
                        children: [
                          const FaIcon(
                            FontAwesomeIcons.heart,
                            color: Colors.red,
                            size: Sizes.size28,
                          ),
                          Gaps.h4,
                          Text(
                            '${script.heartCount}',
                            style: TextStyle(
                              color: Colors.white.withOpacity(0.8),
                              fontSize: Sizes.size18,
                              fontWeight: FontWeight.bold,
                            ),
                          )
                        ],
                      ),
                    ],
                  ),
                  Text(
                    script.name!.trim().replaceAll('"', ''),
                    maxLines: 5,
                    overflow: TextOverflow.ellipsis,
                    style: const TextStyle(
                      color: Colors.white,
                      fontSize: Sizes.size24,
                      fontWeight: FontWeight.w500,
                    ),
                  )
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
