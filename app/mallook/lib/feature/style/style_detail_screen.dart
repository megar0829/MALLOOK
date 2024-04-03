import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/style/api/style_api_service.dart';
import 'package:mallook/feature/style/model/style.dart';
import 'package:mallook/feature/style/model/style_detail.dart';
import 'package:mallook/feature/style/widget/style_product_widget.dart';
import 'package:mallook/global/widget/cart_icon_button.dart';
import 'package:mallook/global/widget/custom_circular_wait_widget.dart';
import 'package:mallook/global/widget/home_icon_button.dart';

class StyleDetailScreen extends StatefulWidget {
  final Style style;

  const StyleDetailScreen({super.key, required this.style});

  @override
  State<StyleDetailScreen> createState() => _StyleDetailScreenState();
}

class _StyleDetailScreenState extends State<StyleDetailScreen> {
  late final Future<StyleDetail> _style = StyleApiService.getStyleDetail(
    styleId: widget.style.id!,
  );

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        surfaceTintColor: Colors.white,
        elevation: 1,
        shadowColor: Colors.grey.shade400,
        centerTitle: true,
        title: const Text(
          '몰룩북 구경',
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
        future: _style,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return SingleChildScrollView(
              child: Padding(
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size10,
                  horizontal: Sizes.size24,
                ),
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.start,
                  children: [
                    Container(
                      clipBehavior: Clip.hardEdge,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(
                          Sizes.size16,
                        ),
                      ),
                      child: Image.network(
                        snapshot.data!.imageUrl ??
                            "https://zooting-s3-bucket.s3.ap-northeast-2.amazonaws.com/logo_sm.png",
                        fit: BoxFit.cover,
                      ),
                    ),
                    Gaps.v6,
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          snapshot.data!.memberNickname ?? "",
                          style: const TextStyle(
                            color: Colors.black,
                            fontWeight: FontWeight.bold,
                            fontSize: Sizes.size16,
                          ),
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.end,
                          children: [
                            const FaIcon(
                              FontAwesomeIcons.solidHeart,
                              color: Colors.orange,
                            ),
                            Gaps.h10,
                            Text(
                              '${snapshot.data!.heartCount ?? 0}',
                              style: const TextStyle(
                                color: Colors.black87,
                                fontSize: Sizes.size18,
                                fontWeight: FontWeight.bold,
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                    Gaps.v4,
                    Divider(
                      color:
                          Theme.of(context).primaryColorLight.withOpacity(0.4),
                    ),
                    Gaps.v6,
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
                      itemBuilder: (context, index) => StyleProductWidget(
                        styleProduct: snapshot.data!.styleProducts![index],
                      ),
                      separatorBuilder: (context, index) => Gaps.v8,
                      itemCount: (snapshot.data!.styleProducts ?? []).length,
                    ),
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
