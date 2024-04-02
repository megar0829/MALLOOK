import 'dart:math';

import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/script/model/script.dart';

class ScriptService {
  static final _dio = DioService();

  static Future<String> createScriptByKeywords({required dynamic data}) async {
    return await _dio.basePost(
      path: "/api/scripts",
      postData: data,
    );
  }

  static Future<Script> getScriptDetail(int scriptId) async {
    await Future.delayed(const Duration(milliseconds: 500));
    return Script(
      id: Random().nextInt(200),
      name: "봄을 입는 옷과 함께 초록빛 나는 스타일",
      heartCount: Random().nextInt(10000),
      imageUrl:
          "https://image.msscdn.net/images/goods_img/20230111/3019671/3019671_17083089992446_500.jpg",
    );
  }

// static Future<List<Product>> getKeywordProducts(List<String> keywords) {
//   return List<Product>[];
// }
}
