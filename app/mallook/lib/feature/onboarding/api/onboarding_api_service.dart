import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/onboarding/model/keyword.dart';

class OnboardingApiService {
  static final _dio = DioService();
  static Future<List<Keyword>> getOnboardingKeywords() async {
    List<Keyword> keywords = [];
    var response = await _dio.baseGet(
      path: "/api/keywords",
    );

    for (var r in response) {
      keywords.add(Keyword.fromJson(r));
    }

    return keywords;
  }
}
