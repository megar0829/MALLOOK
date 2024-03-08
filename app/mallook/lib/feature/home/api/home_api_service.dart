import 'package:mallook/models/product.dart';
import 'package:mallook/models/script.dart';

class HomeApiService {
  static const String baseUrl = "";

  static Future<Script> getMySingleScript() async {
    final url = Uri.parse('$baseUrl');
    Script script = Script(
      id: "oapfjsapfniaspfjsapfnsaf",
      name: "시원하면서 캐주얼하고 편한",
      imageUrl:
          "https://image.msscdn.net/images/goods_img/20231020/3645434/3645434_16992306653497_320.jpg",
    );
    return script;
  }

  static Future<List<Product>> getProducts() async {
    List<Product> productInstances = [];
    final url = Uri.parse('$baseUrl');
    // final response = await http.get(url);
    // if (response.statusCode == 200) {
    //   final List<dynamic> webtoons = jsonDecode(response.body);
    //   for (var webtoon in webtoons) {
    //     webtoonInstances.add(WebtoonModel.fromJson(webtoon));
    //   }
    //   return webtoonInstances;
    // }
    for (int i = 0; i < 200; i++) {
      productInstances.add(Product(
        id: i,
        name: '상품$i',
        price: i * 300,
        fee: i * 50,
        image: 'sofnspfnsfpnsf',
      ));
    }
    return productInstances;
    // throw Error();
  }
}
