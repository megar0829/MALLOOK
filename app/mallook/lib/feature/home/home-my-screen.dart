import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/widgets/my-script-box.dart';
import 'package:mallook/models/product.dart';
import 'package:mallook/models/script.dart';

class HomeMyScreen extends StatefulWidget {
  const HomeMyScreen({super.key});

  @override
  State<HomeMyScreen> createState() => _HomeMyScreenState();
}

class _HomeMyScreenState extends State<HomeMyScreen> {
  late Script script;
  late List<Product> products;

  @override
  void initState() {
    super.initState();
    script = Script(
      id: "oapfjsapfniaspfjsapfnsaf",
      name: "시원하면서 캐주얼하고 편한",
      imageUrl:
          "https://image.msscdn.net/images/goods_img/20231020/3645434/3645434_16992306653497_320.jpg",
    );
    products = [];
    for (int i = 0; i < 200; i++) {
      products.add(Product(
        id: i,
        name: '상품$i',
        price: i * 300,
        fee: i * 50,
        image: 'sofnspfnsfpnsf',
      ));
    }
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Padding(
        padding: const EdgeInsets.symmetric(
          horizontal: Sizes.size20,
        ),
        child: SingleChildScrollView(
          child: Column(
            children: [
              Gaps.v4,
              MyScriptBox(
                script: script,
              ),
              Gaps.v6,
              GridView.builder(
                shrinkWrap: true,
                // 수정: GridView를 SingleChildScrollView에 맞추기 위해 shrinkWrap을 true로 설정합니다.
                physics: NeverScrollableScrollPhysics(),
                // 수정: GridView 스크롤 비활성화
                gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 2,
                  crossAxisSpacing: 10,
                  mainAxisSpacing: 10,
                  childAspectRatio: 0.8,
                ),
                itemBuilder: (context, index) => Container(
                  decoration: BoxDecoration(
                    border: Border.all(
                      color: Colors.black,
                      width: Sizes.size1,
                    ),
                  ),
                  child: Text('$index'),
                ),
                itemCount: products.length,
              ),
            ],
          ),
        ),
      ),
    );
  }
}
