import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/product/model/product_cursor_response.dart';
import 'package:mallook/feature/product/model/product_detail.dart';

class ProductApiService {
  static final _dio = DioService();

  static Future<ProductDetail> getProductDetail(String productId) async {
    return await _dio.baseGet<ProductDetail>(
      path: "/api/products/$productId",
      fromJsonT: (json) => ProductDetail.fromJson(json),
    );
  }

  static Future<ProductCursorResponse> getProductsByScript(
      {required int scriptId, String? cursor}) async {
    Map<String, dynamic> query = {};
    if (cursor != null && cursor.isNotEmpty) {
      query['cursor'] = cursor;
    }
    return await _dio.baseGet<ProductCursorResponse>(
      path: "/api/scripts/$scriptId/product-detail",
      queryParameters: query,
      fromJsonT: (json) => ProductCursorResponse.fromJson(json),
    );
  }
}
