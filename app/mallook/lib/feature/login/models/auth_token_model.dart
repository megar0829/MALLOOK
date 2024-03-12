import 'package:mallook/constants/member_role.dart';

class AuthTokenModel {
  AuthTokenModel({
    String? accessToken,
    String? refreshToken,
    List<MemberRole>? roles,
  })  : _accessToken = accessToken,
        _refreshToken = refreshToken,
        _roles = roles;

  AuthTokenModel.fromJson(dynamic json) {
    _accessToken = json['accessToken'];
    _refreshToken = json['refreshToken'];
    _roles = json['roles'] != null ? addMatchingRoles(json['roles']) : [];
  }

  String? _accessToken;
  String? _refreshToken;
  List<MemberRole>? _roles;

  String? get accessToken => _accessToken;

  String? get refreshToken => _refreshToken;

  List<MemberRole>? get roles => _roles;

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

  MemberRole? _parseStringToMemberRole(String role) {
    switch (role) {
      case 'ANONYMOUS':
        return MemberRole.anonymous;
      case 'BASIC_USER':
        return MemberRole.basicUser;
      case 'USER':
        return MemberRole.user;
      case 'MANAGER':
        return MemberRole.manager;
      case 'ADMIN':
        return MemberRole.admin;
      default:
        return null;
    }
  }

  List<MemberRole> addMatchingRoles(List<dynamic> dynamicRoles) {
    List<MemberRole> parsedRoles = [];
    for (String stringRole in dynamicRoles.cast<String>()) {
      MemberRole? memberRole = _parseStringToMemberRole(stringRole);
      if (memberRole != null) {
        parsedRoles.add(memberRole);
      }
    }
    return parsedRoles;
  }
}
