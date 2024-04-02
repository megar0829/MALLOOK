import 'package:get/get.dart';
import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/onboarding/model/keyword.dart';
import 'package:mallook/feature/product/model/product.dart';
import 'package:mallook/feature/product/model/product_cursor_response.dart';

class SearchApiService {
  static final _dio = DioService();

  static Future<List<Keyword>> getHotKeywords() async {
    List<Keyword> popluarKeywords = [];
    var result = await _dio.baseGet(
      path: '/api/keywords/top-ten',
    );

    for (var r in result) {
      popluarKeywords.add(Keyword.fromJson(r));
    }

    return popluarKeywords;
  }

  static Future<ProductCursorResponse> getSearchedProducts(
      {String? name, String? cursor, required List<Keyword> keywords}) async {
    Map<String, dynamic> query = {};
    if (name != null && name.trim().isNotEmpty) {
      query['name'] = name;
    }
    if (cursor != null && cursor.trim().isNotEmpty) {
      query['cursor'] = cursor;
    }
    if (keywords.isNotEmpty) {
      query['keywords'] = keywords.map((e) => e.name).toList();
    }

    return await _dio.baseGet<ProductCursorResponse>(
      path: "/api/products/search",
      queryParameters: query,
      fromJsonT: (json) => ProductCursorResponse.fromJson(json),
    );
  }
}
