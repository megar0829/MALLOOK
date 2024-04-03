import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/product/model/product_cursor_response.dart';

class CategoryApiService {
  static final _dio = DioService();

  static Future<ProductCursorResponse> getCategoryProducts(
      {required String cursor,
      required String primary,
      required String secondary}) async {
    Map<String, String?> queryParameters = {
      "primary": primary,
    };

    if (cursor.isNotEmpty) {
      queryParameters["cursor"] = cursor;
    }
    if (secondary.isNotEmpty && secondary != "전체") {
      queryParameters["secondary"] = secondary;
    }

    return await _dio.baseGet(
      path: "/api/products",
      queryParameters: queryParameters,
      fromJsonT: (json) => ProductCursorResponse.fromJson(json),
    );
  }
}
