

class AuthTokenModel {
  String? _accessToken;
  String? _refreshToken;

  AuthTokenModel({
    String? accessToken,
    String? refreshToken,
  })  : _accessToken = accessToken,
        _refreshToken = refreshToken;

  AuthTokenModel.fromJson(dynamic json) {
    _accessToken = json['accessToken'];
    _refreshToken = json['refreshToken'];
  }

  String? get accessToken => _accessToken;

  String? get refreshToken => _refreshToken;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['accessToken'] = _accessToken;
    map['refreshToken'] = _refreshToken;
    return map;
  }

  @override
  String toString() {
    return 'AuthTokenModel{_accessToken: $_accessToken, _refreshToken: $_refreshToken}';
  }
}
