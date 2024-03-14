import 'dart:math';

import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/home/models/script.dart';

class HomeApiService {
  static const String baseUrl = "";
  static List<Product> sampleProducts = [
    Product(
      name: "[5,000원 결제혜택] 워싱 스티치 데님자켓 (BLACK)",
      price: Random().nextInt(300000),
      image:
          'https://image.msscdn.net/images/goods_img/20240108/3781888/3781888_17067719220154_320.jpg',
      brandName: "아디다스",
    ),
    Product(
      name: "데일리 바이오 셔츠 - 10 COLOR",
      price: Random().nextInt(300000),
      image:
          'https://image.msscdn.net/images/goods_img/20221226/2996536/2996536_16919781777813_125.jpg',
      brandName: "수아레",
    ),
    Product(
      name: "말티즈 아카이브 맨투맨 라이트옐로우",
      price: Random().nextInt(300000),
      image:
          'https://image.msscdn.net/images/goods_img/20240117/3800916/3800916_17061843713296_500.jpg',
      brandName: "와이케이",
    ),
    Product(
      name: "3S 새틴 긴팔 티 - 블랙 / IR6099",
      price: Random().nextInt(300000),
      image:
          'https://image.msscdn.net/images/goods_img/20240215/3873779/3873779_17080546256622_500.jpg',
      brandName: "아디다스",
    ),
    Product(
      name: "배드가이 데님 웨스턴 셔츠 워시드 블루",
      price: Random().nextInt(300000),
      image:
          'https://image.msscdn.net/images/goods_img/20220124/2322107/2322107_1_500.jpg',
      brandName: "엘무드",
    ),
    Product(
      name: "린넨 코튼 블렌드 브이넥 니트 (라이트그레이)",
      price: Random().nextInt(300000),
      image:
          'https://image.msscdn.net/images/goods_img/20240215/3871784/3871784_17080516684125_500.jpg',
      brandName: "쿠어",
    ),
  ];

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

  static Future<Script> getMySingleScript() async {
    final url = Uri.parse('$baseUrl');
    Script script = Script(
      id: Random().nextInt(200),
      name: "하늘하늘 한 봄 나들이 패션",
      heartCount: Random().nextInt(10000),
      imageUrl:
          "https://image.msscdn.net/images/goods_img/20240125/3823573/3823573_17089046754777_500.jpg",
    );

    return script;
  }

  static Future<List<Product>> getProducts(int page) async {
    List<Product> productInstances = [];
    final url = Uri.parse('$baseUrl');

    for (int i = 0; i < 20; i++) {
      productInstances.add(sampleProducts[i % sampleProducts.length]);
    }
    await Future.delayed(const Duration(milliseconds: 500)); // 예시로 1초 대기
    return productInstances;
  }

  /// 랭킹에 따른 스크립트
  /// TODO: 스크립트 작성자 정보 추가 필요
  static Future<List<Script>> getRankingScripts(int page) async {
    List<Script> scriptInstances = [];

    for (int i = 0; i < 10; i++) {
      scriptInstances.add(sampleScripts[i % sampleScripts.length]);
    }

    await Future.delayed(const Duration(milliseconds: 500));
    return scriptInstances;
  }
}
