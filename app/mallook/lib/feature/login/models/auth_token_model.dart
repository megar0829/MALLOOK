import 'dart:convert';

class AuthTokenModel {
  String? _accessToken;
  String? _refreshToken;
  List<String>? _roles;

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
    _roles = [];
    if (json['roles'] != null) {
      if (json['roles'] is String) {
        _roles = [json['roles']];
      } else {
        print('bbbbbbbbbbb');
        _roles = List<String>.from(json['roles']);
      }
    }
  }

  String? get accessToken => _accessToken;

  String? get refreshToken => _refreshToken;
  List<String>? get roles => _roles;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['accessToken'] = _accessToken;
    map['refreshToken'] = _refreshToken;
    map['roles'] = jsonEncode(_roles);
    return map;
  }

  @override
  String toString() {
    return 'AuthTokenModel{_accessToken: $_accessToken, _refreshToken: $_refreshToken} _roles: $roles';
  }
}
