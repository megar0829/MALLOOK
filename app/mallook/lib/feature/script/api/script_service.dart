import 'dart:math';

import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/script/model/script.dart';
import 'package:mallook/feature/script/model/script_detail.dart';

class ScriptService {
  static final _dio = DioService();

  static Future<String> createScriptByKeywords({required dynamic data}) async {
    return await _dio.basePost(
      path: "/api/scripts",
      postData: data,
    );
  }

  static Future<ScriptDetail> getScriptDetail(int scriptId) async {
    return await _dio.baseGet<ScriptDetail>(
      path: "/api/scripts/$scriptId",
      fromJsonT: (json) => ScriptDetail.fromJson(json),
    );
  }

// static Future<List<Product>> getKeywordProducts(List<String> keywords) {
//   return List<Product>[];
// }
}
