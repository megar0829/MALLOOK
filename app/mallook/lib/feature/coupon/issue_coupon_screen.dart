import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/coupon/api/coupon_api_service.dart';
import 'package:mallook/feature/coupon/model/page_issue_coupon.dart';
import 'package:mallook/global/widget/custom_circular_wait_widget.dart';
import 'package:mallook/global/widget/home_icon_button.dart';

class IssueCouponScreen extends StatefulWidget {
  const IssueCouponScreen({super.key});

  @override
  State<IssueCouponScreen> createState() => _IssueCouponScreenState();
}

class _IssueCouponScreenState extends State<IssueCouponScreen> {
  final ScrollController _scrollController = ScrollController();
  final List<IssueCoupon> _coupons = [];
  int _cursor = 999999999999;
  bool _isCouponLoading = false;

  @override
  void initState() {
    super.initState();

    _scrollController.addListener(() {
      if (_scrollController.offset >=
              (_scrollController.position.maxScrollExtent * 0.9) &&
          !_scrollController.position.outOfRange) {
        _loadMoreCoupons();
      }
    });
    _loadMoreCoupons();
  }

  void _loadMoreCoupons() async {
    if (_cursor == 0) return;
    if (!_isCouponLoading) {
      if (mounted) {
        setState(() {
          _isCouponLoading = true;
        });
      }
      try {
        var loadedCoupons = await CouponApiService.getIssueCoupon(
          cursor: _cursor,
        );

        if (mounted) {
          setState(() {
            _coupons.addAll(loadedCoupons.content ?? []);
            if ((loadedCoupons.content ?? []).isNotEmpty) {
              _cursor = (loadedCoupons.content ?? []).last.id! - 1;
            }
          });
        }
      } finally {
        _isCouponLoading = false;
      }
    }
  }

  void _issueCoupon(IssueCoupon coupon) async {
    await CouponApiService.issueCoupon(body: <String, num>{
      "couponId": coupon.id!,
    });
    // 발급된 경우 화면에서 해당 쿠폰 삭제
    _coupons.remove(coupon);
    setState(() {});
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
        centerTitle: true,
        titleTextStyle: const TextStyle(
          color: Colors.black,
          fontWeight: FontWeight.bold,
          fontSize: Sizes.size18,
        ),
        title: const Text('쿠폰 발급'),
        actions: const [
          HomeIconButton(),
          Gaps.h24,
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(
          horizontal: Sizes.size12,
          vertical: Sizes.size24,
        ),
        child: RefreshIndicator(
          onRefresh: () async {
            _cursor = 999999999999;
            _coupons.clear();
            _loadMoreCoupons();
          },
          child: ListView.separated(
            controller: _scrollController,
            itemBuilder: (context, index) {
              if (index < _coupons.length) {
                return Container(
                  margin: const EdgeInsets.symmetric(
                    vertical: Sizes.size2,
                    horizontal: Sizes.size4,
                  ),
                  padding: const EdgeInsets.symmetric(
                    vertical: Sizes.size10,
                    horizontal: Sizes.size14,
                  ),
                  decoration: BoxDecoration(
                    color: Colors.white,
                    border: Border.all(
                      color: Colors.grey.shade300,
                      width: Sizes.size1,
                    ),
                    borderRadius: BorderRadius.circular(
                      Sizes.size16,
                    ),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.grey.shade400,
                        offset: const Offset(2, 1),
                      )
                    ],
                  ),
                  child: Row(
                    children: [
                      Expanded(
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.spaceAround,
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(
                              _coupons[index].name!,
                              style: const TextStyle(
                                color: Colors.black,
                                fontWeight: FontWeight.bold,
                                fontSize: Sizes.size16,
                              ),
                            ),
                            Gaps.v10,
                            Row(
                              children: [
                                Text(
                                  "만료: ",
                                  style: TextStyle(
                                    color: Colors.grey.shade600,
                                    fontWeight: FontWeight.bold,
                                    fontSize: Sizes.size14,
                                  ),
                                ),
                                Gaps.h5,
                                Text(
                                  _coupons[index].expiredTime ?? "",
                                  style: TextStyle(
                                    color: Colors.grey.shade600,
                                    fontWeight: FontWeight.bold,
                                    fontSize: Sizes.size14,
                                  ),
                                ),
                              ],
                            )
                          ],
                        ),
                      ),
                      Gaps.h20,
                      Center(
                        child: ElevatedButton(
                          onPressed: () => _issueCoupon(_coupons[index]),
                          child: const Text("쿠폰 발급"),
                        ),
                      )
                    ],
                  ),
                );
              } else {
                return CustomCircularWaitWidget(
                  onTap: () => _loadMoreCoupons(),
                );
              }
            },
            separatorBuilder: (context, index) => Gaps.v10,
            itemCount: _coupons.length + 1,
          ),
        ),
      ),
    );
  }
}
