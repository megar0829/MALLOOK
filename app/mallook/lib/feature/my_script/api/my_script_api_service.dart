import 'dart:math';

import 'package:mallook/feature/home/models/script.dart';

class MyScriptApiService {
  static const String baseUrl = "";

  static List<Script> sampleScripts = [
    Script(
      id: Random().nextInt(200),
      name: "시원하면서 캐주얼하고 편한 스타일의 옷 패션",
      heartCount: Random().nextInt(10000),
      imageUrl:
          "https://image.msscdn.net/images/goods_img/20231020/3645434/3645434_16992306653497_320.jpg",
    ),
    Script(
      id: Random().nextInt(200),
      name: "하늘하늘 한 봄 나들이 패션",
      heartCount: Random().nextInt(10000),
      imageUrl:
          "https://image.msscdn.net/images/goods_img/20240125/3823573/3823573_17089046754777_500.jpg",
    ),
    Script(
      id: Random().nextInt(200),
      name: "이제 봄인데 나들이 갈 준비 해야겠죠?",
      heartCount: Random().nextInt(10000),
      imageUrl:
          "https://image.msscdn.net/images/goods_img/20240131/3836128/3836128_17066900628813_500.jpg",
    ),
    Script(
      id: Random().nextInt(200),
      name: "봄을 입는 옷과 함께 초록빛 나는 스타일",
      heartCount: Random().nextInt(10000),
      imageUrl:
          "https://image.msscdn.net/images/goods_img/20230111/3019671/3019671_17083089992446_500.jpg",
    ),
  ];

  static Future<List<Script>> getMyScripts(int page) async {
    List<Script> scriptInstances = [];

    for (int i = 0; i < 10; i++) {
      scriptInstances.add(sampleScripts[i % sampleScripts.length]);
    }

    await Future.delayed(const Duration(milliseconds: 500));
    return scriptInstances;
  }
}
