import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';
import 'package:mallook/feature/onboarding/api/onboarding_api_service.dart';
import 'package:mallook/feature/onboarding/model/keyword.dart';
import 'package:mallook/feature/onboarding/widgets/keyword_button.dart';
import 'package:mallook/feature/script/api/script_service.dart';
import 'package:mallook/feature/sign_up/widgets/form_button.dart';
import 'package:mallook/global/mallook_snackbar.dart';
import 'package:mallook/global/widget/custom_circular_wait_widget.dart';

class InterestsScreen extends StatefulWidget {
  const InterestsScreen({super.key});

  @override
  State<InterestsScreen> createState() => _InterestsScreenState();
}

class _InterestsScreenState extends State<InterestsScreen> {
  final ScrollController _scrollController = ScrollController();
  bool _showTitle = false;
  bool _isAvailable = false;
  Future<List<Keyword>> keywords = OnboardingApiService.getOnboardingKeywords();
  final Set<Keyword> _selectedKeywords = {};

  void _onScroll() {
    if (_scrollController.offset >= 120) {
      if (_showTitle) return;
      setState(() {
        _showTitle = true;
      });
    } else {
      setState(() {
        _showTitle = false;
      });
    }
  }

  @override
  void initState() {
    super.initState();
    _scrollController.addListener(_onScroll);
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  Future<bool> _createScriptByKeyword() async {
    List<String> selected = [];
    for (var keyword in _selectedKeywords) {
      selected.add(keyword.name!);
    }
    var data = <String, dynamic>{
      "keywordsList": selected,
    };
    var result = await ScriptService.createScriptByKeywords(data: data);

    if (result.contains("생성")) {
      return true;
    }

    return false;
  }

  void _onNextTap() {
    _createScriptByKeyword().then((value) {
      ScaffoldMessenger.of(context).showSnackBar(
        mallookSnackBar(title: value ? "분석 완료" : "다시 시도해 주세요."),
      );
      if (value) {
        Navigator.pushAndRemoveUntil(
          context,
          MaterialPageRoute(
            builder: (context) => const MainNavigationScreen(),
          ),
          (route) => false,
        );
      }
    });
  }

  void _addInterest(Keyword keyword) {
    setState(() {
      _selectedKeywords.add(keyword);
      _isAvailable = true;
    });
  }

  void _removeInterest(Keyword keyword) {
    setState(() {
      _selectedKeywords.remove(keyword);
      if (_selectedKeywords.isEmpty) _isAvailable = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        surfaceTintColor: Colors.white,
        title: AnimatedOpacity(
          opacity: _showTitle ? 1.0 : 0.0,
          duration: const Duration(milliseconds: 300),
          child: Text(
            '당신의 옷 취향은?',
            style: TextStyle(
              color: Theme.of(context).primaryColorDark,
            ),
          ),
        ),
      ),
      body: Scrollbar(
        controller: _scrollController,
        child: SingleChildScrollView(
          controller: _scrollController,
          child: Padding(
            padding: const EdgeInsets.only(
              left: Sizes.size24,
              right: Sizes.size24,
              bottom: Sizes.size16,
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Gaps.v24,
                Text(
                  '당신의 옷 스타일은?',
                  style: TextStyle(
                    color: Theme.of(context).primaryColor,
                    fontSize: Sizes.size40,
                    fontWeight: FontWeight.bold,
                    height: 1.2,
                  ),
                ),
                Gaps.v16,
                Text(
                  '당신의 스타일로 추천 받아봐요!',
                  style: TextStyle(
                    color: Colors.grey.shade600,
                    fontSize: Sizes.size20,
                  ),
                ),
                Gaps.v44,
                FutureBuilder(
                  future: keywords,
                  builder: (context, snapshot) {
                    if (snapshot.hasData) {
                      return Wrap(
                        spacing: 15,
                        runSpacing: 15,
                        children: [
                          for (var keyword in snapshot.data ?? [])
                            KeywordButton(
                              keyword: keyword,
                              selected: _selectedKeywords,
                              add: _addInterest,
                              remove: _removeInterest,
                            )
                        ],
                      );
                    }
                    return CustomCircularWaitWidget();
                  },
                ),
              ],
            ),
          ),
        ),
      ),
      bottomNavigationBar: BottomAppBar(
        height: Sizes.size96 + Sizes.size18,
        color: Colors.white,
        shadowColor: Colors.white,
        surfaceTintColor: Colors.white,
        elevation: 5,
        child: Padding(
          padding: const EdgeInsets.only(
            top: Sizes.size6,
            left: Sizes.size24,
            right: Sizes.size24,
            bottom: Sizes.size20,
          ),
          child: Center(
            child: FormButton(
              text: !_isAvailable ? "선택해주세요" : "다음",
              disabled: !_isAvailable,
              onTap: _onNextTap,
            ),
          ),
        ),
      ),
    );
  }
}
