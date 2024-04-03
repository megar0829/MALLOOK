import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/coupon/model/cursor_coupons.dart';
import 'package:mallook/feature/order/ordered_screen.dart';
import 'package:mallook/feature/order/widget/cart_coupon_dropdown_widget.dart';
import 'package:mallook/feature/order/widget/order_product_widget.dart';
import 'package:mallook/global/cart/cart_controller.dart';
import 'package:mallook/global/cart/model/page_cart_item.dart';
import 'package:mallook/global/mallook_snackbar.dart';
import 'package:mallook/global/widget/custom_circular_wait_bold_widget.dart';
import 'package:mallook/global/widget/home_icon_button.dart';

class OrderScreen extends StatefulWidget {
  final List<CartItem> orderItems;

  const OrderScreen({super.key, required this.orderItems});

  @override
  State<OrderScreen> createState() => _OrderScreenState();
}

class _OrderScreenState extends State<OrderScreen> {
  final CartController cartController = Get.put(CartController());
  Coupon? _selectedCoupon;
  final List<Coupon> _coupons = [
    // Coupon(name: '세진 요정', type: "ratio", discount: 20),
    // Coupon(name: '윤정 뚱땡이', type: "ratio", discount: 5),
  ];
  static NumberFormat numberFormat = NumberFormat.currency(
    locale: 'ko_KR',
    symbol: '',
  );

  @override
  void initState() {
    super.initState();
    if (widget.orderItems.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        mallookSnackBar(title: '주문 가능한 상품이 없습니다.'),
      );
      Navigator.of(context).pop();
    }
  }

  void _doOrderProcess() {
    showPurchaseModal();
  }

  bool _isOrderAble() {
    return true;
  }

  int _getTotalQuantity() {
    int total = 0;
    for (var item in widget.orderItems) {
      total += item.count!;
    }
    return total;
  }

  int _getExpectedPrice() {
    int totalPrice = _getTotalPrice();
    if (_selectedCoupon == null) {
      return totalPrice;
    }
    if (_selectedCoupon!.type == 'MONEY') {
      // if (totalPrice >= _selectedCoupon!.discount) {
      //   return totalPrice - _selectedCoupon!.discount;
      // }
      return 0;
    }
    if (_selectedCoupon!.type == 'RATIO') {
      // return totalPrice * (100 - _selectedCoupon!.discount) ~/ 100;
    }
    return 0;
  }

  int _getTotalPrice() {
    int total = 0;
    for (var item in widget.orderItems) {
      total += item.price ?? 0 * (item.count ?? 0);
    }
    return total;
  }

  void updateCoupon(Coupon coupon) {
    setState(() {
      _selectedCoupon = coupon;
    });
  }

  void _clearCoupon() {
    setState(() {
      _selectedCoupon = null;
    });
  }

  void showAlertModal() async {
    await showDialog(
      context: context,
      builder: (context) => AlertDialog(
        title: const Center(
          child: Text(
            '주문에 실패 하였습니다..ㅠ',
            style: TextStyle(
              color: Colors.pinkAccent,
              fontWeight: FontWeight.bold,
              fontSize: Sizes.size18,
            ),
          ),
        ),
        actions: <Widget>[
          TextButton(
            onPressed: () {
              Navigator.of(context).pop();
            },
            child: const Text('확인'),
          ),
        ],
      ),
    );
  }

  void removeOrderedItem() {
    List<CartItem> removeItems = [];
    for (var item in widget.orderItems) {
      removeItems.add(item);
    }
    for (var item in removeItems) {
      cartController.removeItem(cartItem: item);
    }
    setState(() {});
  }

  void showPurchaseModal() async {
    await showDialog(
      context: context,
      barrierDismissible: false, // 다른 동작 막음
      builder: (BuildContext context) {
        Future.delayed(const Duration(seconds: 3), () {
          var response = 200;
          num orderId = 1212;

          if (response == 200) {
            removeOrderedItem();
            Navigator.pushAndRemoveUntil(
              context,
              MaterialPageRoute(
                builder: (context) => OrderedScreen(
                  orderId: orderId,
                ),
              ),
              (route) => false,
            );
          } else {
            Navigator.of(context).pop();
            showAlertModal();
          }
        });

        return const AlertDialog(
          title: Column(
            children: [
              Text(
                '주문 결재 중 입니다.',
                style: TextStyle(
                  color: Colors.black,
                  fontSize: Sizes.size20,
                  fontWeight: FontWeight.bold,
                ),
              ),
              CustomCircularWaitBoldWidget(),
            ],
          ),
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        surfaceTintColor: Colors.white,
        elevation: 1,
        shadowColor: Colors.grey.shade400,
        centerTitle: true,
        title: const Text('주문하기'),
        titleTextStyle: const TextStyle(
          color: Colors.black,
          fontWeight: FontWeight.bold,
          fontSize: Sizes.size18,
        ),
        actions: const [
          HomeIconButton(),
          Gaps.h24,
        ],
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.symmetric(
            vertical: Sizes.size12,
            horizontal: Sizes.size24,
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
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
                ],
              ),
              Gaps.v10,
              ListView.separated(
                shrinkWrap: true,
                physics: const NeverScrollableScrollPhysics(),
                itemCount: widget.orderItems.length,
                itemBuilder: (context, index) => OrderProductWidget(
                  cartItem: widget.orderItems[index],
                ),
                separatorBuilder: (context, index) => Gaps.v10,
              ),
              Gaps.v14,
              const Divider(),
              Gaps.v14,
              Row(
                children: [
                  FaIcon(
                    FontAwesomeIcons.ticket,
                    color: Theme.of(context).primaryColor,
                  ),
                  Gaps.h10,
                  const Text(
                    '쿠폰',
                    style: TextStyle(
                      color: Colors.black,
                      fontWeight: FontWeight.bold,
                      fontSize: Sizes.size18,
                    ),
                  ),
                ],
              ),
              Gaps.v12,
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  CartCouponDropdownWidget(
                    coupons: _coupons,
                    onChange: (coupon) => updateCoupon(coupon),
                    selectedCoupon: _selectedCoupon,
                    totalPrice: _getTotalPrice(),
                  ),
                  Gaps.h12,
                  if (_selectedCoupon != null)
                    GestureDetector(
                      onTap: _clearCoupon,
                      child: Icon(
                        FontAwesomeIcons.xmark,
                        color: Colors.grey.shade600,
                        size: Sizes.size24,
                      ),
                    ),
                ],
              ),
              Gaps.v12,
              const Divider(),
              Gaps.v12,
              Row(
                children: [
                  FaIcon(
                    FontAwesomeIcons.cashRegister,
                    color: Theme.of(context).primaryColor,
                  ),
                  Gaps.h10,
                  const Text(
                    '결재 방법',
                    style: TextStyle(
                      color: Colors.black,
                      fontWeight: FontWeight.bold,
                      fontSize: Sizes.size18,
                    ),
                  ),
                ],
              ),
              Gaps.v12,
            ],
          ),
        ),
      ),
      bottomNavigationBar: BottomAppBar(
        elevation: 1,
        shadowColor: Colors.grey.shade600,
        color: Colors.white,
        surfaceTintColor: Colors.white,
        padding: const EdgeInsets.symmetric(
          vertical: Sizes.size12,
          horizontal: Sizes.size20,
        ),
        child: AnimatedContainer(
          decoration: BoxDecoration(
            color: _isOrderAble() ? Colors.blueAccent : Colors.grey,
            borderRadius: BorderRadius.circular(
              Sizes.size18,
            ),
          ),
          duration: const Duration(
            milliseconds: 500,
          ),
          child: ListTile(
            textColor: Colors.white,
            titleTextStyle: const TextStyle(
              fontSize: Sizes.size20,
              fontWeight: FontWeight.bold,
            ),
            title: Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: [
                Text("${_getTotalQuantity()}개"),
                const Text("|"),
                Text("${numberFormat.format(_getExpectedPrice())} ₩"),
                Gaps.h20,
                const Text('구매하기'),
                const SizedBox.shrink(),
              ],
            ),
            onTap: _doOrderProcess,
          ),
        ),
      ),
    );
  }
}
