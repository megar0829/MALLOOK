import 'package:get/get.dart';
import 'package:mallook/feature/home/models/product.dart';

class CartItem {
  Product product;
  int quantity;
  String size;
  String color;

  CartItem({
    required this.product,
    required this.quantity,
    required this.size,
    required this.color,
  });
}

class CartController extends GetxController {
  final _items = <String, CartItem>{}.obs;
  final _totalPrice = 0.obs;
  final _totalCount = 0.obs;

  Map<String, CartItem> get items => _items;

  RxInt get totalQuantity => _totalCount;

  RxInt get totalPrice => _totalPrice;

  void addItem({
    required String productId,
    required CartItem cartItem,
  }) {
    _items[productId] = cartItem;
    totalQuantity.value += 1;
    totalPrice.value += cartItem.product.price * cartItem.quantity;
    update(); // 상태 업데이트
  }

  void removeItem(String productId) {
    var cartItem = _items.remove(productId);
    if (cartItem == null) return;
    totalQuantity.value -= 1;
    totalPrice.value -= cartItem.product.price * cartItem.quantity;
    update(); // 상태 업데이트
  }
}
