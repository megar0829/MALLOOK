import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/product/model/product.dart';
import 'package:mallook/feature/product/product_screen.dart';
import 'package:mallook/feature/style/api/style_api_service.dart';
import 'package:mallook/feature/style/model/style.dart';
import 'package:mallook/feature/style/style_detail_screen.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';

class StyleScreen extends StatefulWidget {
  const StyleScreen({super.key});

  @override
  State<StyleScreen> createState() => _StyleScreenState();
}

class _StyleScreenState extends State<StyleScreen> {
  final ScrollController _scrollController = ScrollController();
  final List<Style> _styles = [];
  int _styleCursor = 9999999999999999;
  bool __isStyleLoading = false;

  @override
  void initState() {
    super.initState();

    _loadMoreStyles();
    _scrollController.addListener(() {
      if (_scrollController.offset >=
              (_scrollController.position.maxScrollExtent * 0.9) &&
          !_scrollController.position.outOfRange) {
        _loadMoreStyles();
      }
    });
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  void _loadMoreStyles() async {
    if (_styleCursor == 0) return;
    if (!__isStyleLoading) {
      if (mounted) {
        setState(() {
          __isStyleLoading = true;
        });

        try {
          var data = await StyleApiService.getPageStyle(cursor: _styleCursor);

          if (mounted) {
            _styles.addAll(data.content ?? []);
            if ((data.content ?? []).isNotEmpty) {
              _styleCursor = data.content!.last.id!;
            }
          }
        } finally {
          __isStyleLoading = false;
        }
      }
    }
  }

  void _moveToProductDetailScreen(Product product) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => ProductScreen(product: product),
      ),
    );
  }

  void _moveToStyleDetailScreen(int scriptId) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => StyleDetailScreen(
          scriptId: scriptId,
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        surfaceTintColor: Colors.white,
        elevation: 1,
        shadowColor: Colors.grey.shade400,
        centerTitle: true,
        leading: Image.asset(
          "assets/images/app_logo/land_after.png",
          width: Sizes.size52,
        ),
        title: const Text('몰룩북'),
        titleTextStyle: const TextStyle(
          color: Colors.black,
          fontWeight: FontWeight.bold,
          fontSize: Sizes.size18,
        ),
        actions: const [
          CartIconButton(),
          Gaps.h24,
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(
          vertical: Sizes.size12,
          horizontal: Sizes.size20,
        ),
        child: ListView.separated(
          itemCount: _styles.length,
          itemBuilder: (context, index) {
            return Container(
              padding: const EdgeInsets.symmetric(
                vertical: Sizes.size10,
                horizontal: Sizes.size12,
              ),
              decoration: BoxDecoration(
                color: const Color(0xfff0f2fd),
                borderRadius: BorderRadius.circular(
                  Sizes.size18,
                ),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Row(
                        children: [
                          FaIcon(
                            FontAwesomeIcons.user,
                            color: Colors.grey.shade600,
                          ),
                          Gaps.h8,
                          Text(
                            _styles[index].memberNickname ?? "",
                            style: const TextStyle(
                              color: Colors.black,
                              fontWeight: FontWeight.bold,
                              fontSize: Sizes.size16,
                            ),
                          ),
                        ],
                      ),
                      if (_styles[index].heartCount != null)
                        Row(
                          children: [
                            FaIcon(
                              FontAwesomeIcons.solidHeart,
                              color: Theme.of(context).primaryColorDark,
                            ),
                            Gaps.h10,
                            Text(
                              "${_styles[index].heartCount!}",
                              style: const TextStyle(
                                color: Colors.black,
                                fontSize: Sizes.size16,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        )
                    ],
                  ),
                  if (_styles[index].memberNickname != null) const Divider(),
                  GestureDetector(
                    onTap: () => _moveToStyleDetailScreen(_styles[index].id!),
                    child: Text(
                      _styles[index].name ?? "",
                      maxLines: 5,
                      style: const TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: Sizes.size12,
                      ),
                    ),
                  ),
                  Gaps.v10,
                  SizedBox(
                    height: 120,
                    child: GridView.builder(
                      scrollDirection: Axis.horizontal,
                      gridDelegate:
                          const SliverGridDelegateWithFixedCrossAxisCount(
                        crossAxisCount: 1,
                        mainAxisSpacing: Sizes.size10,
                        childAspectRatio: 10 / 9,
                      ),
                      itemCount:
                          (_styles[index].productsListDtoList ?? []).length + 1,
                      itemBuilder: (context, pageIndex) {
                        if (pageIndex ==
                            (_styles[index].productsListDtoList ?? []).length) {
                          return Center(
                            child: GestureDetector(
                              onTap: () =>
                                  _moveToStyleDetailScreen(_styles[index].id!),
                              child: Container(
                                decoration: const BoxDecoration(
                                  shape: BoxShape.circle,
                                  color: Colors.white,
                                ),
                                padding: const EdgeInsets.all(
                                  Sizes.size18,
                                ),
                                child: const Column(
                                  mainAxisSize: MainAxisSize.min,
                                  children: [
                                    FaIcon(
                                      FontAwesomeIcons.arrowRight,
                                    ),
                                    Gaps.v8,
                                    Text(
                                      "더보기",
                                      style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: Sizes.size12,
                                      ),
                                    ),
                                  ],
                                ),
                              ),
                            ),
                          );
                        }
                        var product = (_styles[index].productsListDtoList ??
                            [])[pageIndex];
                        return GestureDetector(
                          onTap: () => _moveToProductDetailScreen(
                            _styles[index].productsListDtoList![pageIndex],
                          ),
                          child: Image.network(
                            product.image!,
                            fit: BoxFit.cover,
                          ),
                        );
                      },
                    ),
                  ),
                ],
              ),
            );
          },
          separatorBuilder: (context, index) => Gaps.v12,
        ),
      ),
    );
  }
}
