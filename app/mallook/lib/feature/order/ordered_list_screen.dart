import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:intl/intl.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/order/api/order_api_service.dart';
import 'package:mallook/feature/order/model/order_detail_model.dart';
import 'package:mallook/feature/order/ordered_screen.dart';
import 'package:mallook/global/widget/custom_circular_wait_widget.dart';

class OrderedListScreen extends StatefulWidget {
  const OrderedListScreen({super.key});

  @override
  State<OrderedListScreen> createState() => _OrderedListScreenState();
}

class _OrderedListScreenState extends State<OrderedListScreen> {
  final ScrollController _scrollController = ScrollController();
  NumberFormat numberFormat = NumberFormat.currency(
    locale: 'ko_KR',
    symbol: '',
  );
  final List<OrderDetail> _orderedList = [];
  int _orderPage = 0;
  bool _isOrderLoading = false;

  @override
  void initState() {
    super.initState();

    _loadMoreOrderedList();
    _scrollController.addListener(() {
      if (_scrollController.offset >=
              (_scrollController.position.maxScrollExtent * 0.9) &&
          !_scrollController.position.outOfRange) {
        _loadMoreOrderedList();
      }
    });
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  void _moveToOrderDetailScreen(OrderDetail orderedList) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => OrderedScreen(
          orderId: orderedList.id,
        ),
      ),
    );
  }

  void _loadMoreOrderedList() async {
    if (!_isOrderLoading) {
      print('hihihihihi');
      if (mounted) {
        setState(() {
          _isOrderLoading = true;
        });

        var loadedOrderedList =
            await OrderApiService.getOrderedList(_orderPage);
        if (mounted) {
          setState(() {
            _orderedList.addAll(loadedOrderedList);
            _orderPage++;
            _isOrderLoading = false;
          });
        }
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    print(_orderedList);
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        surfaceTintColor: Colors.white,
        elevation: 1,
        shadowColor: Colors.grey.shade400,
        titleTextStyle: const TextStyle(
          color: Colors.black,
          fontWeight: FontWeight.bold,
          fontSize: Sizes.size20,
        ),
        centerTitle: true,
        title: const Text("주문 리스트"),
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(
          vertical: Sizes.size12,
          horizontal: Sizes.size24,
        ),
        child: ListView.separated(
          controller: _scrollController,
          itemBuilder: (context, index) {
            if (index < _orderedList.length) {
              return GestureDetector(
                onTap: () => _moveToOrderDetailScreen(_orderedList[index]),
                child: Container(
                  decoration: BoxDecoration(
                    border: Border.all(
                      color: Colors.grey.shade400,
                      width: Sizes.size1,
                    ),
                    borderRadius: BorderRadius.circular(
                      Sizes.size16,
                    ),
                  ),
                  padding: const EdgeInsets.symmetric(
                    vertical: Sizes.size10,
                    horizontal: Sizes.size12,
                  ),
                  child: Column(
                    children: [
                      Row(
                        children: [
                          FaIcon(
                            FontAwesomeIcons.calendarDays,
                            color: Colors.grey.shade800,
                            size: Sizes.size20,
                          ),
                          Gaps.h10,
                          Text(
                            '2023-10-12 16:33:12',
                            style: TextStyle(
                              color: Colors.grey.shade800,
                              fontSize: Sizes.size14,
                            ),
                          ),
                        ],
                      ),
                      const Divider(),
                      Row(
                        children: [
                          Image.network(
                            '${_orderedList[index].items.first.product.image}',
                            fit: BoxFit.cover,
                            height: 120,
                          ),
                          Gaps.h12,
                          Expanded(
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.spaceAround,
                              children: [
                                Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: [
                                    const Text(
                                      '수량',
                                      style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: Sizes.size14,
                                      ),
                                    ),
                                    Text(
                                      '${_orderedList[index].totalCount}',
                                      style: TextStyle(
                                        color: Colors.grey.shade700,
                                        fontWeight: FontWeight.bold,
                                        fontSize: Sizes.size14,
                                      ),
                                    )
                                  ],
                                ),
                                Gaps.v6,
                                Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: [
                                    const Text(
                                      '배송료',
                                      style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: Sizes.size14,
                                      ),
                                    ),
                                    Text(
                                      '${numberFormat.format(_orderedList[index].totalFee)} ₩',
                                      style: TextStyle(
                                        color: Colors.grey.shade700,
                                        fontWeight: FontWeight.bold,
                                        fontSize: Sizes.size14,
                                      ),
                                    )
                                  ],
                                ),
                                Gaps.v6,
                                Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: [
                                    const Text(
                                      '상품 가격',
                                      style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: Sizes.size14,
                                      ),
                                    ),
                                    Text(
                                      '${numberFormat.format(_orderedList[index].totalPrice)} ₩',
                                      style: TextStyle(
                                        color: Colors.grey.shade700,
                                        fontWeight: FontWeight.bold,
                                        fontSize: Sizes.size14,
                                      ),
                                    )
                                  ],
                                ),
                                Gaps.v6,
                                Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceBetween,
                                  children: [
                                    const Text(
                                      '총 구매금액',
                                      style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: Sizes.size14,
                                      ),
                                    ),
                                    Text(
                                      '${numberFormat.format(_orderedList[index].totalFee + _orderedList[index].totalPrice)} ₩',
                                      style: TextStyle(
                                        color: Colors.grey.shade700,
                                        fontWeight: FontWeight.bold,
                                        fontSize: Sizes.size14,
                                      ),
                                    ),
                                  ],
                                ),
                              ],
                            ),
                          ),
                        ],
                      )
                    ],
                  ),
                ),
              );
            } else {
              return CustomCircularWaitWidget();
            }
          },
          separatorBuilder: (context, index) => Gaps.v10,
          itemCount: _orderedList.length + 1,
        ),
      ),
    );
  }
}
