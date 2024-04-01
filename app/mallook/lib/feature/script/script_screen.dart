import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/api/home_api_service.dart';
import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/home/models/script.dart';
import 'package:mallook/feature/script/api/script_service.dart';
import 'package:mallook/feature/script/widget/script_img_widget.dart';
import 'package:mallook/feature/script/widget/script_product_widget.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';
import 'package:mallook/global/widget/custom_circular_wait_widget.dart';
import 'package:mallook/global/widget/home_icon_button.dart';

class ScriptScreen extends StatefulWidget {
  final Script script;

  const ScriptScreen({super.key, required this.script});

  @override
  State<ScriptScreen> createState() => _ScriptScreenState();
}

class _ScriptScreenState extends State<ScriptScreen>
    with SingleTickerProviderStateMixin {
  final ScrollController _scrollController = ScrollController();
  late final Future<Script> _script =
      ScriptService.getScriptDetail(widget.script.id!);
  final List<Product> _products = [];
  int _productPage = 0;
  bool _isProductLoading = false;

  @override
  void initState() {
    super.initState();

    _scrollController.addListener(() {
      if (_scrollController.offset >=
              (_scrollController.position.maxScrollExtent * 0.9) &&
          !_scrollController.position.outOfRange) {
        _loadMoreProducts();
      }
    });
    _loadMoreProducts();
  }

  @override
  void dispose() {
    _scrollController.dispose();
    super.dispose();
  }

  void _loadMoreProducts() async {
    List<String> keywords = ["예쁜", "섹시"]; // TODO: script keyword
    if (!_isProductLoading && keywords.isNotEmpty) {
      if (mounted) {
        setState(() {
          _isProductLoading = true;
        });

        var loadedProducts = await HomeApiService.getProducts(_productPage);
        if (mounted) {
          setState(() {
            _products.addAll(loadedProducts);
            _productPage++;
            _isProductLoading = false;
          });
        }
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        surfaceTintColor: Colors.white,
        elevation: 1,
        shadowColor: Colors.grey.shade400,
        title: const Text(
          '스타일',
          style: TextStyle(
            color: Colors.black,
            fontSize: Sizes.size18,
            fontWeight: FontWeight.bold,
          ),
        ),
        actions: const [
          HomeIconButton(),
          Gaps.h10,
          CartIconButton(),
          Gaps.h24,
        ],
      ),
      body: FutureBuilder(
        future: _script,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return SingleChildScrollView(
              controller: _scrollController,
              child: Padding(
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size10,
                  horizontal: Sizes.size24,
                ),
                child: Column(
                  children: [
                    ScriptImgWidget(
                      script: snapshot.data!,
                    ),
                    Gaps.v4,
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Row(
                          children: [
                            CircleAvatar(
                              backgroundColor: Colors.white.withOpacity(0.7),
                              child: FaIcon(
                                FontAwesomeIcons.solidUser,
                                color: Theme.of(context).primaryColorDark,
                                size: Sizes.size18,
                              ),
                            ),
                            Gaps.h4,
                            const Text(
                              "아바타",
                              maxLines: 2,
                              style: TextStyle(
                                color: Colors.black,
                                fontSize: Sizes.size18,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        ),
                        Row(
                          children: [
                            const FaIcon(
                              FontAwesomeIcons.heart,
                              color: Colors.red,
                              size: Sizes.size24,
                            ),
                            Gaps.h8,
                            Text(
                              '${snapshot.data!.heartCount!}',
                              style: const TextStyle(
                                color: Colors.black87,
                                fontSize: Sizes.size18,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        )
                      ],
                    ),
                    Divider(
                      color:
                          Theme.of(context).primaryColorLight.withOpacity(0.4),
                    ),
                    Text(
                      snapshot.data!.name!,
                      maxLines: 5,
                      style: const TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: Sizes.size18,
                      ),
                    ),
                    Gaps.v6,
                    Divider(
                      color: Theme.of(context).primaryColorLight,
                    ),
                    Gaps.v10,
                    ListView.separated(
                      shrinkWrap: true,
                      physics: const NeverScrollableScrollPhysics(),
                      itemBuilder: (context, index) => ScriptProductWidget(
                        product: _products[index],
                      ),
                      separatorBuilder: (context, index) => Gaps.v8,
                      itemCount: _products.length,
                    ),
                    if (_isProductLoading) CustomCircularWaitWidget(),
                  ],
                ),
              ),
            );
          } else {
            return CustomCircularWaitWidget();
          }
        },
      ),
    );
  }
}
