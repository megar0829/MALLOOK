import 'package:mallook/config/dio_service.dart';

class SearchApiService {
  static final _dio = DioService();

  static Future<List<String>> getHotKeywords() async {
    var result = await _dio.baseGet(
      path: '/api/keywords/top-ten',
    );
    print('getHotKeywords $result');

    return [];
  }
}
