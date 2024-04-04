import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/coupon/model/page_issue_coupon.dart';
import 'package:mallook/feature/coupon/model/page_my_coupon.dart';

class CouponApiService {
  static final _dio = DioService();

  static Future<PageMyCoupons> getMyCoupons(
      {required int cursor, int? size}) async {
    Map<String, dynamic> query = {};
    query['cursor'] = cursor;

    if (size != null) {
      query['size'] = size;
    }

    return await _dio.baseGet<PageMyCoupons>(
      path: "/api/coupons",
      queryParameters: query,
      fromJsonT: (json) => PageMyCoupons.fromJson(json),
    );
  }

  static Future<PageIssueCoupon> getIssueCoupon({required int cursor}) async {
    Map<String, dynamic> query = {};
    query['cursor'] = cursor;

    return await _dio.baseGet<PageIssueCoupon>(
      path: "/api/coupons/all",
      queryParameters: query,
      fromJsonT: (json) => PageIssueCoupon.fromJson(json),
    );
  }

  static Future<dynamic> issueCoupon({required dynamic body}) async {
    return await _dio.basePost(
      path: "/api/coupons",
      postData: body,
    );
  }

  static Future<dynamic> removeCoupon({required dynamic body}) async {
    return await _dio.baseDelete(
      path: "/api/coupons",
      deleteData: body,
    );
  }
}
