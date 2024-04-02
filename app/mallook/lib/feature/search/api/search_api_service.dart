import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/onboarding/model/keyword.dart';

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
}
