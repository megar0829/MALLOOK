import 'dart:math';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/config/global_functions.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';
import 'package:mallook/feature/profile/profile_screen.dart';
import 'package:mallook/feature/script/api/script_service.dart';
import 'package:mallook/feature/worldcup/api/worldcup_api_service.dart';
import 'package:mallook/feature/worldcup/model/worldcup_cody.dart';
import 'package:mallook/global/mallook_snackbar.dart';
import 'package:mallook/global/widget/custom_circular_wait_widget.dart';

class WorldcupScreen extends StatefulWidget {
  const WorldcupScreen({super.key});

  @override
  State<WorldcupScreen> createState() => _WorldcupScreenState();
}

class _WorldcupScreenState extends State<WorldcupScreen> {
  Future<List<WorldcupCody>> data = WorldcupApiService.getWorldCupCodies();
  List<WorldcupCody> _codies = [];
  List<WorldcupCody> _selected = [];
  int _itemCount = 8;
  int _index = 0;

  @override
  void initState() {
    super.initState();

    _codies.shuffle(Random());
  }

  @override
  void dispose() {
    super.dispose();
  }

  void _onSubmit() async {
    var winner = _codies.first;
    var data = <String, dynamic>{
      "keywordsList": winner.keywordList ?? [],
    };

    var result = await ScriptService.createScriptByKeywords(
      data: data,
    );

    if (result.contains("생성")) {
      ScaffoldMessenger.of(context).showSnackBar(
        mallookSnackBar(title: '스크립트 생성 완료!'),
      );
    }

    moveToNavigationScreen();
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
      return '결승전 입니다.';
    }
    return '$_itemCount 강 입니다.';
  }

  Widget _bottomWidget() {
    if (_itemCount == 1) {
      return ElevatedButton(
        style: ElevatedButton.styleFrom(
          backgroundColor: Colors.blue,
        ),
        onPressed: _onSubmit,
        child: const Text(
          '저장하기',
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.bold,
            fontSize: Sizes.size18,
          ),
        ),
      );
    } else {
      return Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          FaIcon(
            FontAwesomeIcons.bullhorn,
            color: Theme.of(context).primaryColorDark,
            size: Sizes.size18,
          ),
          Gaps.h10,
          Text(
            getTitle(),
            style: const TextStyle(
              color: Colors.black,
              fontWeight: FontWeight.bold,
              fontSize: Sizes.size20,
            ),
          ),
        ],
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        centerTitle: true,
        title: Row(
          mainAxisSize: MainAxisSize.min,
          children: [
            Image.asset(
              "assets/images/app_logo/land_mallook.png",
              width: Sizes.size32,
            ),
            Gaps.h12,
            const Text(
              "내 옷 취향 찾기",
              style: TextStyle(
                color: Colors.black,
                fontSize: Sizes.size18,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
      body: FutureBuilder<List<WorldcupCody>>(
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            _codies.addAll(snapshot.data!);
            return Center(
              child: Padding(
                padding: const EdgeInsets.symmetric(
                  horizontal: Sizes.size24,
                ),
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    if (_itemCount >= 2)
                      Column(
                        children: [
                          Row(
                            mainAxisAlignment: MainAxisAlignment.start,
                            children: [
                              GestureDetector(
                                onTap: _selectLeft,
                                child: Container(
                                  width: 220,
                                  height: 250,
                                  clipBehavior: Clip.hardEdge,
                                  decoration: BoxDecoration(
                                    border: Border.all(
                                      color: Colors.grey.shade300,
                                      width: Sizes.size1,
                                    ),
                                    borderRadius: BorderRadius.circular(
                                      Sizes.size16,
                                    ),
                                  ),
                                  child: Image.network(
                                    _codies[_index].imageUrl!,
                                    fit: BoxFit.cover,
                                  ),
                                ),
                              ),
                              Gaps.h10,
                              Expanded(
                                child: Column(
                                  mainAxisSize: MainAxisSize.min,
                                  children: [
                                    SizedBox(
                                      width: 220,
                                      child: Text(
                                        _codies[_index].name!,
                                        maxLines: 3,
                                        textAlign: TextAlign.center,
                                        style: const TextStyle(
                                          color: Colors.black,
                                          fontWeight: FontWeight.bold,
                                          fontSize: Sizes.size16,
                                        ),
                                      ),
                                    ),
                                    Gaps.v12,
                                    Wrap(
                                      spacing: Sizes.size4,
                                      runSpacing: Sizes.size4,
                                      children: [
                                        for (var item
                                            in _codies[_index].keywordList ??
                                                [])
                                          Text(
                                            '#$item',
                                            style: TextStyle(
                                              color: Colors.grey.shade600,
                                              fontWeight: FontWeight.bold,
                                              fontSize: Sizes.size12,
                                            ),
                                          ),
                                      ],
                                    ),
                                  ],
                                ),
                              ),
                            ],
                          ),
                          const Padding(
                            padding: EdgeInsets.symmetric(
                              vertical: Sizes.size6,
                            ),
                            child: Center(
                              child: Text(
                                'Vs',
                                style: TextStyle(
                                  color: Colors.blueGrey,
                                  fontWeight: FontWeight.bold,
                                  fontSize: Sizes.size20,
                                ),
                              ),
                            ),
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.end,
                            children: [
                              Expanded(
                                child: Column(
                                  mainAxisSize: MainAxisSize.min,
                                  children: [
                                    SizedBox(
                                      width: 220,
                                      child: Text(
                                        _codies[_index + 1].name!,
                                        maxLines: 3,
                                        textAlign: TextAlign.center,
                                        style: const TextStyle(
                                          color: Colors.black,
                                          fontWeight: FontWeight.bold,
                                          fontSize: Sizes.size16,
                                        ),
                                      ),
                                    ),
                                    Gaps.v12,
                                    Wrap(
                                      spacing: Sizes.size4,
                                      runSpacing: Sizes.size4,
                                      children: [
                                        for (var item in _codies[_index + 1]
                                                .keywordList ??
                                            [])
                                          Text(
                                            '#$item',
                                            style: TextStyle(
                                              color: Colors.grey.shade600,
                                              fontWeight: FontWeight.bold,
                                              fontSize: Sizes.size12,
                                            ),
                                          ),
                                      ],
                                    ),
                                  ],
                                ),
                              ),
                              Gaps.h10,
                              GestureDetector(
                                onTap: _selectRight,
                                child: Container(
                                  width: 220,
                                  height: 250,
                                  clipBehavior: Clip.hardEdge,
                                  decoration: BoxDecoration(
                                    border: Border.all(
                                      color: Colors.grey.shade300,
                                      width: Sizes.size1,
                                    ),
                                    borderRadius: BorderRadius.circular(
                                      Sizes.size16,
                                    ),
                                  ),
                                  child: Image.network(
                                    _codies[_index + 1].imageUrl!,
                                    fit: BoxFit.cover,
                                  ),
                                ),
                              ),
                            ],
                          ),
                        ],
                      ),
                    if (_itemCount == 1)
                      Column(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          const Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              FaIcon(
                                FontAwesomeIcons.rocket,
                                color: Colors.blueAccent,
                                size: Sizes.size24,
                              ),
                              Gaps.h10,
                              Text(
                                '우승 코디',
                                style: TextStyle(
                                  color: Colors.lightBlue,
                                  fontWeight: FontWeight.bold,
                                  fontSize: Sizes.size20,
                                ),
                              ),
                            ],
                          ),
                          Gaps.v10,
                          GestureDetector(
                            onTap: _selectLeft,
                            child: Container(
                              width: 280,
                              height: 330,
                              clipBehavior: Clip.hardEdge,
                              decoration: BoxDecoration(
                                border: Border.all(
                                  color: Colors.grey.shade300,
                                  width: Sizes.size1,
                                ),
                                borderRadius: BorderRadius.circular(
                                  Sizes.size16,
                                ),
                              ),
                              child: Image.network(
                                _codies[_index].imageUrl!,
                                fit: BoxFit.cover,
                              ),
                            ),
                          ),
                          Gaps.v10,
                          SizedBox(
                            width: 220,
                            child: Text(
                              _codies[_index].name!,
                              textAlign: TextAlign.center,
                              maxLines: 3,
                              style: const TextStyle(
                                color: Colors.black,
                                fontWeight: FontWeight.bold,
                                fontSize: Sizes.size20,
                              ),
                            ),
                          ),
                          Wrap(
                            spacing: Sizes.size4,
                            runSpacing: Sizes.size4,
                            children: [
                              for (var item
                                  in _codies[_index].keywordList ?? [])
                                Text(
                                  '#$item',
                                  style: TextStyle(
                                    color: Colors.grey.shade600,
                                    fontWeight: FontWeight.bold,
                                    fontSize: Sizes.size12,
                                  ),
                                ),
                            ],
                          ),
                        ],
                      ),
                  ],
                ),
              ),
            );
          }
          return CustomCircularWaitWidget();
        },
        future: data,
      ),
      bottomNavigationBar: BottomAppBar(
        color: Colors.white,
        surfaceTintColor: Colors.white,
        child: _bottomWidget(),
      ),
    );
  }
}
