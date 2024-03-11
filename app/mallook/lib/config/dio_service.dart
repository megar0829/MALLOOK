import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:mallook/constants/http_status.dart';
import 'package:mallook/feature/login/models/auth_token_model.dart';

class DioService {
  Dio? _authDio;
  static final DioService _instance = DioService._internal();
  final storage = const FlutterSecureStorage();
  factory DioService() {
    return _instance;
  }

  DioService._internal() {
    _authDio = Dio(BaseOptions(baseUrl: "https://j10a606.p.ssafy.io"));
    _authDio!.options.headers['userAgent'] =
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Whale/3.23.214.17 Safari/537.36';
    _authDio!.options.headers['Content-Type'] = 'application/json';

    _authDio!.interceptors.clear();
    _authDio!.interceptors.add(
      InterceptorsWrapper(
        onRequest: (options, handler) async {
          final accessToken = await storage.read(key: 'access-token');
          if (accessToken == null) {
            options.headers["Authorization"] = "";
            return handler.next(options);
          }

          options.headers['Authorization'] = 'Bearer $accessToken';
          return handler.next(options);
        },
        onError: (error, handler) async {
          if (error.response?.statusCode == 404) {
            final refreshToken = await storage.read(key: "refresh-token");

            if (refreshToken == null) {
              return handler.next(error);
            }

            var refreshDio = Dio();
            final response = await refreshDio.post(
              "/api/auth/token/refresh",
              data: jsonEncode(<String, String>{'refresh-token': refreshToken}),
            );

            if (response.statusCode == HttpStatus.SELECT_SUCCESS) {
              final AuthTokenModel newAuthTokenModel =
                  AuthTokenModel.fromJson(response.data["result"]);
              await storage.write(
                  key: "access-token", value: newAuthTokenModel.accessToken);
              await storage.write(
                  key: "refresh-token", value: newAuthTokenModel.refreshToken);

              error.requestOptions.headers['Authorization'] =
                  'Bearer ${newAuthTokenModel.accessToken}';
              return handler.resolve(await authDio.fetch(error.requestOptions));
            }
          }
          return handler.next(error);
        },
      ),
    );
  }

  Dio get authDio => _authDio!;
}
