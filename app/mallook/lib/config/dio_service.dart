import 'dart:collection';
import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:mallook/config/global_functions.dart';
import 'package:mallook/feature/login/models/auth_token_model.dart';

class DioService {
  Dio? _authDio;
  final _requestQueue = Queue<RequestOptions>();
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
          final storageToken = await storage.read(key: "token");
          if (storageToken == null) {
            throw Error();
          }
          final token = AuthTokenModel.fromJson(jsonDecode(storageToken));
          if (token.accessToken == null) {
            options.headers["Authorization"] = "";
            return handler.next(options);
          }

          options.headers['Authorization'] = 'Bearer ${token.accessToken}';
          return handler.next(options);
        },
        onResponse: (response, handler) async {
          handler.next(response);
        },
        onError: (error, handler) async {
          if (error.response?.statusCode == 401) {
            _requestQueue.add(error.requestOptions);

            final storageToken = await storage.read(key: "token");
            if (storageToken == null) {
              return;
            }
            final token = AuthTokenModel.fromJson(jsonDecode(storageToken));
            // refresh token이 없으면 로그인 페이지로 이동
            if (token.refreshToken == null) {
              moveToLoginScreen();
              return;
            }

            try {
              var refreshDio =
                  Dio(BaseOptions(baseUrl: "https://j10a606.p.ssafy.io"));
              final response = await refreshDio.post(
                "/api/auth/token/refresh",
                data: jsonEncode(
                    <String, String>{'refresh-token': token.refreshToken!}),
              );

              if (response.statusCode == 200) {
                final AuthTokenModel newAuthTokenModel =
                    AuthTokenModel.fromJson(response.data["result"]);
                await storage.write(
                    key: 'token',
                    value: jsonEncode(newAuthTokenModel.toJson()));

                _processRequestQueue(
                    newAuthTokenModel.accessToken!); // 저장된 요청 처리
                return;
              } else {
                moveToLoginScreen();
              }
            } catch (error) {
              moveToLoginScreen();
            }
          }
        },
      ),
    );
  }

  Dio get authDio => _authDio!;

  void _processRequestQueue(String accessToken) async {
    while (_requestQueue.isNotEmpty) {
      var requestOptions = _requestQueue.removeFirst();

      requestOptions.headers['Authorization'] = 'Bearer $accessToken';
      try {
        await _authDio!.fetch(requestOptions); // 요청 재실행
      } catch (e) {
        print("Failed to reprocess request: $e");
      }
    }
  }

  Future<T> convertedGet<T>(
    String path, {
    Map<String, dynamic>? queryParameters,
    required T Function(Map<String, dynamic>) fromJsonT,
  }) async {
    try {
      final response =
          await _authDio!.get(path, queryParameters: queryParameters);
      if (response.statusCode == 200) {
        int status = response.data['status'];
        String message = response.data['message'];
        T result = fromJsonT(response.data);
        return result;
      } else {
        throw Exception('Request failed with status: ${response.statusCode}');
      }
    } catch (e) {
      rethrow;
    }
  }
}
