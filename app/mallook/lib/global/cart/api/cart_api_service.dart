import 'package:mallook/config/dio_service.dart';
import 'package:mallook/global/cart/model/page_cart_item.dart';

class CartApiService {
  static final _dio = DioService();

  static Future<PageCartItem> getCartItems() async {
    return await _dio.baseGet(
      path: "/api/carts",
      fromJsonT: (json) => PageCartItem.fromJson(json),
    );
  }

  static Future<String> removeCartItem({required dynamic data}) async {
    return await _dio.baseDelete(
      path: "/api/carts",
      deleteData: data,
    );
  }

  static Future<String> addCartItem({required dynamic data}) async {
    return await _dio.basePost(
      path: "/api/carts",
      postData: data,
    );
  }

  static Future<String> removeAllCartItems({required dynamic data}) async {
    return await _dio.baseDelete(
      path: "/api/carts/carts",
      deleteData: data,
    );
  }
}
