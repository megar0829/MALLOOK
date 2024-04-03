import 'package:get/get.dart';
import 'package:mallook/feature/login/login_screen.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';

void moveToLoginScreen() {
  Get.offAll(const LoginScreen());
}

void moveToNavigationScreen() {
  Get.to(() => const MainNavigationScreen());
}

String getLevelImage(int level) {
  switch (level) {
    case 1:
      return "assets/images/level/level1.png";
    case 2:
      return "assets/images/level/level1.png";
    case 3:
      return "assets/images/level/level1.png";
    case 4:
      return "assets/images/level/level1.png";
    case 5:
      return "assets/images/level/level1.png";
    case 6:
      return "assets/images/level/level1.png";
    case 7:
      return "assets/images/level/level1.png";
    default:
      return "assets/images/app_logo/logo_sm.png";
  }
}

String getStringLevelImage(String level) {
  switch (level) {
    case 'LEVEL1':
      return "assets/images/level/level1.png";
    case 'LEVEL2':
      return "assets/images/level/level1.png";
    case 'LEVEL3':
      return "assets/images/level/level1.png";
    case 'LEVEL4':
      return "assets/images/level/level1.png";
    case 'LEVEL5':
      return "assets/images/level/level1.png";
    case 'LEVEL6':
      return "assets/images/level/level1.png";
    case 'LEVEL7':
      return "assets/images/level/level1.png";
    default:
      return "assets/images/app_logo/logo_sm.png";
  }
}

num getLevelDiscountRatio(int level) {
  switch (level) {
    case 1:
      return 1;
    case 2:
      return 1;
    case 3:
      return 2;
    case 4:
      return 2;
    case 5:
      return 3;
    case 6:
      return 3;
    case 7:
      return 4;
    default:
      return 0;
  }
}

num getStringLevelDiscountRatio(String level) {
  switch (level) {
    case 'LEVEL1':
      return 1;
    case 'LEVEL2':
      return 1;
    case 'LEVEL3':
      return 2;
    case 'LEVEL4':
      return 2;
    case 'LEVEL5':
      return 3;
    case 'LEVEL6':
      return 3;
    case 'LEVEL7':
      return 4;
    default:
      return 0;
  }
}
