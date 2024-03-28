import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/order/order_screen.dart';
import 'package:mallook/feature/product/product_screen.dart';
import 'package:mallook/global/cart/cart_controller.dart';
import 'package:mallook/global/mallook_snackbar.dart';
import 'package:mallook/global/widget/home_icon_button.dart';

class CartScreen extends StatefulWidget {
  final List<CartItem>? carItem;

  const CartScreen({super.key, this.carItem});

  @override
  State<CartScreen> createState() => _CartScreenState();
}

class _CartScreenState extends State<CartScreen> {
  late List<CartItem> _cartItems;
  final CartController cartController = Get.put(CartController());
  static NumberFormat numberFormat = NumberFormat.currency(
    locale: 'ko_KR',
    symbol: '',
  );
  bool _isAllItemSelected = true;

  @override
  void initState() {
    super.initState();
    _cartItems = widget.carItem ?? cartController.items;
    for (var item in _cartItems) {
      item.selected = true;
    }
  }

  void goBack(BuildContext context) {
    Navigator.of(context).pop();
  }

  void _toggleAllItem(bool? value) {
    _isAllItemSelected = value!;
    for (var item in _cartItems) {
      item.selected = _isAllItemSelected;
    }
    setState(() {});
  }

  void _updateIsAllItemSelected() {
    _isAllItemSelected = true;
    for (var item in _cartItems) {
      if (!item.selected) {
        _isAllItemSelected = false;
        break;
      }
    }
    setState(() {});
  }

  bool _isOrderAble() {
    for (var item in _cartItems) {
      if (item.selected) {
        return true;
      }
    }
    return false;
  }

  // 선택되어 있는 상품 삭제
  void _clearSelectedItem() {
    List<CartItem> removeItems = [];

    for (var item in _cartItems) {
      if (item.selected) {
        removeItems.add(item);
      }
    }

    for (var item in removeItems) {
      cartController.removeItem(cartItem: item);
    }
    setState(() {});
  }

  int _getTotalPrice() {
    int totalPrice = 0;
    for (var item in _cartItems) {
      if (item.selected) {
        totalPrice += item.product.price * item.quantity;
      }
    }
    return totalPrice;
  }

  int _getTotalQuantity() {
    int totalQuantity = 0;
    for (var item in _cartItems) {
      if (item.selected) {
        totalQuantity += item.quantity;
      }
    }
    return totalQuantity;
  }

  void _moveToProductScreen(Product product) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => ProductScreen(product: product),
      ),
    );
  }

  void _moveToOrderScreen() {
    List<CartItem> orderItem = [];
    for (var item in _cartItems) {
      if (!item.selected) continue;
      orderItem.add(item);
    }
    if (orderItem.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        mallookSnackBar(title: '주문하려는 상품이 없습니다.'),
      );
      return;
    }
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => OrderScreen(
          cartItem: _cartItems,
        ),
      ),
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
        title: const Text('장바구니'),
        titleTextStyle: const TextStyle(
          color: Colors.black,
          fontWeight: FontWeight.bold,
          fontSize: Sizes.size18,
        ),
        actions: const [
          HomeIconButton(),
          Gaps.h20,
        ],
        bottom: PreferredSize(
          preferredSize: const Size.fromHeight(
            Sizes.size56,
          ),
          child: Padding(
            padding: const EdgeInsets.symmetric(
              horizontal: Sizes.size18,
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Row(
                  children: [
                    Transform.scale(
                      scale: 1.4,
                      child: Checkbox(
                        checkColor: Theme.of(context).primaryColorDark,
                        fillColor: const MaterialStatePropertyAll(Colors.white),
                        shape: const CircleBorder(
                          side: BorderSide(
                            color: Colors.black,
                          ),
                        ),
                        materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
                        visualDensity: const VisualDensity(
                          horizontal: VisualDensity.minimumDensity,
                        ),
                        value: _isAllItemSelected,
                        onChanged: (value) => _toggleAllItem(value),
                      ),
                    ),
                    Gaps.h8,
                    const Text(
                      '전체선택',
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: Sizes.size16,
                      ),
                    )
                  ],
                ),
                GestureDetector(
                  onTap: _clearSelectedItem,
                  child: Text(
                    '상품삭제',
                    style: TextStyle(
                      color: Colors.grey.shade500,
                      fontSize: Sizes.size16,
                    ),
                  ),
                )
              ],
            ),
          ),
        ),
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.symmetric(
            vertical: Sizes.size12,
            horizontal: Sizes.size24,
          ),
          child: ListView.separated(
            shrinkWrap: true,
            physics: const NeverScrollableScrollPhysics(),
            itemCount: _cartItems.length,
            itemBuilder: (context, index) => Container(
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
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Row(
                        children: [
                          Transform.scale(
                            scale: 1.2,
                            child: Checkbox(
                              checkColor: Theme.of(context).primaryColorDark,
                              fillColor:
                                  const MaterialStatePropertyAll(Colors.white),
                              shape: const CircleBorder(
                                side: BorderSide(
                                  color: Colors.black,
                                ),
                              ),
                              materialTapTargetSize:
                                  MaterialTapTargetSize.shrinkWrap,
                              visualDensity: const VisualDensity(
                                horizontal: VisualDensity.minimumDensity,
                                vertical: VisualDensity.minimumDensity,
                              ),
                              value: _cartItems[index].selected,
                              onChanged: (value) => setState(() {
                                _cartItems[index].selected = value!;
                                _updateIsAllItemSelected();
                              }),
                            ),
                          ),
                          Gaps.h6,
                          Text(
                            '선택',
                            style: TextStyle(
                              color: Colors.grey.shade600,
                              fontSize: Sizes.size16,
                            ),
                          )
                        ],
                      ),
                      GestureDetector(
                        onTap: () => setState(() {
                          cartController.removeItem(
                            cartItem: _cartItems[index],
                          );
                        }),
                        child: Text(
                          '삭제',
                          style: TextStyle(
                            color: Colors.grey.shade800,
                            fontSize: Sizes.size16,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      )
                    ],
                  ),
                  const Divider(),
                  Gaps.v8,
                  GestureDetector(
                    onTap: () =>
                        _moveToProductScreen(_cartItems[index].product),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.start,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Image.network(
                          _cartItems[index].product.image!,
                          height: 150,
                          fit: BoxFit.cover,
                        ),
                        Gaps.h10,
                        Expanded(
                          child: SizedBox(
                            height: 150,
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              children: [
                                Text(
                                  _cartItems[index].product.name,
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
                                    mainAxisAlignment:
                                        MainAxisAlignment.spaceBetween,
                                    children: [
                                      Text(
                                        '수량 ${_cartItems[index].quantity}',
                                        style: TextStyle(
                                          color: Colors.grey.shade700,
                                          fontSize: Sizes.size16,
                                          fontWeight: FontWeight.bold,
                                        ),
                                      ),
                                      Text(
                                        _cartItems[index].size,
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
                                      '${numberFormat.format(_cartItems[index].product.price * _cartItems[index].quantity)} ₩',
                                      style: TextStyle(
                                        color:
                                            Theme.of(context).primaryColorDark,
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
                    ),
                  )
                ],
              ),
            ),
            separatorBuilder: (context, index) => Gaps.v10,
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
                Text("${numberFormat.format(_getTotalPrice())} ₩"),
                Gaps.h20,
                const Text('구매하기'),
                const SizedBox.shrink(),
              ],
            ),
            onTap: _moveToOrderScreen,
          ),
        ),
      ),
    );
  }
}
