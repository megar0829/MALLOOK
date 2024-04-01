import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/product/api/product_api_service.dart';
import 'package:mallook/feature/product/model/product.dart';
import 'package:mallook/feature/product/model/product_detail.dart';
import 'package:mallook/feature/product/widget/order_sheet.dart';
import 'package:mallook/feature/product/widget/product_img_widget.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';
import 'package:mallook/global/widget/home_icon_button.dart';

class ProductScreen extends StatefulWidget {
  final Product product;

  const ProductScreen({
    super.key,
    required this.product,
  });

  @override
  State<ProductScreen> createState() => _ProductScreenState();
}

class _ProductScreenState extends State<ProductScreen>
    with SingleTickerProviderStateMixin {
  late Future<ProductDetail> _productDetail;
  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 2, vsync: this);
    _productDetail = ProductApiService.getProductDetail(widget.product.id!);
    print('hihih   $_productDetail');
  }

  @override
  void dispose() {
    super.dispose();
    _tabController.dispose();
  }

  void _onClosePressed() {
    Navigator.of(context).pop();
  }

  void _clickHeartIcon() {}

  void _showOrderBottomSheet(List<String> sizes, List<String> colors) async {
    await showModalBottomSheet(
      backgroundColor: Colors.transparent,
      isScrollControlled: true,
      context: context,
      builder: (context) => OrderSheet(
        product: widget.product,
        title: widget.product.name,
        sizes: sizes,
        colors: colors,
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.grey.shade50,
        surfaceTintColor: Colors.grey.shade50,
        elevation: 1,
        shadowColor: Colors.grey.shade300,
        actions: const [
          HomeIconButton(),
          Gaps.h10,
          CartIconButton(),
          Gaps.h24,
        ],
      ),
      body: FutureBuilder<ProductDetail>(
        future: _productDetail,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            var product = snapshot.data!;
            return NestedScrollView(
              headerSliverBuilder: (context, value) {
                return [
                  SliverToBoxAdapter(
                      child: Column(
                    children: [
                      ProductImgWidget(
                        images: [
                          product.image ??
                              "https://zooting-s3-bucket.s3.ap-northeast-2.amazonaws.com/ssafy_logo.png"
                        ],
                      ),
                      Padding(
                        padding: const EdgeInsets.symmetric(
                          vertical: Sizes.size10,
                          horizontal: Sizes.size24,
                        ),
                        child: Text(
                          product.name!,
                          maxLines: 5,
                          style: const TextStyle(
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            fontSize: Sizes.size18,
                          ),
                        ),
                      ),
                    ],
                  )),
                  SliverToBoxAdapter(
                    child: TabBar(
                      controller: _tabController,
                      labelStyle: const TextStyle(
                        fontSize: Sizes.size16,
                        fontWeight: FontWeight.bold,
                      ),
                      labelColor: Theme.of(context).primaryColorDark,
                      indicatorColor: Theme.of(context).primaryColorDark,
                      tabs: const [
                        Tab(
                          height: Sizes.size36,
                          text: "상세보기",
                        ),
                        Tab(
                          height: Sizes.size36,
                          text: "리뷰",
                        ),
                      ],
                    ),
                  ),
                ];
              },
              body: Padding(
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size10,
                  horizontal: Sizes.size20,
                ),
                child: TabBarView(
                  controller: _tabController,
                  children: <Widget>[
                    ListView.separated(
                      itemBuilder: (context, index) => Image.network(
                        product.detailImages![index],
                        filterQuality: FilterQuality.low,
                      ),
                      separatorBuilder: (context, index) => Gaps.v8,
                      itemCount: (product.detailImages ?? []).length,
                    ),
                    Column(
                      children: [
                        Padding(
                          padding: const EdgeInsets.symmetric(
                            horizontal: Sizes.size32,
                          ),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              if (product.review!.count != null)
                                Text(
                                  '리뷰 ${product.review!.count!}개',
                                  style: TextStyle(
                                    color: Theme.of(context).primaryColorDark,
                                    fontWeight: FontWeight.bold,
                                    fontSize: Sizes.size18,
                                  ),
                                ),
                              if (product.review!.averagePoint != null)
                                Text(
                                  '평점 ${product.review!.averagePoint!}점',
                                  style: TextStyle(
                                    color: Theme.of(context).primaryColorDark,
                                    fontWeight: FontWeight.bold,
                                    fontSize: Sizes.size18,
                                  ),
                                )
                            ],
                          ),
                        ),
                        Gaps.v12,
                        Expanded(
                          child: ListView.separated(
                            itemBuilder: (context, index) => Container(
                              padding: const EdgeInsets.symmetric(
                                vertical: Sizes.size10,
                                horizontal: Sizes.size14,
                              ),
                              decoration: BoxDecoration(
                                border: Border.all(
                                  color: Colors.grey.shade300,
                                  width: Sizes.size1,
                                ),
                                borderRadius: BorderRadius.circular(
                                  Sizes.size14,
                                ),
                              ),
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    product
                                        .review!.reviewList![index].createdAt!,
                                    style: TextStyle(
                                      color: Colors.grey.shade600,
                                      fontSize: Sizes.size12,
                                    ),
                                  ),
                                  const Divider(),
                                  Text(
                                    product
                                        .review!.reviewList![index].contents!,
                                    maxLines: 5,
                                    style: const TextStyle(
                                      color: Colors.black,
                                      fontSize: Sizes.size14,
                                    ),
                                  ),
                                  const Divider(),
                                  Row(
                                    mainAxisAlignment:
                                        MainAxisAlignment.spaceBetween,
                                    children: [
                                      if (product.review!.reviewList![index]
                                              .userSize!.height !=
                                          null)
                                        Text(
                                          "신장 ${product.review!.reviewList![index].userSize!.height}",
                                          style: const TextStyle(
                                            color: Colors.black87,
                                            fontWeight: FontWeight.bold,
                                            fontSize: Sizes.size12,
                                          ),
                                        ),
                                      if (product.review!.reviewList![index]
                                              .userSize!.weight !=
                                          null)
                                        Text(
                                          "체중 ${product.review!.reviewList![index].userSize!.weight}",
                                          style: const TextStyle(
                                            color: Colors.black87,
                                            fontWeight: FontWeight.bold,
                                            fontSize: Sizes.size12,
                                          ),
                                        ),
                                    ],
                                  )
                                ],
                              ),
                            ),
                            separatorBuilder: (context, index) => Gaps.v8,
                            itemCount:
                                (product.review?.reviewList ?? []).length,
                          ),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
            );
            return ListView.builder(
              itemBuilder: (context, index) {
                if (index == 0) {
                  return ProductImgWidget(
                    images: [
                      product.image ??
                          "https://zooting-s3-bucket.s3.ap-northeast-2.amazonaws.com/ssafy_logo.png"
                    ],
                  );
                } else if (index == 1) {
                  return Padding(
                    padding: EdgeInsets.symmetric(
                      vertical: Sizes.size12,
                      horizontal: Sizes.size24,
                    ),
                    child: Column(
                      children: [
                        Gaps.v10,
                        Text(
                          product.name!,
                          maxLines: 5,
                          style: const TextStyle(
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            fontSize: Sizes.size18,
                          ),
                        ),
                        Gaps.v10,
                      ],
                    ),
                  );
                } else if (index == 2) {
                  return TabBar(
                    controller: _tabController,
                    padding: const EdgeInsets.symmetric(
                      horizontal: Sizes.size10,
                    ),
                    splashFactory: NoSplash.splashFactory,
                    isScrollable: true,
                    labelColor: Theme.of(context).primaryColorDark,
                    unselectedLabelColor: Colors.grey.shade500,
                    labelStyle: const TextStyle(
                      fontSize: Sizes.size18,
                      fontWeight: FontWeight.bold,
                    ),
                    indicatorColor: Theme.of(context).primaryColorDark,
                    indicatorWeight: 0.1,
                    tabAlignment: TabAlignment.center,
                    tabs: const [
                      Tab(
                        height: Sizes.size36,
                        text: "상세보기",
                      ),
                      Tab(
                        height: Sizes.size36,
                        text: "리뷰",
                      )
                    ],
                  );
                }
                return TabBarView(
                  controller: _tabController,
                  children: const [
                    Text('osfnsfsf'),
                    Text('osfnsfsf'),
                  ],
                );
              },
              itemCount: 4,
            );
            return Column(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Gaps.v10,
                Padding(
                  padding: const EdgeInsets.symmetric(
                    vertical: Sizes.size8,
                    horizontal: Sizes.size32,
                  ),
                  child: Column(
                    children: [
                      Text(
                        product.name!,
                        maxLines: 5,
                        style: const TextStyle(
                          color: Colors.black,
                          fontWeight: FontWeight.bold,
                          fontSize: Sizes.size18,
                        ),
                      ),
                    ],
                  ),
                ),
                Gaps.v10,
                TabBar(
                  controller: _tabController,
                  padding: const EdgeInsets.symmetric(
                    horizontal: Sizes.size10,
                  ),
                  splashFactory: NoSplash.splashFactory,
                  isScrollable: true,
                  labelColor: Theme.of(context).primaryColorDark,
                  unselectedLabelColor: Colors.grey.shade500,
                  labelStyle: const TextStyle(
                    fontSize: Sizes.size18,
                    fontWeight: FontWeight.bold,
                  ),
                  indicatorColor: Theme.of(context).primaryColorDark,
                  indicatorWeight: 0.1,
                  tabAlignment: TabAlignment.center,
                  tabs: const [
                    Tab(
                      height: Sizes.size36,
                      text: "상세보기",
                    ),
                    Tab(
                      height: Sizes.size36,
                      text: "리뷰",
                    )
                  ],
                ),
                Gaps.v12,
                TabBarView(
                  controller: _tabController,
                  children: [
                    Text('osfnsfsf'),
                    Text('osfnsfsf'),
                  ],
                ),
              ],
            );
          }
          return const CircularProgressIndicator();
        },
      ),
      bottomNavigationBar: BottomAppBar(
        padding: const EdgeInsets.symmetric(
            horizontal: Sizes.size32, vertical: Sizes.size10),
        color: Colors.white,
        surfaceTintColor: Colors.white,
        elevation: 1,
        child: FutureBuilder<ProductDetail>(
          future: _productDetail,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: Theme.of(context).primaryColorDark,
                  padding: const EdgeInsets.symmetric(
                    vertical: Sizes.size12,
                  ),
                ),
                onPressed: () => _showOrderBottomSheet(
                  snapshot.data!.size ?? [],
                  snapshot.data!.color ?? [],
                ),
                child: const Text(
                  '구매하기',
                  style: TextStyle(
                    fontSize: Sizes.size20,
                    fontWeight: FontWeight.bold,
                    color: Colors.white,
                  ),
                ),
              );
            }
            return const SizedBox.shrink();
          },
        ),
      ),
    );
  }
}
