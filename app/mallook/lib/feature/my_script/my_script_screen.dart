import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/models/script.dart';
import 'package:mallook/feature/home/widgets/custom_circular_wait_widget.dart';
import 'package:mallook/feature/my_script/api/my_script_api_service.dart';
import 'package:mallook/feature/my_script/widget/my_script_list_box.dart';
import 'package:mallook/feature/script/script_screen.dart';

class MyScriptScreen extends StatefulWidget {
  const MyScriptScreen({super.key});

  @override
  State<MyScriptScreen> createState() => _MyScriptScreenState();
}

class _MyScriptScreenState extends State<MyScriptScreen> {
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
      var loadedScripts = await MyScriptApiService.getMyScripts(_scriptPage);

      if (mounted) {
        setState(() {
          _scripts.addAll(loadedScripts); // 기존 _products List에 새로운 제품 추가
          _scriptPage++;
          _isScriptLoading = false;
        });
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
      appBar: AppBar(
        backgroundColor: Colors.white,
        surfaceTintColor: Colors.white,
        elevation: 1,
        shadowColor: Colors.grey.shade600,
        title: const Text(
          "내 스크립트",
          style: TextStyle(
            color: Colors.black,
            fontSize: Sizes.size18,
            fontWeight: FontWeight.bold,
          ),
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(
          vertical: Sizes.size10,
          horizontal: Sizes.size20,
        ),
        child: ListView.separated(
          controller: _scrollController,
          separatorBuilder: (context, index) => Gaps.v10,
          itemCount: _scripts.length + 1,
          itemBuilder: (context, index) {
            if (index < _scripts.length) {
              return GestureDetector(
                onTap: () => _moveToScriptDetail(_scripts[index]),
                child: MyScriptListBox(script: _scripts[index]),
              );
            } else {
              return const CustomCircularWaitWidget();
            }
          },
        ),
      ),
    );
  }
}