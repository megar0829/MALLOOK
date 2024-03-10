import 'dart:async';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/api/home_api_service.dart';
import 'package:mallook/feature/home/widgets/cart_button.dart';
import 'package:mallook/feature/home/widgets/cart_modal.dart';
import 'package:mallook/feature/home/widgets/my-script-box.dart';
import 'package:mallook/feature/home/models/thumbnail_product.dart';
import 'package:mallook/feature/home/models/script.dart';

class HomeMyScreen extends StatefulWidget {
  const HomeMyScreen({super.key});

  @override
  State<HomeMyScreen> createState() => _HomeMyScreenState();
}

class _HomeMyScreenState extends State<HomeMyScreen> {
  final ScrollController _scrollController = ScrollController();
  final List<ThumbnailProduct> _products = []; // Future를 List로 변경합니다.
  final Future<Script> _script = HomeApiService.getMySingleScript();
  final NumberFormat numberFormat = NumberFormat.currency(
    locale: 'ko_KR',
    symbol: '',
  );
  int _productPage = 0;
  bool _isProductLoading = false;

  @override
  void initState() {
    super.initState();

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
    if (!_isProductLoading) {
      setState(() {
        _isProductLoading = true;
      });
      var loadedProducts = await HomeApiService.getProducts(_productPage);
      setState(() {
        _products.addAll(loadedProducts); // 기존 _products List에 새로운 제품 추가
        _productPage++;
        _isProductLoading = false;
      });
    }
  }

  void _onCartBtnTap(ThumbnailProduct product) async {
    await showModalBottomSheet(
      context: context,
      backgroundColor: Colors.transparent,
      isScrollControlled: true,
      builder: (context) => CartModal(
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
        child: SingleChildScrollView(
          controller: _scrollController,
          child: Column(
            children: [
              Gaps.v4,
              FutureBuilder(
                future: _script,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    return MyScriptBox(script: snapshot.data!);
                  }
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                },
              ),
              Gaps.v6,
              GridView.builder(
                shrinkWrap: true,
                physics: const NeverScrollableScrollPhysics(),
                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 2,
                  crossAxisSpacing: Sizes.size10,
                  mainAxisSpacing: Sizes.size5,
                  childAspectRatio: 0.73,
                ),
                itemBuilder: (context, index) => Column(
                  children: [
                    Container(
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(
                          Sizes.size20,
                        ),
                        border: Border.all(
                          color: Colors.grey.shade300,
                          width: Sizes.size1,
                        ),
                      ),
                      clipBehavior: Clip.hardEdge,
                      child: Stack(
                        children: [
                          AspectRatio(
                            aspectRatio: 1,
                            child: FadeInImage.assetNetwork(
                              placeholder: "assets/images/script_default.png",
                              image: _products[index].image,
                              fit: BoxFit.fitHeight,
                              filterQuality: FilterQuality.low,
                            ),
                          ),
                          Positioned(
                            bottom: Sizes.size6,
                            right: Sizes.size6,
                            child: GestureDetector(
                              onTap: () => _onCartBtnTap(_products[index]),
                              child: const CartButton(),
                            ),
                          )
                        ],
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.symmetric(
                        vertical: Sizes.size2,
                        horizontal: Sizes.size8,
                      ),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Row(
                            mainAxisAlignment: MainAxisAlignment.start,
                            children: [
                              Text(
                                "${_products[index].discountRatio}%",
                                style: const TextStyle(
                                  color: Color(0xfffc3e75),
                                  fontSize: Sizes.size14,
                                  fontWeight: FontWeight.bold,
                                ),
                              ),
                              Gaps.h10,
                              Text(
                                numberFormat.format(_products[index].price),
                                style: const TextStyle(
                                  color: Colors.black,
                                  fontSize: Sizes.size14,
                                  fontWeight: FontWeight.bold,
                                ),
                              ),
                            ],
                          ),
                          Gaps.v2,
                          Text(
                            _products[index].name,
                            overflow: TextOverflow.ellipsis,
                            style: const TextStyle(
                              color: Colors.black,
                              fontSize: Sizes.size12,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          Gaps.v1,
                          Row(
                            children: [
                              CircleAvatar(
                                radius: Sizes.size10,
                                foregroundImage: NetworkImage(
                                    _products[index].shoppingMallImgUrl),
                                child: Container(),
                              ),
                              Gaps.h10,
                              Text(
                                _products[index].shoppingMall,
                                overflow: TextOverflow.fade,
                                style: const TextStyle(
                                  color: Colors.black,
                                  fontSize: Sizes.size12,
                                  fontWeight: FontWeight.bold,
                                ),
                              ),
                            ],
                          )
                        ],
                      ),
                    )
                  ],
                ),
                itemCount: _products.length, // itemCount 수정
              ),
              if (_isProductLoading)
                Padding(
                  padding: const EdgeInsets.symmetric(
                    vertical: Sizes.size32,
                  ),
                  child: Center(
                    child: Column(
                      children: [
                        CircularProgressIndicator(
                          color: Theme.of(context).primaryColorLight,
                        ),
                        Gaps.v10,
                        Text(
                          '쪼매 기다리쇼 금방 돼여',
                          style: TextStyle(
                            fontSize: Sizes.size14,
                            color: Theme.of(context).primaryColor,
                            fontWeight: FontWeight.bold,
                          ),
                        )
                      ],
                    ),
                  ),
                ),
            ],
          ),
        ),
      ),
    );
  }
}
