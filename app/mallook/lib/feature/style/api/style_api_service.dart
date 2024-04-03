import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/style/model/page_style_response.dart';

class StyleApiService {
  static final _dio = DioService();

  static Future<PageStyleResponse> getPageStyle({required int cursor}) async {
    Map<String, dynamic> query = {};
    query['cursor'] = cursor;

    return await _dio.baseGet<PageStyleResponse>(
      path: "/api/styles",
      queryParameters: query,
      fromJsonT: (json) => PageStyleResponse.fromJson(json),
    );
  }
}
