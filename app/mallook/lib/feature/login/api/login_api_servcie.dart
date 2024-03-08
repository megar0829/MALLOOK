import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:kakao_flutter_sdk_user/kakao_flutter_sdk_user.dart';
import 'package:mallook/constants/http_status.dart';
import 'package:mallook/feature/login/models/auth_token_model.dart';

class LoginApiService {
  static const String baseUrl = "http://localhost:8080";

  static Future<AuthTokenModel> getAuthToken(OAuthToken oAuthToken) async {
    final url = Uri.parse("$baseUrl/api/auth/login/kakao");
    final response = await http.post(
      url,
      body: jsonEncode(<String, String>{'accessToken': oAuthToken.accessToken}),
    );

    if (response.statusCode == HttpStatus.SELECT_SUCCESS) {
      final baseResponse = jsonDecode(response.body);
      final int status = baseResponse['status'];
      final String message = baseResponse['message'];
      final result = baseResponse['result'];

      if (!(status == HttpStatus.SELECT_SUCCESS ||
          status == HttpStatus.INSERT_SUCCESS)) {
        throw Error();
      }
      return AuthTokenModel.fromJson(result);
    }
    throw Error();
  }
}
