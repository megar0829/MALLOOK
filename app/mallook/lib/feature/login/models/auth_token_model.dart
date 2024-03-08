class AuthTokenModel {
  final String accessToken, refreshToken;

  AuthTokenModel({required this.accessToken, required this.refreshToken});

  @override
  String toString() {
    return 'AuthTokenModel{accessToken: $accessToken, refreshToken: $refreshToken}';
  }
}
