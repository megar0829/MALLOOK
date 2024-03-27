import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/global/cart/cart_controller.dart';
import 'package:mallook/global/widget/home_icon_button.dart';

class OrderScreen extends StatefulWidget {
  final List<CartItem>? carItem;

  const OrderScreen({super.key, this.carItem});

  @override
  State<OrderScreen> createState() => _OrderScreenState();
}

class _OrderScreenState extends State<OrderScreen> {
  late List<CartItem> _cartItems;
  final CartController cartController = Get.put(CartController());
  bool _selectAllItem = true;

  @override
  void initState() {
    super.initState();
    _cartItems = widget.carItem ?? cartController.items;
    for (var item in _cartItems) {
      if (!item.selected) {
        _selectAllItem = false;
        break;
      }
    }
    // if (_cartItems.isEmpty) {
    //   print('empty cart');
    //   goBack(context);
    // }
  }

  void goBack(BuildContext context) {
    Navigator.of(context).pop();
  }

  void _toggleAllItem(bool? value) {
    _selectAllItem = value!;
    if (_selectAllItem) {
      for (var element in _cartItems) {
        element.selected = true;
      }
    } else {
      for (var element in _cartItems) {
        element.selected = false;
      }
    }
    setState(() {});
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
                        value: _selectAllItem,
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
                  Text(
                    _cartItems[index].product.name,
                    maxLines: 5,
                    style: const TextStyle(
                      color: Colors.black,
                      fontSize: Sizes.size16,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  Gaps.v8,
                  Row(
                    mainAxisAlignment: MainAxisAlignment.start,
                    children: [
                      Image.network(
                        _cartItems[index].product.image!,
                        height: 150,
                        fit: BoxFit.cover,
                      ),
                      Gaps.h10,
                      Expanded(
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            Row(
                              mainAxisAlignment: MainAxisAlignment.spaceAround,
                              children: [
                                const Text(
                                  '사이즈',
                                  style: TextStyle(
                                    color: Colors.black54,
                                    fontWeight: FontWeight.bold,
                                    fontSize: Sizes.size18,
                                  ),
                                ),
                                Text(
                                  _cartItems[index].size,
                                  style: const TextStyle(
                                    color: Colors.black,
                                    fontWeight: FontWeight.bold,
                                    fontSize: Sizes.size18,
                                  ),
                                )
                              ],
                            ),
                            Gaps.v12,
                            Row(
                              mainAxisAlignment: MainAxisAlignment.spaceAround,
                              children: [
                                const Text(
                                  '색상',
                                  style: TextStyle(
                                    color: Colors.black54,
                                    fontWeight: FontWeight.bold,
                                    fontSize: Sizes.size18,
                                  ),
                                ),
                                Text(
                                  _cartItems[index].color,
                                  style: const TextStyle(
                                    color: Colors.black,
                                    fontWeight: FontWeight.bold,
                                    fontSize: Sizes.size18,
                                  ),
                                )
                              ],
                            ),
                            Gaps.v12,
                            Row(
                              mainAxisAlignment: MainAxisAlignment.spaceAround,
                              children: [
                                const Text(
                                  '수량',
                                  style: TextStyle(
                                    color: Colors.black54,
                                    fontWeight: FontWeight.bold,
                                    fontSize: Sizes.size18,
                                  ),
                                ),
                                Text(
                                  "${_cartItems[index].quantity}",
                                  style: const TextStyle(
                                    color: Colors.black,
                                    fontWeight: FontWeight.bold,
                                    fontSize: Sizes.size18,
                                  ),
                                )
                              ],
                            ),
                            Gaps.v12,
                            Text(
                              '${_cartItems[index].product.price * _cartItems[index].quantity}원',
                              style: TextStyle(
                                color: Theme.of(context).primaryColorDark,
                                fontSize: Sizes.size20,
                                fontWeight: FontWeight.bold,
                              ),
                            )
                          ],
                        ),
                      ),
                    ],
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
        child: Row(),
      ),
    );
  }
}
