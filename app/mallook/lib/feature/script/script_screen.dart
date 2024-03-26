import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/home/api/home_api_service.dart';
import 'package:mallook/feature/home/models/product.dart';
import 'package:mallook/feature/home/models/script.dart';
import 'package:mallook/feature/home/widgets/custom_circular_wait_widget.dart';
import 'package:mallook/feature/script/api/script_service.dart';
import 'package:mallook/feature/script/widget/script_img_widget.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';
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
    List<String> keywords = ["예쁜", "섹시"];
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
            print('sofnsofn ${_products.length}');
          });
        }
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
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
          Gaps.h20,
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
                    Gaps.v10,
                    Text(
                      snapshot.data!.name!,
                      maxLines: 5,
                      style: const TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: Sizes.size18,
                      ),
                    ),
                    Gaps.v10,
                    Divider(
                      color: Colors.grey.shade200,
                    ),
                    Gaps.v10,
                    ListView.separated(
                      shrinkWrap: true,
                      physics: const NeverScrollableScrollPhysics(),
                      itemBuilder: (context, index) => Row(
                        children: [
                          Container(
                            width: 120,
                            height: 120,
                            clipBehavior: Clip.hardEdge,
                            decoration: BoxDecoration(
                              border: Border.all(
                                color: Colors.grey.shade300,
                                width: Sizes.size1,
                              ),
                              borderRadius: BorderRadius.circular(
                                Sizes.size20,
                              ),
                            ),
                            child: Image.network(
                              _products[index].image!,
                              fit: BoxFit.cover,
                            ),
                          ),
                          Gaps.h8,
                          Expanded(
                            child: Container(
                              height: 120,
                              padding: const EdgeInsets.symmetric(
                                vertical: Sizes.size8,
                                horizontal: Sizes.size10,
                              ),
                              clipBehavior: Clip.hardEdge,
                              decoration: BoxDecoration(
                                border: Border.all(
                                  color: Colors.grey.shade300,
                                  width: Sizes.size1,
                                ),
                                borderRadius: BorderRadius.circular(
                                  Sizes.size20,
                                ),
                              ),
                              child: Column(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceBetween,
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    _products[index].name,
                                    maxLines: 2,
                                    overflow: TextOverflow.ellipsis,
                                    style: const TextStyle(
                                      color: Colors.black,
                                      fontWeight: FontWeight.bold,
                                      fontSize: Sizes.size14,
                                    ),
                                  ),
                                  Gaps.h4,
                                  Text(
                                    '${_products[index].price}₩',
                                    style: const TextStyle(
                                      color: Colors.black,
                                      fontSize: Sizes.size14,
                                    ),
                                  ),
                                  Gaps.h4,
                                  const Row(
                                    mainAxisAlignment:
                                        MainAxisAlignment.spaceBetween,
                                    children: [
                                      Row(
                                        children: [
                                          CircleAvatar(),
                                          Gaps.h4,
                                          Text(
                                            'sfsfs',
                                            style: TextStyle(
                                              color: Colors.black,
                                              fontSize: Sizes.size14,
                                              fontWeight: FontWeight.bold,
                                            ),
                                          ),
                                        ],
                                      ),
                                      Column(
                                        children: [
                                          FaIcon(
                                            FontAwesomeIcons.heart,
                                            size: Sizes.size16,
                                            color: Colors.red,
                                          ),
                                          Text(
                                            '121',
                                            style: TextStyle(
                                              color: Colors.black,
                                              fontSize: Sizes.size12,
                                              fontWeight: FontWeight.bold,
                                            ),
                                          )
                                        ],
                                      )
                                    ],
                                  ),
                                ],
                              ),
                            ),
                          )
                        ],
                      ),
                      separatorBuilder: (context, index) => Gaps.v8,
                      itemCount: _products.length,
                    ),
                    if (_isProductLoading) const CustomCircularWaitWidget(),
                  ],
                ),
              ),
            );
          } else {
            return const CustomCircularWaitWidget();
          }
        },
      ),
    );
  }
}
