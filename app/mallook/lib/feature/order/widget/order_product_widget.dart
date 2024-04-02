import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/global/cart/model/page_cart_item.dart';

class OrderProductWidget extends StatelessWidget {
  final CartItem cartItem;

  const OrderProductWidget({super.key, required this.cartItem});

  static NumberFormat numberFormat = NumberFormat.currency(
    locale: 'ko_KR',
    symbol: '',
  );

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(
        vertical: Sizes.size10,
        horizontal: Sizes.size16,
      ),
      decoration: BoxDecoration(
        border: Border.all(
          color: Colors.grey.shade300,
          width: Sizes.size1,
        ),
        borderRadius: BorderRadius.circular(
          Sizes.size18,
        ),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Image.network(
                cartItem.image == null
                    ? "https://zooting-s3-bucket.s3.ap-northeast-2.amazonaws.com/ssafy_logo.png"
                    : cartItem.image!,
                height: 120,
                fit: BoxFit.cover,
              ),
              Gaps.h10,
              Expanded(
                child: SizedBox(
                  height: 120,
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        cartItem.name!,
                        maxLines: 5,
                        style: const TextStyle(
                          color: Colors.black,
                          fontSize: Sizes.size16,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.symmetric(
                          vertical: Sizes.size6,
                          horizontal: Sizes.size18,
                        ),
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Text(
                              '수량 ${cartItem.count!}',
                              style: TextStyle(
                                color: Colors.grey.shade700,
                                fontSize: Sizes.size16,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                            Text(
                              cartItem.size!,
                              maxLines: 3,
                              style: TextStyle(
                                color: Colors.grey.shade700,
                                fontSize: Sizes.size16,
                                fontWeight: FontWeight.bold,
                              ),
                            )
                          ],
                        ),
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.end,
                        children: [
                          Text(
                            '${numberFormat.format(cartItem.price! * cartItem.count!)} ₩',
                            style: TextStyle(
                              color: Theme.of(context).primaryColorDark,
                              fontSize: Sizes.size18,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                        ],
                      )
                    ],
                  ),
                ),
              )
            ],
          )
        ],
      ),
    );
  }
}
