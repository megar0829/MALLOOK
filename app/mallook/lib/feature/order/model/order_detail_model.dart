import 'package:mallook/global/cart/cart_controller.dart';

class OrderDetail {
  final num id;
  final List<CartItem> items;
  final int totalPrice;
  final int totalFee;
  final int totalCount;

  OrderDetail(
      {required this.id,
      required this.items,
      required this.totalPrice,
      required this.totalFee,
      required this.totalCount});
}
