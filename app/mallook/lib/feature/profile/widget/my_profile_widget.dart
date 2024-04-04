import 'package:flutter/cupertino.dart';
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
    required this.nickname,
    required this.nicknameTag,
    required this.level,
    required this.exp,
    required this.expRange,
  });

  final String nickname;
  final String nicknameTag;
  final int level;
  final int exp;
  final List<int> expRange;

  double _getPercentage() {
    int range = expRange[1] - expRange[0];
    int ex = exp - expRange[0];
    return ex * 100 / range;
  }

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

  void _showLevelDiscountGuide(BuildContext context) async {
    var size = MediaQuery.of(context).size;
    await showDialog(
      context: context,
      builder: (BuildContext context) => Center(
        child: Container(
          width: size.width * 2 / 3,
          height: size.height / 2,
          padding: const EdgeInsets.symmetric(
            vertical: Sizes.size16,
            horizontal: Sizes.size20,
          ),
          decoration: BoxDecoration(
            color: Colors.grey.shade100,
            border: Border.all(
              color: Colors.grey.shade400,
              width: Sizes.size1,
            ),
            borderRadius: BorderRadius.circular(
              Sizes.size20,
            ),
          ),
          child: Center(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                const Text(
                  '등급 혜택',
                  style: TextStyle(
                    color: Colors.black,
                    fontSize: Sizes.size18,
                    fontWeight: FontWeight.bold,
                  ),
                ),
                Gaps.v20,
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    const Text(
                      '등급',
                      style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: Sizes.size16,
                      ),
                    ),
                    Text(
                      '할인율',
                      style: TextStyle(
                        color: Colors.grey.shade800,
                        fontWeight: FontWeight.bold,
                        fontSize: Sizes.size16,
                      ),
                    ),
                  ],
                ),
                const Divider(),
                for (int i = 1; i <= 7; i++)
                  Padding(
                    padding: const EdgeInsets.symmetric(
                      vertical: Sizes.size10,
                    ),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          'LEVEL$i',
                          style: const TextStyle(
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            fontSize: Sizes.size16,
                          ),
                        ),
                        Text(
                          '${getLevelDiscountRatio(i)}% 할인',
                          style: TextStyle(
                            color: Colors.grey.shade700,
                            fontWeight: FontWeight.bold,
                            fontSize: Sizes.size16,
                          ),
                        ),
                      ],
                    ),
                  ),
              ],
            ),
          ),
        ),
      ),
    );
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
                decoration: const BoxDecoration(
                  color: Colors.white,
                  shape: BoxShape.circle,
                ),
                child: Image.asset(
                  getLevelImage(level),
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
                        Text(
                          nickname,
                          style: const TextStyle(
                            color: Colors.black,
                            fontSize: Sizes.size24,
                            fontWeight: FontWeight.bold,
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
                      '#$nicknameTag',
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
                  percent: _getPercentage(),
                  center: Text(
                    '${_getPercentage().toInt()}%',
                    style: const TextStyle(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  linearStrokeCap: LinearStrokeCap.roundAll,
                  progressColor: Theme.of(context).primaryColorDark,
                ),
                GestureDetector(
                  onTap: () => _showLevelDiscountGuide(context),
                  child: Row(
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
                ),
              ],
            ),
          )
        ],
      ),
    );
  }
}
