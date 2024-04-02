import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/category/api/category_api_service.dart';
import 'package:mallook/feature/home/widgets/product_widget.dart';
import 'package:mallook/feature/product/model/product.dart';
import 'package:mallook/feature/product/model/product_cursor_response.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';
import 'package:mallook/global/widget/custom_circular_wait_widget.dart';

Map<String, List<String>> categorys = {
  "상의": ["전체", "긴팔티", "반팔티", "민소매", "후드티", "맨투맨", "니트/스웨터", "셔츠/블라우스", "기타"],
  "하의": ["전체", "데님", "면", "슬랙스", "트레이닝/조거팬츠", "스커트", "레깅스", "숏팬츠", "와이드", "기타"],
  "아우터": [
    "전체",
    "숏패딩/패딩조끼",
    "롱패딩",
    "숏코트",
    "롱코트",
    "라이더재킷",
    "블레이저",
    "무스탕",
    "재킷",
    "점퍼",
    "플리스",
    "가디건",
    "후드집업",
    "바람막이",
    "기타"
  ],
  "원피스": ["전체", "롱원피스", "미니원피스", "기타"],
  "가방": ["전체", "크로스백", "숄더백", "토트백", "클러치", "에코/캔버스 백", "백팩", "기타"],
  "신발": [
    "전체",
    "스니커즈",
    "러닝화/워킹화",
    "스포츠화",
    "구두",
    "힐/펌프스",
    "로퍼",
    "뮬/블로퍼",
    "플랫슈즈",
    "부츠",
    "샌들/슬리퍼",
    "기타"
  ],
  "모자": ["전체", "볼캡/야구모자", "스냅백", "비니", "버킷햇", "베레모", "페도라", "기타"]
};

Map<String, String> titleImage = {
  "상의": "assets/images/tops_shirt.jpg",
  "하의": "assets/images/bottoms_slacks.jpg",
  "아우터": "assets/images/outer_short-padding.jpg",
  "원피스": "assets/images/dresses_mini.jpg",
  "가방": "assets/images/bags_tote.jpg",
  "신발": "assets/images/shoes_running.jpg",
  "모자": "assets/images/hats_bucket-hat.jpg",
};

class CategoryScreen extends StatefulWidget {
  const CategoryScreen({super.key});

  @override
  State<CategoryScreen> createState() => _CategoryScreenState();
}

class _CategoryScreenState extends State<CategoryScreen> {
  final ScrollController _scrollController = ScrollController();
  final List<Product> _products = [];
  bool _isProductLoading = false;
  String _primary = categorys.keys.first;
  late String _secondary = "";
  String? _cursor = "";

  @override
  void initState() {
    super.initState();
    _secondary = categorys[_primary]!.first;

    _loadMoreProducts();
    _scrollController.addListener(() {
      if (_scrollController.offset >=
              (_scrollController.position.maxScrollExtent * 0.9) &&
          !_scrollController.position.outOfRange) {
        _loadMoreProducts();
      }
    });
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  void _loadMoreProducts() async {
    if (_cursor == null) return;
    if (!_isProductLoading) {
      if (mounted) {
        setState(() {
          _isProductLoading = true;
        });
      }

      print("================cursor================");
      print(_cursor ?? "XXXX");

      try {
        ProductCursorResponse loadedProducts =
            await CategoryApiService.getCategoryProducts(
          cursor: _cursor ?? '',
          primary: _primary,
          secondary: _secondary,
        );

        if (mounted) {
          setState(() {
            if (loadedProducts.content != null) {
              _products.addAll(loadedProducts.content!);
            }
            _cursor = loadedProducts.nextCursor;
            print("================next cursor================");
            print(_cursor ?? "XXXX");
          });
        }
      } finally {
        _isProductLoading = false;
      }
    }
  }

  void _onTapPrimaryCategory(int primaryIdx) async {
    _isProductLoading = false;
    _cursor = "";
    _primary = categorys.keys.toList()[primaryIdx];
    _secondary = categorys[_primary]!.first;
    _products.clear();
    _loadMoreProducts();
    setState(() {});
  }

  void _onTapSecondaryCategory(int secondaryIdx) async {
    _isProductLoading = false;
    _cursor = "";
    _secondary = categorys[_primary]![secondaryIdx];
    _products.clear();
    _loadMoreProducts();
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          automaticallyImplyLeading: false,
          backgroundColor: Colors.white,
          surfaceTintColor: Colors.white,
          elevation: 1,
          shadowColor: Colors.grey.shade400,
          centerTitle: true,
          title: Text(
            _secondary.isNotEmpty ? '$_primary · $_secondary' : _primary,
            style: const TextStyle(
              color: Colors.black,
              fontWeight: FontWeight.bold,
              fontSize: Sizes.size18,
            ),
          ),
          actions: const [
            CartIconButton(),
            Gaps.h24,
          ],
          bottom: PreferredSize(
              preferredSize: const Size.fromHeight(
                120,
              ),
              child: Padding(
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size10,
                  horizontal: Sizes.size28,
                ),
                child: Column(
                  children: [
                    SizedBox(
                      height: Sizes.size80,
                      child: ListView.separated(
                        scrollDirection: Axis.horizontal,
                        itemBuilder: (context, index) => GestureDetector(
                          onTap: () => _onTapPrimaryCategory(index),
                          child: Column(
                            children: [
                              Container(
                                height: Sizes.size60,
                                decoration: BoxDecoration(
                                  border: Border.all(
                                    color: Colors.grey.shade300,
                                    width: 1,
                                  ),
                                  shape: BoxShape.circle,
                                ),
                                child: Image.asset(titleImage[
                                    categorys.keys.toList()[index]]!),
                              ),
                              Text(
                                categorys.keys.toList()[index],
                                style: const TextStyle(
                                  color: Colors.black,
                                  fontWeight: FontWeight.bold,
                                  fontSize: Sizes.size14,
                                ),
                              ),
                            ],
                          ),
                        ),
                        separatorBuilder: (context, index) => Gaps.h8,
                        itemCount: categorys.length,
                      ),
                    ),
                    Gaps.v8,
                    SizedBox(
                      height: Sizes.size24,
                      child: ListView.separated(
                        scrollDirection: Axis.horizontal,
                        itemBuilder: (context, index) => GestureDetector(
                          onTap: () => _onTapSecondaryCategory(index),
                          child: Container(
                            height: Sizes.size20,
                            padding: const EdgeInsets.symmetric(
                              vertical: Sizes.size2,
                              horizontal: Sizes.size8,
                            ),
                            decoration: BoxDecoration(
                              color: Colors.grey.shade200,
                              borderRadius: BorderRadius.circular(
                                Sizes.size16,
                              ),
                            ),
                            child: Text(
                              categorys[_primary]!.toList()[index],
                              style: const TextStyle(
                                color: Colors.black,
                                fontSize: Sizes.size14,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                        ),
                        separatorBuilder: (context, index) => Gaps.h8,
                        itemCount: categorys[_primary]!.length,
                      ),
                    )
                  ],
                ),
              )),
        ),
        body: SingleChildScrollView(
          controller: _scrollController,
          padding: const EdgeInsets.symmetric(
            vertical: Sizes.size10,
            horizontal: Sizes.size20,
          ),
          child: Column(
            children: [
              GridView.builder(
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
              if (_isProductLoading) CustomCircularWaitWidget(),
            ],
          ),
        ),
      ),
    );
  }
}
