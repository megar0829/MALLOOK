import 'package:flutter/material.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/config/global_functions.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:percent_indicator/linear_percent_indicator.dart';

class MyProfileWidget extends StatelessWidget {
  const MyProfileWidget({
    super.key,
    required this.username,
    required this.hashcode,
    required this.level,
    required this.percentage,
  });

  final String username;
  final String hashcode;
  final String level;
  final double percentage;

  Future<void> _showConfirmationDialog(BuildContext context) async {
    return showDialog<void>(
      context: context,
      barrierDismissible: false,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text(
            '로그아웃',
            style: TextStyle(
              fontWeight: FontWeight.bold,
              fontSize: Sizes.size16,
              color: Colors.black,
            ),
          ),
          content: const Text(
            '정말 로그아웃 하시겠습니까?',
            style: TextStyle(
              fontWeight: FontWeight.bold,
              fontSize: Sizes.size14,
              color: Colors.black,
            ),
          ),
          actions: <Widget>[
            TextButton(
              onPressed: () => _onLogoutBtnPressed(context),
              child: Text(
                '예',
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: Sizes.size14,
                  color: Colors.grey.shade500,
                ),
              ),
            ),
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: const Text(
                '아니오',
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: Sizes.size14,
                  color: Colors.black,
                ),
              ),
            ),
          ],
        );
      },
    );
  }

  void _onLogoutBtnPressed(BuildContext context) async {
    const storage = FlutterSecureStorage();
    await storage.deleteAll();
    moveToLoginScreen();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(
        vertical: Sizes.size24,
        horizontal: Sizes.size16,
      ),
      decoration: BoxDecoration(
        color: Theme.of(context).primaryColor.withOpacity(0.2),
        borderRadius: BorderRadius.circular(
          Sizes.size20,
        ),
      ),
      child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              Container(
                width: Sizes.size64,
                clipBehavior: Clip.hardEdge,
                decoration: BoxDecoration(
                  color: Theme.of(context).primaryColor,
                  shape: BoxShape.circle,
                ),
                child: FadeInImage.assetNetwork(
                  placeholder: "assets/images/ssafy_logo.png",
                  image: "https://avatars.githubusercontent.com/u/86183856?v=4",
                  fit: BoxFit.fill,
                  filterQuality: FilterQuality.low,
                ),
              ),
              Gaps.h12,
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        RichText(
                          text: TextSpan(
                            children: [
                              TextSpan(
                                text: username,
                                style: const TextStyle(
                                  color: Colors.black,
                                  fontSize: Sizes.size24,
                                  fontWeight: FontWeight.bold,
                                ),
                              ),
                              TextSpan(
                                text: "님 안녕하세요!",
                                style: TextStyle(
                                  color: Colors.grey.shade700,
                                  fontSize: Sizes.size18,
                                  fontWeight: FontWeight.bold,
                                ),
                              ),
                            ],
                          ),
                        ),
                        GestureDetector(
                          onTap: () => _showConfirmationDialog(context),
                          child: const FaIcon(
                            FontAwesomeIcons.rightFromBracket,
                            color: Colors.black,
                            size: Sizes.size20,
                          ),
                        ),
                      ],
                    ),
                    Gaps.v4,
                    Text(
                      '#$hashcode',
                      style: TextStyle(
                        color: Theme.of(context).primaryColorDark,
                        fontSize: Sizes.size18,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
              ),
            ],
          ),
          Gaps.v6,
          Padding(
            padding: const EdgeInsets.symmetric(
              vertical: Sizes.size4,
              horizontal: Sizes.size10,
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text(
                  'Lv. $level',
                  style: const TextStyle(
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                    fontSize: Sizes.size16,
                  ),
                ),
                LinearPercentIndicator(
                  width: MediaQuery.of(context).size.width / 2,
                  animation: true,
                  lineHeight: Sizes.size20,
                  animationDuration: 3000,
                  percent: percentage.toDouble() / 100,
                  center: Text(
                    '${percentage.toInt()}%',
                    style: const TextStyle(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  linearStrokeCap: LinearStrokeCap.roundAll,
                  progressColor: Theme.of(context).primaryColorDark,
                ),
                Row(
                  children: [
                    Text(
                      '등급혜택',
                      style: TextStyle(
                        color: Colors.grey.shade400,
                        fontSize: Sizes.size14,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Gaps.h2,
                    FaIcon(
                      FontAwesomeIcons.angleRight,
                      color: Colors.grey.shade400,
                      size: Sizes.size14,
                    ),
                  ],
                ),
              ],
            ),
          )
        ],
      ),
    );
  }
}
