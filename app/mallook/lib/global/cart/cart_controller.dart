import 'package:get/get.dart';
import 'package:mallook/feature/home/models/product.dart';

class CartItem {
  final Product product;
  final int price;
  final int quantity;

  CartItem(
      {required this.product, required this.price, required this.quantity});
}

class CartController extends GetxController {
  final _items = <String, CartItem>{}.obs;
  final _totalPrice = 0.obs;
  final _totalCount = 0.obs;

  Map<String, CartItem> get items => _items;

  RxInt get totalQuantity => _totalCount;

  RxInt get totalPrice => _totalPrice;

  void addItem(String productId, Product product, int quantity) {
    _items[productId] = CartItem(
      product: product,
      price: product.price! * quantity,
      quantity: quantity,
    );
    totalQuantity.value += 1;
    totalPrice.value += product.price! * quantity;
    update(); // 상태 업데이트
  }

  void removeItem(String productId) {
    var cartItem = _items.remove(productId);
    if (cartItem == null) return;
    totalQuantity.value -= 1;
    totalPrice.value -= cartItem.price;
    update(); // 상태 업데이트
  }
}
