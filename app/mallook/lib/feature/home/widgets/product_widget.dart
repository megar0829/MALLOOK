import 'dart:math';

import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/product/product_screen.dart';

class ProductWidget extends StatelessWidget {
  final Product _product;
  static NumberFormat numberFormat = NumberFormat.currency(
    locale: 'ko_KR',
    symbol: '',
  );

  const ProductWidget({super.key, required Product product})
      : _product = product;

  void _moveToProductScreen(BuildContext context, Product product) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => ProductScreen(product: product),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Column(
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
          child: GestureDetector(
            onTap: () => _moveToProductScreen(context, _product),
            child: AspectRatio(
              aspectRatio: 1,
              child: FadeInImage.assetNetwork(
                placeholder: "assets/images/ssafy_logo.png",
                image: _product.image!,
                fit: BoxFit.fill,
                filterQuality: FilterQuality.low,
              ),
            ),
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
                    "${Random().nextInt(100)}%",
                    style: const TextStyle(
                      color: Color(0xfffc3e75),
                      fontSize: Sizes.size14,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  Gaps.h10,
                  Text(
                    numberFormat.format(_product.price),
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
                _product.name!,
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
                    foregroundImage: NetworkImage(_product.image!),
                    child: Container(),
                  ),
                  Gaps.h10,
                  Text(
                    _product.brandName!,
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
    );
  }
}
