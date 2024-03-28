import 'dart:math';

class CouponApiService {
  static Future<List<Coupon>> getMyCoupons(int page) async {
    List<Coupon> coupons = [];

    for (int i = 0; i < 10; i++) {
      int id = Random().nextInt(10000);
      coupons.add(
        Coupon(),
      );
    }

    await Future.delayed(const Duration(seconds: 1));
    return coupons;
  }

  static Future<List<Coupon>> getCoupons(int page) async {
    List<Coupon> coupons = [];

    for (int i = 0; i < 10; i++) {
      int id = Random().nextInt(10000);
      coupons.add(
        Coupon(id: id, name: '쿠폰 $id', description: '쿠폰 상세 정보 $id'),
      );
      print(id);
    }

    await Future.delayed(const Duration(seconds: 1));
    return coupons;
  }
}
