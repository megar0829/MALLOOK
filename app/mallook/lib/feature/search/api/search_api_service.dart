import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/search/models/popular_keyword.dart';

class SearchApiService {
  static final _dio = DioService();

  static Future<List<PopularKeyword>> getHotKeywords() async {
    List<PopularKeyword> pk = [];
    var result = await _dio.baseGet(
      path: '/api/keywords/top-ten',
    );

    for (var r in result) {
      pk.add(PopularKeyword.fromJson(r));
    }

    return pk;
  }
}
