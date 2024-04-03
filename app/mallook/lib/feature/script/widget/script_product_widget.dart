import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/product/model/product.dart';

import '../../product/product_screen.dart';

class ScriptProductWidget extends StatelessWidget {
  final Product product;
  static NumberFormat numberFormat = NumberFormat.currency(
    locale: 'ko_KR',
    symbol: '',
  );

  const ScriptProductWidget({
    super.key,
    required this.product,
  });

  void _moveToProductScreen(BuildContext context, Product product) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => ProductScreen(product: product),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => _moveToProductScreen(context, product),
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
              product.image!,
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
                    product.name!,
                    maxLines: 3,
                    overflow: TextOverflow.ellipsis,
                    style: const TextStyle(
                      color: Colors.black,
                      fontWeight: FontWeight.bold,
                      fontSize: Sizes.size16,
                    ),
                  ),
                  Text(
                    '${numberFormat.format(product.price)} ₩',
                    style: TextStyle(
                      color: Theme.of(context).primaryColorDark,
                      fontSize: Sizes.size16,
                      fontWeight: FontWeight.bold,
                    ),
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
