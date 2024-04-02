import 'package:get/get.dart';
import 'package:mallook/global/cart/api/cart_api_service.dart';
import 'package:mallook/global/cart/model/page_cart_item.dart';

class CartController extends GetxController {
  final _items = <CartItem>[].obs;
  final _totalPrice = 0.obs;
  final _totalCount = 0.obs;

  List<CartItem> get items => _items;

  RxInt get totalQuantity => _totalCount;

  RxInt get totalPrice => _totalPrice;

  void loadCartItems() async {
    var pageCartItem = await CartApiService.getCartItems();

    _items.clear();
    _items.addAll(pageCartItem.content ?? []);
    _updatePriceAndQuantity(items);
  }

  void _updatePriceAndQuantity(List<CartItem> items) {
    _totalPrice.value = 0;
    _totalCount.value = 0;

    for (var item in items) {
      _totalCount.value += item.count!;
      _totalPrice.value += item.price! * item.count!;
    }
  }

  void updateAllCartItems() async {
    for (var item in items) {
      await CartApiService.addCartItem(data: <String, dynamic>{
        "productId": item.productId,
        "count": item.count,
        "size": item.size,
        "price": item.price,
        "color": item.color,
        "fee": item.fee,
      });
    }
  }

  void addItem({
    required CartItem cartItem,
  }) async {
    _items.add(cartItem);
    totalQuantity.value += 1;
    totalPrice.value += cartItem.price! * cartItem.count!;
    update(); // 상태 업데이트
  }

  void removeItem({required CartItem cartItem}) async {
    var removedCartItem = _items.remove(cartItem);
    totalQuantity.value -= 1;
    totalPrice.value -= cartItem.price! * cartItem.count!;
    update(); // 상태 업데이트
  }
}
