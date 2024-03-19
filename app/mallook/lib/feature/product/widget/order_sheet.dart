import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:get/get.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/order/order_screen.dart';
import 'package:mallook/feature/product/widget/option_selector.dart';
import 'package:mallook/global/cart/cart_controller.dart';
import 'package:mallook/global/mallook_snackbar.dart';

class OrderSheet extends StatefulWidget {
  final Product product; // TODO: API를 통해 상품 정보 로딩 필요
  final String? title;
  final List<String> sizes;
  final List<String> colors;

  const OrderSheet({
    super.key,
    required this.sizes,
    required this.colors,
    this.title,
    required this.product,
  });

  @override
  State<OrderSheet> createState() => _OrderSheetState();
}

class _OrderSheetState extends State<OrderSheet> {
  final CartController cartController = Get.put(CartController());
  String? _selectedSize;
  String? _selectedColor;
  final List<CartItem> _cartItems = [];

  void _updateSize(String? newValue) {
    setState(() {
      _selectedSize = newValue;
    });
  }

  void _updateColor(String? newValue) {
    setState(() {
      _selectedColor = newValue;

      if (_selectedSize == null || _selectedColor == null) return;
      CartItem cartItem = CartItem(
        product: widget.product,
        quantity: 1,
        size: _selectedSize!,
        color: _selectedColor!,
      );
      _cartItems.add(cartItem);
      _selectedSize = null;
      _selectedColor = null;
    });
  }

  void _onOrderBtnTap() {
    if (_cartItems.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        mallookSnackBar(
          title: '담겨 있는 상품이 없습니다!',
        ),
      );

      return;
    }
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => OrderScreen(
          carItem: _cartItems,
        ),
      ),
    );
  }

  void _onCartBtnTap() {
    if (_cartItems.isEmpty) {
      ScaffoldMessenger.of(context).showSnackBar(
        mallookSnackBar(
          title: '담겨 있는 상품이 없습니다!',
        ),
      );

      return;
    }
    for (var cartItem in _cartItems) {
      cartController.addItem(
        productId: cartItem.product.name,
        cartItem: cartItem,
      );
    }
    setState(() {
      _cartItems.clear();
    });

    ScaffoldMessenger.of(context).showSnackBar(
      mallookSnackBar(
        title: "상품이 장바구니에 담겼습니다.",
        icon: FontAwesomeIcons.arrowRight,
        onTap: () => Navigator.of(context).push(
          MaterialPageRoute(
            builder: (context) => const OrderScreen(),
          ),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    var deviceSize = MediaQuery.of(context).size;
    return Container(
      height: deviceSize.height * 0.6,
      clipBehavior: Clip.hardEdge,
      decoration: BoxDecoration(
        color: Colors.grey.shade300,
        border: Border.all(
          color: Colors.grey.shade600,
          width: Sizes.size1,
        ),
        borderRadius: const BorderRadius.only(
          topLeft: Radius.circular(
            Sizes.size24,
          ),
          topRight: Radius.circular(
            Sizes.size24,
          ),
        ),
      ),
      child: Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          surfaceTintColor: Colors.white,
          backgroundColor: Colors.white,
          automaticallyImplyLeading: false,
          elevation: 1,
          shadowColor: Colors.black,
          title: Center(
            child: Text(
              widget.title == null ? '' : widget.title!,
              style: const TextStyle(
                color: Colors.black,
                fontSize: Sizes.size18,
                overflow: TextOverflow.fade,
              ),
            ),
          ),
          actions: [
            IconButton(
              onPressed: () => Navigator.of(context).pop(),
              icon: Icon(
                Icons.close,
                color: Theme.of(context).primaryColor,
                size: Sizes.size24,
              ),
            )
          ],
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(
            vertical: Sizes.size20,
            horizontal: Sizes.size24,
          ),
          child: Column(
            children: [
              Container(
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size6,
                  horizontal: Sizes.size10,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      '사이즈',
                      style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: Sizes.size14,
                      ),
                    ),
                    Gaps.v6,
                    OptionSelector(
                      items: widget.sizes,
                      hintText: "사이즈 선택!",
                      onChanged: _updateSize,
                      selectedItem: _selectedSize,
                    )
                  ],
                ),
              ),
              Gaps.v20,
              Container(
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size6,
                  horizontal: Sizes.size10,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      '컬러',
                      style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: Sizes.size14,
                      ),
                    ),
                    Gaps.v6,
                    OptionSelector(
                      items: widget.colors,
                      hintText: "컬러 선택!",
                      onChanged: _updateColor,
                      selectedItem: _selectedColor,
                      isEnable: _selectedSize == null ? false : true,
                    )
                  ],
                ),
              ),
              Expanded(
                child: ListView.separated(
                  itemCount: _cartItems.length,
                  separatorBuilder: (context, index) => const Divider(),
                  itemBuilder: (context, index) => ListTile(
                    title: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceAround,
                      children: [
                        Text(
                          _cartItems[index].size,
                          style: const TextStyle(
                            color: Colors.black,
                            fontSize: Sizes.size16,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        Text(
                          _cartItems[index].color,
                          style: const TextStyle(
                            color: Colors.black,
                            fontSize: Sizes.size16,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                      ],
                    ),
                    trailing: Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        Container(
                          padding: const EdgeInsets.all(
                            Sizes.size6,
                          ),
                          decoration: BoxDecoration(
                            color: Colors.black.withOpacity(0.3),
                            borderRadius: BorderRadius.circular(
                              Sizes.size6,
                            ),
                          ),
                          child: InkWell(
                            onTap: () {
                              if (_cartItems[index].quantity > 1) {
                                setState(() {
                                  _cartItems[index].quantity -= 1;
                                });
                              } else {
                                setState(() {
                                  _cartItems.remove(_cartItems[index]);
                                });
                              }
                            },
                            child: const Icon(
                              Icons.remove,
                              size: Sizes.size18,
                              color: Colors.black,
                            ),
                          ),
                        ),
                        Center(
                          child: Padding(
                            padding: const EdgeInsets.symmetric(
                              horizontal: Sizes.size10,
                            ), // Sizes.size12 대신 사용
                            child: Text(
                              '${_cartItems[index].quantity}',
                              style: const TextStyle(
                                color: Colors.black,
                                fontSize: Sizes.size14,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ),
                        ),
                        Container(
                          padding: const EdgeInsets.all(
                            Sizes.size6,
                          ),
                          decoration: BoxDecoration(
                            color: Colors.black.withOpacity(0.3),
                            borderRadius: BorderRadius.circular(
                              Sizes.size6,
                            ),
                          ),
                          child: InkWell(
                            onTap: () {
                              setState(() {
                                _cartItems[index].quantity += 1;
                              });
                            },
                            child: const Icon(
                              Icons.add,
                              size: Sizes.size18,
                              color: Colors.black,
                            ),
                          ),
                        ),
                        Container(
                          padding: const EdgeInsets.all(
                            Sizes.size6,
                          ),
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(
                              Sizes.size6,
                            ),
                          ),
                          child: InkWell(
                            onTap: () {
                              setState(() {
                                _cartItems.remove(_cartItems[index]);
                              });
                            },
                            child: const Icon(
                              Icons.close_rounded,
                              size: Sizes.size16,
                              color: Colors.black,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
        bottomNavigationBar: BottomAppBar(
          surfaceTintColor: Colors.white,
          color: Colors.white,
          elevation: 5,
          shadowColor: Colors.black,
          padding: const EdgeInsets.symmetric(
            vertical: Sizes.size12,
            horizontal: Sizes.size20,
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              OutlinedButton(
                style: OutlinedButton.styleFrom(
                  side: BorderSide(
                    color: Colors.grey.shade400,
                  ),
                  padding: const EdgeInsets.symmetric(
                    horizontal: Sizes.size32,
                    vertical: Sizes.size12,
                  ),
                ),
                onPressed: _onOrderBtnTap,
                child: Text(
                  '바로구매',
                  style: TextStyle(
                    color: _cartItems.isEmpty
                        ? Theme.of(context).primaryColor
                        : Theme.of(context).primaryColorDark,
                    fontSize: Sizes.size18,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
              Gaps.h20,
              ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: _cartItems.isEmpty
                      ? Theme.of(context).primaryColor
                      : Theme.of(context).primaryColorDark,
                  padding: const EdgeInsets.symmetric(
                    horizontal: Sizes.size32,
                    vertical: Sizes.size12,
                  ),
                ),
                onPressed: _onCartBtnTap,
                child: const Text(
                  '장바구니',
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: Sizes.size18,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
