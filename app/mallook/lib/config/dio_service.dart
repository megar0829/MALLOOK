import 'package:dio/dio.dart';

class DioService {
  Dio? _dio;

  static final DioService _instance = DioService._internal();

  factory DioService() {
    return _instance;
  }

  DioService._internal() {
    _dio = Dio(BaseOptions(baseUrl: "http://10.0.2.2:8080"));
    _dio!.options.headers['userAgent'] =
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Whale/3.23.214.17 Safari/537.36';
    _dio!.options.headers['Content-Type'] = 'application/json';
    // 필요하다면 다른 전역 설정들을 여기에 추가할 수 있습니다.
  }

  Dio get dio => _dio!;
}
