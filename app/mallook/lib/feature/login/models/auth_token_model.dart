class AuthTokenModel {
  AuthTokenModel({
    String? accessToken,
    String? refreshToken,
    List<String>? roles,
  })  : _accessToken = accessToken,
        _refreshToken = refreshToken,
        _roles = roles;

  AuthTokenModel.fromJson(dynamic json) {
    _accessToken = json['accessToken'];
    _refreshToken = json['refreshToken'];
    _roles = json['roles'] != null ? json['roles'].cast<String>() : [];
  }

  String? _accessToken;
  String? _refreshToken;
  List<String>? _roles;

  String? get accessToken => _accessToken;

  String? get refreshToken => _refreshToken;

  List<String>? get roles => _roles;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['accessToken'] = _accessToken;
    map['refreshToken'] = _refreshToken;
    map['roles'] = _roles;
    return map;
  }

  @override
  String toString() {
    return 'AuthTokenModel{_accessToken: $_accessToken, _refreshToken: $_refreshToken, _roles: $_roles}';
  }
}
