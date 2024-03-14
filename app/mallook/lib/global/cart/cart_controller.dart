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

  @override
  String toString() {
    return 'CartItem{product: $product, quantity: $quantity, size: $size, color: $color}';
  }
}

class CartController extends GetxController {
  final _items = <CartItem>[].obs;
  final _totalPrice = 0.obs;
  final _totalCount = 0.obs;

  List<CartItem> get items => _items;

  RxInt get totalQuantity => _totalCount;

  RxInt get totalPrice => _totalPrice;

  void addItem({
    required String productId,
    required CartItem cartItem,
  }) {
    _items.add(cartItem);
    totalQuantity.value += 1;
    totalPrice.value += cartItem.product.price * cartItem.quantity;
    update(); // 상태 업데이트
  }

  void removeItem({required CartItem cartItem}) {
    var removedCartItem = _items.remove(cartItem);
    totalQuantity.value -= 1;
    totalPrice.value -= cartItem.product.price * cartItem.quantity;
    update(); // 상태 업데이트
  }
}
