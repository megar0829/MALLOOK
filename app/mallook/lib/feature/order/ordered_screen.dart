import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:intl/intl.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/order/api/order_api_service.dart';
import 'package:mallook/feature/order/model/order_detail_model.dart';
import 'package:mallook/feature/order/widget/order_product_widget.dart';
import 'package:mallook/global/widget/custom_circular_wait_widget.dart';
import 'package:mallook/global/widget/home_icon_button.dart';

class OrderedScreen extends StatelessWidget {
  final num orderId;

  OrderedScreen({super.key, required this.orderId});

  late final Future<OrderDetail> _orderedDetail =
      OrderApiService.getOrderDetail(orderId);

  final NumberFormat numberFormat = NumberFormat.currency(
    locale: 'ko_KR',
    symbol: '',
  );

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          backgroundColor: Colors.white,
          surfaceTintColor: Colors.white,
          elevation: 1,
          shadowColor: Colors.grey.shade400,
          title: const Text('주문 내역'),
          centerTitle: true,
          titleTextStyle: const TextStyle(
            color: Colors.black,
            fontSize: Sizes.size18,
            fontWeight: FontWeight.bold,
          ),
          actions: const [
            HomeIconButton(),
            Gaps.h20,
          ],
        ),
        body: SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.symmetric(
              vertical: Sizes.size12,
              horizontal: Sizes.size24,
            ),
            child: Column(
              children: [
                Row(
                  children: [
                    FaIcon(
                      FontAwesomeIcons.gift,
                      color: Theme.of(context).primaryColor,
                    ),
                    Gaps.h10,
                    const Text(
                      '상품',
                      style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: Sizes.size18,
                      ),
                    ),
                    Gaps.v12,
                  ],
                ),
                Gaps.v12,
                FutureBuilder(
                  future: _orderedDetail,
                  builder: (context, snapshot) {
                    if (snapshot.hasData) {
                      return ListView.separated(
                        shrinkWrap: true,
                        physics: const NeverScrollableScrollPhysics(),
                        itemBuilder: (context, index) => OrderProductWidget(
                          cartItem: snapshot.data!.items[index],
                        ),
                        separatorBuilder: (context, index) => Gaps.v10,
                        itemCount: snapshot.data!.items.length,
                      );
                    } else {
                      return const CustomCircularWaitWidget();
                    }
                  },
                ),
                Gaps.v12,
                const Divider(),
                Gaps.v12,
                const Text(
                  '결재 내역',
                  style: TextStyle(
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                    fontSize: Sizes.size20,
                  ),
                ),
                Gaps.v12,
                FutureBuilder(
                  future: _orderedDetail,
                  builder: (context, snapshot) {
                    if (snapshot.hasData) {
                      return Column(
                        children: [
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              const Text(
                                '수량',
                                style: TextStyle(
                                  color: Colors.black,
                                  fontWeight: FontWeight.bold,
                                  fontSize: Sizes.size18,
                                ),
                              ),
                              Text(
                                '${numberFormat.format(snapshot.data!.totalCount)} 개',
                                style: TextStyle(
                                  color: Colors.grey.shade600,
                                  fontWeight: FontWeight.bold,
                                  fontSize: Sizes.size18,
                                ),
                              )
                            ],
                          ),
                          Gaps.v10,
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              const Text(
                                '배송비',
                                style: TextStyle(
                                  color: Colors.black,
                                  fontWeight: FontWeight.bold,
                                  fontSize: Sizes.size18,
                                ),
                              ),
                              Text(
                                '${numberFormat.format(snapshot.data!.totalFee)} ₩',
                                style: TextStyle(
                                  color: Colors.grey.shade600,
                                  fontWeight: FontWeight.bold,
                                  fontSize: Sizes.size18,
                                ),
                              )
                            ],
                          ),
                          Gaps.v10,
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              const Text(
                                '주문 금액',
                                style: TextStyle(
                                  color: Colors.black,
                                  fontWeight: FontWeight.bold,
                                  fontSize: Sizes.size18,
                                ),
                              ),
                              Text(
                                '${numberFormat.format(snapshot.data!.totalPrice)} ₩',
                                style: TextStyle(
                                  color: Colors.grey.shade600,
                                  fontWeight: FontWeight.bold,
                                  fontSize: Sizes.size18,
                                ),
                              )
                            ],
                          ),
                          Gaps.v10,
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              const Text(
                                '총 주문 금액',
                                style: TextStyle(
                                  color: Colors.black,
                                  fontWeight: FontWeight.bold,
                                  fontSize: Sizes.size18,
                                ),
                              ),
                              Text(
                                '${numberFormat.format(snapshot.data!.totalPrice + snapshot.data!.totalFee)} ₩',
                                style: TextStyle(
                                  color: Colors.grey.shade600,
                                  fontWeight: FontWeight.bold,
                                  fontSize: Sizes.size18,
                                ),
                              )
                            ],
                          ),
                        ],
                      );
                    } else {
                      return const SizedBox.shrink();
                    }
                  },
                ),
                Gaps.v12,
                const Divider(),
                Gaps.v12,
                const Text(
                  '결재 방법',
                  style: TextStyle(
                    color: Colors.black,
                    fontWeight: FontWeight.bold,
                    fontSize: Sizes.size20,
                  ),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
