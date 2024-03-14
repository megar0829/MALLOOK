import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/api/home_api_service.dart';
import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/home/models/script.dart';
import 'package:mallook/feature/home/widgets/rank_script_box.dart';
import 'package:mallook/feature/product/product_screen.dart';

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
      var loadedScripts = await HomeApiService.getRankingScripts(_scriptPage);
      if (mounted) {
        setState(() {
          _scripts.addAll(loadedScripts); // 기존 _products List에 새로운 제품 추가
          _scriptPage++;
          _isScriptLoading = false;
        });
      }
    }
  }

  void _onProductTap(Product product) async {
    await showModalBottomSheet(
      context: context,
      backgroundColor: Colors.transparent,
      isScrollControlled: true,
      builder: (context) => ProductScreen(
        product: product,
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
        ),
        child: Column(
          children: [
            Gaps.v10,
            Expanded(
              // ListView를 Expanded로 감싸주어 오류를 방지합니다.
              child: ListView.separated(
                controller: _scrollController,
                itemCount: _scripts.length + 1,
                // itemCount 수정하여 마지막에 로딩 인디케이터를 포함시킵니다.
                separatorBuilder: (context, index) => const SizedBox(
                  height: 10, // Sizes.size10 대신 직접 값을 넣어줬습니다.
                ),
                itemBuilder: (context, index) {
                  if (index < _scripts.length) {
                    // _scripts의 마지막 인덱스보다 작을 때만 MyScriptBox를 반환합니다.
                    return RankScriptBox(
                      script: _scripts[index],
                    );
                  } else {
                    // 마지막 인덱스일 때 로딩 인디케이터를 반환합니다.
                    return Padding(
                      padding: const EdgeInsets.symmetric(
                        vertical: 32, // Sizes.size32 대신 직접 값을 넣어줬습니다.
                      ),
                      child: Center(
                        child: Column(
                          children: [
                            CircularProgressIndicator(
                              color: Theme.of(context).primaryColorLight,
                            ),
                            const SizedBox(height: 10),
                            // Gaps.v10 대신 직접 값을 넣어줬습니다.
                            Text(
                              '쪼매 기다리쇼 금방 돼여',
                              style: TextStyle(
                                fontSize: 14, // Sizes.size14 대신 직접 값을 넣어줬습니다.
                                color: Theme.of(context).primaryColor,
                                fontWeight: FontWeight.bold,
                              ),
                            )
                          ],
                        ),
                      ),
                    );
                  }
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
