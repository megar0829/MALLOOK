import 'package:mallook/feature/coupon/model/cursor_coupons.dart';

class CouponApiService {
  static Future<List<Coupon>> getMyCoupons(int page) async {
    List<Coupon> coupons = [];

    await Future.delayed(const Duration(seconds: 4));
    return coupons;
  }

  static Future<List<Coupon>> getCoupons(int page) async {
    List<Coupon> coupons = [];

    await Future.delayed(const Duration(seconds: 4));
    return coupons;
  }
}
