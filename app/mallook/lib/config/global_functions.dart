import 'package:get/get.dart';
import 'package:mallook/constants/member_role.dart';
import 'package:mallook/feature/login/LoginScreen.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';

void moveToLoginScreen() {
  Get.offAll(const LoginScreen());
}

void moveToNavigationScreen() {
  Get.to(() => const MainNavigationScreen());
}

MemberRole? _parseStringToMemberRole(String role) {
  switch (role) {
    case 'anonymous':
      return MemberRole.anonymous;
    case 'basicUser':
      return MemberRole.basicUser;
    case 'user':
      return MemberRole.user;
    case 'manager':
      return MemberRole.manager;
    case 'admin':
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
