import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/worldcup/model/worldcup_cody.dart';

class WorldcupApiService {
  static final _dio = DioService();

  static Future<List<WorldcupCody>> getWorldCupCodies() async {
    var items = await _dio.baseGet(
      path: "/api/styles/world-cup",
    );

    List<WorldcupCody> products = [];
    for (var item in items) {
      products.add(WorldcupCody.fromJson(item));
    }
    return products;
  }
}
