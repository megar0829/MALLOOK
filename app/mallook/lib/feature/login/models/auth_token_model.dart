class AuthTokenModel {
  final String accessToken, refreshToken;

  AuthTokenModel.fromJson(Map<String, dynamic> json)
      : accessToken = json['accessToken'],
        refreshToken = json['refreshToken'];
}
