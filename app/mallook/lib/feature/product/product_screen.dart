import 'package:flutter/material.dart';
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

class _ProductScreenState extends State<ProductScreen> {
  late Future<ProductDetail> _productDetail;
  final ScrollController _storeController = ScrollController();

  @override
  void initState() {
    super.initState();
    _productDetail = ProductApiService.getProductDetail(widget.product.id!);
    print('hihih   $_productDetail');
  }

  @override
  void dispose() {
    super.dispose();
    _storeController.dispose();
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
      body: SingleChildScrollView(
        child: FutureBuilder<ProductDetail>(
          future: _productDetail,
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              var product = snapshot.data!;
              return Column(
                mainAxisAlignment: MainAxisAlignment.start,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  ProductImgWidget(
                    images: [
                      product.image ??
                          "https://zooting-s3-bucket.s3.ap-northeast-2.amazonaws.com/ssafy_logo.png"
                    ],
                  ),
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
                  Expanded(
                    child: ListView.separated(
                        itemBuilder: (context, index) => Container(),
                        separatorBuilder: (context, index) => Gaps.v8,
                        itemCount: (product.detailImages ?? []).length),
                  ),
                ],
              );
            }
            return const CircularProgressIndicator();
          },
        ),
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
