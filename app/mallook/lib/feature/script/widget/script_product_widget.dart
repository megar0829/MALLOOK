import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/models/product.dart';

class ScriptProductWidget extends StatelessWidget {
  final Product product;

  const ScriptProductWidget({
    super.key,
    required this.product,
  });

  @override
  Widget build(BuildContext context) {
    return Row(
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
                  product.name,
                  maxLines: 2,
                  overflow: TextOverflow.ellipsis,
                  style: const TextStyle(
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                    fontSize: Sizes.size14,
                  ),
                ),
                Gaps.h4,
                Text(
                  '${product.price}₩',
                  style: const TextStyle(
                    color: Colors.black,
                    fontSize: Sizes.size14,
                  ),
                ),
                Gaps.h4,
                const Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    CircleAvatar(), // TODO: 쇼핑몰 사진 정보
                    Gaps.h10,
                    Text(
                      'sfsfs', // TODO: 쇼핑몰 정보
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: Sizes.size14,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
        )
      ],
    );
  }
}
