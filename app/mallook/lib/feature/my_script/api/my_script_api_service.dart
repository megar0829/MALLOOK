import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/script/model/page_script.dart';

class MyScriptApiService {
  static final _dio = DioService();
  static Future<PageScript> getMyScripts(int? cursor) async {
    Map<String, num> query = {};
    if (cursor != null) {
      query['cursor'] = cursor;
    }
    return await _dio.baseGet<PageScript>(
      path: "/api/scripts",
      queryParameters: query,
      fromJsonT: (json) => PageScript.fromJson(json),
    );
  }
}
