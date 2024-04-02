import 'dart:math';

import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';

class TestItem {
  final int id;

  TestItem({required this.id});
}

class WorldcupScreen extends StatefulWidget {
  const WorldcupScreen({super.key});

  @override
  State<WorldcupScreen> createState() => _WorldcupScreenState();
}

class _WorldcupScreenState extends State<WorldcupScreen> {
  List<int> _codies = [];
  List<int> _selected = [];
  int _itemCount = 8;
  int _index = 0;
  int? winner;

  @override
  void initState() {
    super.initState();
    for (int i = 0; i < _itemCount; i++) {
      _codies.add(i);
    }
    _codies.shuffle(Random());
  }

  @override
  void dispose() {
    super.dispose();
  }

  void _selectLeft() {
    if (_itemCount == 1) return;
    _selected.add(_codies[_index]);
    // 다음 라운드

    if (_selected.length == (_itemCount ~/ 2)) {
      _codies.clear();
      _codies.addAll(_selected);
      _selected.clear();

      _codies.shuffle(Random());
      _index = 0;
      _itemCount ~/= 2;

      print('count: $_itemCount index: $_index');
      print('codies: $_codies');
    } else {
      _index += 2;
    }
    setState(() {});
  }

  void _selectRight() {
    if (_itemCount == 1) return;
    _selected.add(_codies[_index + 1]);

    if (_selected.length == (_itemCount ~/ 2)) {
      _codies.clear();
      _codies.addAll(_selected);
      _selected.clear();

      _codies.shuffle(Random());
      _index = 0;
      _itemCount ~/= 2;

      print('count: $_itemCount index: $_index');
      print('codies: $_codies');
    } else {
      _index += 2;
    }
    setState(() {});
  }

  String getTitle() {
    if (_itemCount == 1) {
      return "우승!!";
    }
    if (_itemCount == 2) {
      return '결승전 입니다.}';
    }
    return '$_itemCount 강 입니다.';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: const Text(
          "내 옷 취향 찾기",
          style: TextStyle(
            color: Colors.black,
            fontSize: Sizes.size18,
            fontWeight: FontWeight.bold,
          ),
        ),
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.symmetric(
            horizontal: Sizes.size24,
          ),
          child: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  FaIcon(
                    FontAwesomeIcons.bullhorn,
                    color: Theme.of(context).primaryColorDark,
                  ),
                  Gaps.h10,
                  Text(
                    getTitle(),
                    style: const TextStyle(
                      color: Colors.black,
                      fontWeight: FontWeight.bold,
                      fontSize: Sizes.size24,
                    ),
                  ),
                ],
              ),
              Gaps.v36,
              if (_itemCount >= 2)
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Column(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        const Text(
                          'A',
                          style: TextStyle(
                            color: Colors.red,
                            fontWeight: FontWeight.bold,
                            fontSize: Sizes.size20,
                          ),
                        ),
                        Gaps.v5,
                        GestureDetector(
                          onTap: _selectLeft,
                          child: Container(
                            width: 160,
                            height: 200,
                            decoration: BoxDecoration(
                              border: Border.all(
                                color: Colors.grey.shade300,
                                width: Sizes.size1,
                              ),
                              borderRadius: BorderRadius.circular(
                                Sizes.size16,
                              ),
                            ),
                            child: Center(
                              child: Text(
                                '${_codies[_index]}',
                                style: const TextStyle(
                                  color: Colors.red,
                                  fontSize: Sizes.size20,
                                ),
                              ),
                            ),
                          ),
                        ),
                        Gaps.v10,
                        const Text(
                          '월드컵 멘트',
                          style: TextStyle(
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            fontSize: Sizes.size16,
                          ),
                        )
                      ],
                    ),
                    Gaps.h16,
                    Column(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        const Text(
                          'B',
                          style: TextStyle(
                            color: Colors.blue,
                            fontWeight: FontWeight.bold,
                            fontSize: Sizes.size20,
                          ),
                        ),
                        Gaps.v5,
                        GestureDetector(
                          onTap: _selectRight,
                          child: Container(
                            width: 160,
                            height: 200,
                            decoration: BoxDecoration(
                              border: Border.all(
                                color: Colors.grey.shade300,
                                width: Sizes.size1,
                              ),
                              borderRadius: BorderRadius.circular(
                                Sizes.size16,
                              ),
                            ),
                            child: Center(
                              child: Text(
                                '${_codies[_index + 1]}',
                                style: const TextStyle(
                                  color: Colors.blue,
                                  fontSize: Sizes.size20,
                                ),
                              ),
                            ),
                          ),
                        ),
                        Gaps.v10,
                        const Text(
                          '월드컵 멘트',
                          style: TextStyle(
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            fontSize: Sizes.size16,
                          ),
                        )
                      ],
                    )
                  ],
                ),
              if (_itemCount == 1)
                Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    GestureDetector(
                      onTap: _selectLeft,
                      child: Container(
                        width: 280,
                        height: 350,
                        decoration: BoxDecoration(
                          border: Border.all(
                            color: Colors.grey.shade400,
                            width: Sizes.size1,
                          ),
                          borderRadius: BorderRadius.circular(
                            Sizes.size16,
                          ),
                        ),
                        child: Center(
                          child: Text(
                            '${_codies[_index]}',
                            style: const TextStyle(
                              color: Colors.red,
                              fontSize: Sizes.size20,
                            ),
                          ),
                        ),
                      ),
                    ),
                    Gaps.v10,
                    const Text(
                      '월드컵 멘트',
                      style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: Sizes.size16,
                      ),
                    )
                  ],
                ),
            ],
          ),
        ),
      ),
    );
  }
}
