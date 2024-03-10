import 'dart:math';

import 'package:mallook/feature/home/models/thumbnail_product.dart';
import 'package:mallook/feature/home/models/script.dart';

class HomeApiService {
  static const String baseUrl = "";

  static Future<Script> getMySingleScript() async {
    final url = Uri.parse('$baseUrl');
    Script script = Script(
      id: "oapfjsapfniaspfjsapfnsaf",
      name: "시원하면서 캐주얼하고 편한 스타일의 옷들을 골라봤어요!!",
      imageUrl:
          "https://image.msscdn.net/images/goods_img/20231020/3645434/3645434_16992306653497_320.jpg",
    );
    return script;
  }

  static Future<List<ThumbnailProduct>> getProducts(int page) async {
    List<ThumbnailProduct> productInstances = [];
    final url = Uri.parse('$baseUrl');
    for (int i = 0; i < 20; i++) {
      productInstances.add(ThumbnailProduct(
        id: i,
        name: "$i [5,000원 결제혜택] 워싱 스티치 데님자켓 (BLACK) $i",
        price: Random().nextInt(300000),
        image:
            'https://image.msscdn.net/images/goods_img/20240108/3781888/3781888_17067719220154_320.jpg',
        discountRatio: Random().nextInt(100),
        shoppingMall: "mallook",
        shoppingMallImgUrl:
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRXpU79_HFRNHqW4SiJq2bGMZnf9svidTueYk3QjdY&usqp=CAE&s",
      ));
    }
    await Future.delayed(const Duration(seconds: 2)); // 예시로 1초 대기
    return productInstances;
    // throw Error();
  }
}
