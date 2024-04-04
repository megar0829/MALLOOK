import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/product/model/product.dart';
import 'package:mallook/feature/style/model/style_detail.dart';

import '../../product/product_screen.dart';

class StyleProductWidget extends StatelessWidget {
  final StyleProduct styleProduct;
  static NumberFormat numberFormat = NumberFormat.currency(
    locale: 'ko_KR',
    symbol: '',
  );

  const StyleProductWidget({
    super.key,
    required this.styleProduct,
  });

  void _moveToProductScreen(BuildContext context, StyleProduct styleProduct) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => ProductScreen(
          product: Product(
            id: styleProduct.productsId,
            name: styleProduct.name,
            price: styleProduct.price,
            brandName: styleProduct.brandName,
            image: styleProduct.image,
          ),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => _moveToProductScreen(context, styleProduct),
      child: Row(
        children: [
          Container(
            width: 120,
            height: 120,
            clipBehavior: Clip.hardEdge,
            decoration: BoxDecoration(
              border: Border.all(
                color: Colors.grey.shade300,
                width: Sizes.size1,
              ),
              borderRadius: BorderRadius.circular(
                Sizes.size20,
              ),
            ),
            child: Image.network(
              styleProduct.image!,
              fit: BoxFit.cover,
            ),
          ),
          Gaps.h8,
          Expanded(
            child: Container(
              height: 120,
              padding: const EdgeInsets.symmetric(
                vertical: Sizes.size8,
                horizontal: Sizes.size10,
              ),
              clipBehavior: Clip.hardEdge,
              decoration: BoxDecoration(
                border: Border.all(
                  color: Colors.grey.shade300,
                  width: Sizes.size1,
                ),
                borderRadius: BorderRadius.circular(
                  Sizes.size20,
                ),
              ),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    styleProduct.name!,
                    maxLines: 3,
                    overflow: TextOverflow.ellipsis,
                    style: const TextStyle(
                      color: Colors.black,
                      fontWeight: FontWeight.bold,
                      fontSize: Sizes.size16,
                    ),
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        '${numberFormat.format(styleProduct.price)} ₩',
                        style: TextStyle(
                          color: Theme.of(context).primaryColorDark,
                          fontSize: Sizes.size16,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      Text(
                        styleProduct.brandName ?? "",
                        overflow: TextOverflow.ellipsis,
                        style: TextStyle(
                          color: Colors.grey.shade800,
                          fontWeight: FontWeight.bold,
                          fontSize: Sizes.size14,
                        ),
                      )
                    ],
                  ),
                ],
              ),
            ),
          )
        ],
      ),
    );
  }
}
