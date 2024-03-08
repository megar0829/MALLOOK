import 'dart:async';

import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/api/home_api_service.dart';
import 'package:mallook/feature/home/widgets/my-script-box.dart';
import 'package:mallook/models/product.dart';
import 'package:mallook/models/script.dart';

class HomeMyScreen extends StatefulWidget {
  const HomeMyScreen({super.key});

  @override
  State<HomeMyScreen> createState() => _HomeMyScreenState();
}

class _HomeMyScreenState extends State<HomeMyScreen> {
  Future<Script> script = HomeApiService.getMySingleScript();
  Future<List<Product>> products = HomeApiService.getProducts();

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
              FutureBuilder(
                future: script,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    return MyScriptBox(script: snapshot.data!);
                  }
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                },
              ),
              Gaps.v6,
              FutureBuilder(
                future: products,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    return GridView.builder(
                      shrinkWrap: true,
                      // 수정: GridView를 SingleChildScrollView에 맞추기 위해 shrinkWrap을 true로 설정합니다.
                      physics: const NeverScrollableScrollPhysics(),
                      // 수정: GridView 스크롤 비활성화
                      gridDelegate:
                          const SliverGridDelegateWithFixedCrossAxisCount(
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
                      itemCount: snapshot.data!.length,
                    );
                  }
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
