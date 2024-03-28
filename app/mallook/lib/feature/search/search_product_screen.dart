import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/api/home_api_service.dart';
import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/home/widgets/custom_circular_wait_widget.dart';
import 'package:mallook/feature/home/widgets/product_widget.dart';
import 'package:mallook/feature/search/api/search_api_service.dart';
import 'package:mallook/feature/search/models/hot_keyword.dart';
import 'package:mallook/feature/search/widget/hot_keyword_grid_widget.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';

class SearchProductScreen extends StatefulWidget {
  final String searchWord;
  final Set<String> searchKeywords;

  const SearchProductScreen(
      {super.key, required this.searchWord, required this.searchKeywords});

  @override
  State<SearchProductScreen> createState() => _SearchProductScreenState();
}

class _SearchProductScreenState extends State<SearchProductScreen>
    with SingleTickerProviderStateMixin {
  final TextEditingController _textEditingController = TextEditingController();
  final ScrollController _appBarScrollController = ScrollController();
  late final AnimationController _animationController = AnimationController(
    vsync: this,
    duration: const Duration(
      milliseconds: 200,
    ),
  );
  late final Animation<double> _arrowAnimation = Tween(
    begin: 0.0,
    end: 0.5,
  ).animate(_animationController);
  final ScrollController _scrollController = ScrollController();
  final Future<List<HotKeyword>> _hotKeywords =
      SearchApiService.getHotKeywords();
  final List<Product> _products = [];
  int _productPage = 0;
  bool _isProductLoading = false;
  late Set<String> _searchKeywords;
  late String _searchWord;
  bool _isHotKeywordVisible = false;

  @override
  void initState() {
    setState(() {
      _textEditingController.text = _searchWord = widget.searchWord;
      _searchKeywords = widget.searchKeywords.toSet();
    });
    super.initState();
    _textEditingController.addListener(
      () {
        setState(() {
          _searchWord = _textEditingController.text;
        });
      },
    );

    _scrollController.addListener(() {
      if (_scrollController.offset >=
              (_scrollController.position.maxScrollExtent * 0.9) &&
          !_scrollController.position.outOfRange) {
        _loadMoreProducts();
      }
    });

    _loadMoreProducts();
  }

  void _loadMoreProducts() async {
    if (!_isProductLoading) {
      if (mounted) {
        setState(() {
          _isProductLoading = true;
        });
      }
      var loadedProducts = await HomeApiService.getProducts(_productPage);
      if (mounted) {
        setState(() {
          _products.addAll(loadedProducts); // 기존 _products List에 새로운 제품 추가
          _productPage++;
          _isProductLoading = false;
        });
      }
    }
  }

  void _onSearchSubmitted() {
    if (_searchWord.isNotEmpty || _searchKeywords.isNotEmpty) {
      Navigator.of(context).push(
        MaterialPageRoute(
          builder: (context) => SearchProductScreen(
            searchWord: _searchWord,
            searchKeywords: _searchKeywords,
          ),
        ),
      );
    }
  }

  void _onClearTap() {
    _textEditingController.clear();
  }

  void _onStopSearch() {
    FocusScope.of(context).unfocus();
  }

  void addSearchKeyword(String keyword) {
    setState(() {
      _appBarScrollController.animateTo(
        _appBarScrollController.position.maxScrollExtent,
        duration: const Duration(milliseconds: 300),
        curve: Curves.ease,
      );

      _searchKeywords.add(keyword);
    });
  }

  void _removeSearchKeyword(String keyword) {
    setState(() {
      _searchKeywords.remove(keyword);
    });
  }

  @override
  void dispose() {
    _textEditingController.dispose();
    _appBarScrollController.dispose();
    _animationController.dispose();
    _scrollController.dispose();
    super.dispose();
  }

  void _toggleHotKeyword() async {
    if (_animationController.isCompleted) {
      setState(() {
        _isHotKeywordVisible = false;
      });
      await _animationController.reverse();
    } else {
      setState(() {
        _isHotKeywordVisible = true;
      });
      await _animationController.forward();
    }
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: _onStopSearch,
      child: Scaffold(
        backgroundColor: Colors.white,
        resizeToAvoidBottomInset: false,
        appBar: AppBar(
          backgroundColor: Colors.white,
          surfaceTintColor: Colors.white,
          automaticallyImplyLeading: false,
          elevation: 1,
          shadowColor: Colors.grey.shade400,
          title: TextField(
            textInputAction: TextInputAction.search,
            onEditingComplete: _onSearchSubmitted,
            controller: _textEditingController,
            decoration: InputDecoration(
              hintText: "내 스타일 옷 검색...",
              border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(
                  Sizes.size8,
                ),
                borderSide: BorderSide.none,
              ),
              filled: true,
              fillColor: Colors.grey.shade200,
              contentPadding: EdgeInsets.zero,
              prefixIcon: const Row(
                mainAxisSize: MainAxisSize.min,
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Padding(
                    padding: EdgeInsets.only(left: Sizes.size16),
                    child: FaIcon(
                      FontAwesomeIcons.magnifyingGlass,
                      color: Colors.black,
                      size: Sizes.size18,
                    ),
                  ),
                ],
              ),
              suffixIcon: Row(
                mainAxisSize: MainAxisSize.min,
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  if (_searchWord.isNotEmpty)
                    Padding(
                      padding: const EdgeInsets.only(right: Sizes.size10),
                      child: GestureDetector(
                        onTap: _onClearTap,
                        child: FaIcon(
                          FontAwesomeIcons.solidCircleXmark,
                          color: Colors.grey.shade600,
                        ),
                      ),
                    ),
                ],
              ),
            ),
          ),
          actions: [
            InkWell(
              borderRadius: BorderRadius.circular(
                Sizes.size20,
              ),
              onTap: _onSearchSubmitted,
              child: Text(
                '검색',
                style: TextStyle(
                  color: Theme.of(context).primaryColorDark,
                  fontWeight: FontWeight.bold,
                  fontSize: Sizes.size18,
                ),
              ),
            ),
            Gaps.h10,
            const CartIconButton(),
            Gaps.h24,
          ],
        ),
        body: SingleChildScrollView(
          controller: _scrollController,
          child: Column(
            children: [
              AnimatedContainer(
                height: _searchKeywords.isNotEmpty ? Sizes.size44 : 0,
                color: Theme.of(context).primaryColorLight,
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size6,
                  horizontal: Sizes.size20,
                ),
                duration: const Duration(
                  milliseconds: 300,
                ),
                child: ListView.separated(
                  controller: _appBarScrollController,
                  scrollDirection: Axis.horizontal,
                  itemBuilder: (context, index) => Container(
                    padding: const EdgeInsets.symmetric(
                      vertical: Sizes.size4,
                      horizontal: Sizes.size10,
                    ),
                    decoration: BoxDecoration(
                      color: Theme.of(context).primaryColorDark,
                      borderRadius: BorderRadius.circular(
                        Sizes.size20,
                      ),
                    ),
                    child: Row(
                      children: [
                        Text(
                          '# ${_searchKeywords.elementAt(index)}',
                          style: const TextStyle(
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            fontSize: Sizes.size14,
                          ),
                        ),
                        Gaps.h4,
                        GestureDetector(
                          onTap: () => _removeSearchKeyword(
                              _searchKeywords.elementAt(index)),
                          child: Container(
                            padding: const EdgeInsets.all(
                              Sizes.size2,
                            ),
                            decoration: BoxDecoration(
                              color: Theme.of(context).primaryColorLight,
                              borderRadius: BorderRadius.circular(
                                Sizes.size10,
                              ),
                            ),
                            child: FaIcon(
                              FontAwesomeIcons.xmark,
                              color: Colors.grey.shade700,
                              size: Sizes.size14,
                            ),
                          ),
                        )
                      ],
                    ),
                  ),
                  separatorBuilder: (context, index) => Gaps.h10,
                  itemCount: _searchKeywords.length,
                ),
              ),
              Container(
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size18,
                  horizontal: Sizes.size18,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      children: [
                        FaIcon(
                          FontAwesomeIcons.fire,
                          color: Colors.redAccent,
                          size: Sizes.size24,
                          shadows: [
                            BoxShadow(
                              color: Colors.orange.shade300,
                              blurRadius: 5,
                              offset: const Offset(Sizes.size2, Sizes.size2),
                            )
                          ],
                        ),
                        Gaps.h12,
                        const Text(
                          '핫한 키워드',
                          style: TextStyle(
                            color: Colors.black,
                            fontSize: Sizes.size18,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        Gaps.h10,
                        GestureDetector(
                          onTap: _toggleHotKeyword,
                          child: RotationTransition(
                            turns: _arrowAnimation,
                            child: const FaIcon(
                              FontAwesomeIcons.angleUp,
                            ),
                          ),
                        ),
                      ],
                    ),
                    Gaps.v6,
                    const Divider(),
                    AnimatedContainer(
                      height: _isHotKeywordVisible ? 230 : 0,
                      duration: const Duration(
                        milliseconds: 300,
                      ),
                      child: FutureBuilder(
                        future: _hotKeywords,
                        builder: (context, snapshot) {
                          if (snapshot.hasData) {
                            return HotKeywordGridWidget(
                              hotKeywords: snapshot.data!,
                              addKeyword: addSearchKeyword,
                            );
                          }
                          return const Center(
                            child: CustomCircularWaitWidget(),
                          );
                        },
                      ),
                    ),
                    Visibility(
                      visible: _isHotKeywordVisible,
                      child: const Divider(),
                    ),
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size12,
                  horizontal: Sizes.size20,
                ),
                child: GridView.builder(
                  shrinkWrap: true,
                  physics: const NeverScrollableScrollPhysics(),
                  gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                    crossAxisCount: 2,
                    crossAxisSpacing: Sizes.size16,
                    mainAxisSpacing: Sizes.size10,
                    childAspectRatio: 0.73,
                  ),
                  itemBuilder: (context, index) =>
                      ProductWidget(product: _products[index]),
                  itemCount: _products.length, // itemCount 수정
                ),
              ),
              if (_isProductLoading) const CustomCircularWaitWidget(),
            ],
          ),
        ),
      ),
    );
  }
}
