import 'dart:math';

import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/home/models/script.dart';

class HomeApiService {
  static const String baseUrl = "";

  static Future<Script> getMySingleScript() async {
    final url = Uri.parse('$baseUrl');
    Script script = Script(
      id: 2,
      name: "시원하면서 캐주얼하고 편한 스타일의 옷들을 골라봤어요!!",
      heartCount: 3,
      imageUrl:
          "https://image.msscdn.net/images/goods_img/20231020/3645434/3645434_16992306653497_320.jpg",
    );

    return script;
  }

  static Future<List<Product>> getProducts(int page) async {
    List<Product> productInstances = [];
    final url = Uri.parse('$baseUrl');

    for (int i = 0; i < 20; i++) {
      productInstances.add(
        Product(
          name: "$i [5,000원 결제혜택] 워싱 스티치 데님자켓 (BLACK) sfsfsfsfsfsfsfsfsfsfsf$i",
          price: Random().nextInt(300000),
          image:
              'https://image.msscdn.net/images/goods_img/20240108/3781888/3781888_17067719220154_320.jpg',
          brandName: "아디다스",
        ),
      );
    }
    await Future.delayed(const Duration(seconds: 2)); // 예시로 1초 대기
    return productInstances;
  }

  /// 랭킹에 따른 스크립트
  /// TODO: 스크립트 작성자 정보 추가 필요
  static Future<List<Script>> getRankingScripts(int page) async {
    List<Script> scriptInstances = [];

    for (int i = 0; i < 10; i++) {
      scriptInstances.add(Script(
          id: 122,
          name: "시원하면서도 따뜻한 사계절 코디 발랄하게 즐겨보아요",
          heartCount: 3,
          imageUrl:
              "https://image.msscdn.net/images/goods_img/20231020/3645434/3645434_16992306653497_320.jpg"));
    }

    await Future.delayed(const Duration(seconds: 2));
    return scriptInstances;
  }
}
