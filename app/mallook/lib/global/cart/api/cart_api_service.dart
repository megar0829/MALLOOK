import 'package:mallook/config/dio_service.dart';
import 'package:mallook/global/cart/model/page_cart_item.dart';

class CartApiService {
  static final _dio = DioService();

  static Future<PageCartItem> getCartItems(int page) async {
    return await _dio.baseGet(
      path: "/api/carts",
      queryParameters: <String, num>{"page": page},
      fromJsonT: (json) => PageCartItem.fromJson(json),
    );
  }
}
