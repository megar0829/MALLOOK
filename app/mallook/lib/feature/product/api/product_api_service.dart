import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/product/model/product_detail.dart';

class ProductApiService {
  static final _dio = DioService();

  static Future<ProductDetail> getProductDetail(String productId) async {
    print("productId: $productId");
    return await _dio.baseGet<ProductDetail>(
      path: "/api/products/$productId",
      fromJsonT: (json) => ProductDetail.fromJson(json),
    );
  }
}
