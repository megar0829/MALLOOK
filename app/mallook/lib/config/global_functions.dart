import 'package:get/get.dart';
import 'package:mallook/feature/login/LoginScreen.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';

void moveToLoginScreen() {
  Get.offAll(const LoginScreen());
}

void moveToNavigationScreen() {
  Get.to(() => const MainNavigationScreen());
}
