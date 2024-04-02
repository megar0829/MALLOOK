import 'dart:math';

import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/product/model/product.dart';
import 'package:mallook/feature/product/model/product_page_response.dart';
import 'package:mallook/feature/script/model/cursor_script.dart';
import 'package:mallook/feature/script/model/script.dart';

class HomeApiService {
  static final _dio = DioService();
  static Future<Script> getMySingleScript() async {
    return await _dio.baseGet<Script>(
      path: "/api/scripts/my-latest",
      fromJsonT: (json) => Script.fromJson(json),
    );
  }

  static Future<ProductPageResponse> getPopularProducts(int page) async {
    return await _dio.baseGet<ProductPageResponse>(
      path: "/api/products/popular",
      queryParameters: <String, num>{
        "page": page,
      },
      fromJsonT: (json) => ProductPageResponse.fromJson(json),
    );
  }

  /// 랭킹에 따른 스크립트
  /// TODO: 스크립트 작성자 정보 추가 필요
  static Future<CursorScript> getRankingScripts(int cursor) async {
    return await _dio.baseGet<CursorScript>(
      path: "/api/scripts/all",
      queryParameters: <String, num>{
        "cursor": cursor,
      },
      fromJsonT: (json) => CursorScript.fromJson(json),
    );
  }
}
