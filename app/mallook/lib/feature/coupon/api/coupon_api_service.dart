import 'dart:math';

import 'package:mallook/feature/coupon/model/coupon_model.dart';

class CouponApiService {
  static Future<List<Coupon>> getMyCoupons(int page) async {
    List<Coupon> coupons = [];

    for (int i = 0; i < 10; i++) {
      int id = Random().nextInt(10000);
      coupons.add(
        Coupon(
          name: '쿠폰명 $id',
          type: i % 2 == 0 ? "amount" : "ratio",
          discount:
              i % 2 == 0 ? Random().nextInt(100000) : Random().nextInt(100),
        ),
      );
    }

    await Future.delayed(const Duration(seconds: 4));
    return coupons;
  }

  static Future<List<Coupon>> getCoupons(int page) async {
    List<Coupon> coupons = [];

    for (int i = 0; i < 10; i++) {
      int id = Random().nextInt(10000);
      coupons.add(
        Coupon(
          name: '쿠폰명 $id',
          type: i % 2 == 0 ? "amount" : "ratio",
          discount:
              i % 2 == 0 ? Random().nextInt(100000) : Random().nextInt(100),
        ),
      );
    }

    await Future.delayed(const Duration(seconds: 4));
    return coupons;
  }
}
