import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/api/home_api_service.dart';
import 'package:mallook/feature/home/widgets/rank_script_box.dart';
import 'package:mallook/feature/script/model/script.dart';
import 'package:mallook/feature/script/script_screen.dart';
import 'package:mallook/global/widget/custom_circular_wait_widget.dart';

class HomeOthersScreen extends StatefulWidget {
  const HomeOthersScreen({super.key});

  @override
  State<HomeOthersScreen> createState() => _HomeOthersScreenState();
}

class _HomeOthersScreenState extends State<HomeOthersScreen> {
  final ScrollController _scrollController = ScrollController();
  final List<Script> _scripts = [];
  int _scriptPage = 0;
  bool _isScriptLoading = false;

  @override
  void initState() {
    super.initState();

    _loadMoreScripts();
    _scrollController.addListener(() {
      if (_scrollController.offset >=
              (_scrollController.position.maxScrollExtent * 0.9) &&
          !_scrollController.position.outOfRange) {
        _loadMoreScripts();
      }
    });
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  void _loadMoreScripts() async {
    if (!_isScriptLoading) {
      if (mounted) {
        setState(() {
          _isScriptLoading = true;
        });
      }

      try {
        List<Script> loadedScripts =
            await HomeApiService.getRankingScripts(_scriptPage);
        if (mounted) {
          setState(() {
            _scripts.addAll(loadedScripts); // 기존 _products List에 새로운 제품 추가
            _scriptPage++;
          });
        }
      } finally {
        _isScriptLoading = false;
      }
    }
  }

  void _moveToScriptDetail(Script script) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => ScriptScreen(
          script: script,
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Padding(
        padding: const EdgeInsets.symmetric(
          horizontal: Sizes.size20,
          vertical: Sizes.size10,
        ),
        child: ListView.separated(
          controller: _scrollController,
          itemCount: _scripts.length + 1,
          separatorBuilder: (context, index) => const SizedBox(
            height: Sizes.size10,
          ),
          itemBuilder: (context, index) {
            if (index < _scripts.length) {
              return GestureDetector(
                onTap: () => _moveToScriptDetail(_scripts[index]),
                child: RankScriptBox(
                  script: _scripts[index],
                ),
              );
            } else {
              return CustomCircularWaitWidget();
            }
          },
        ),
      ),
    );
  }
}
