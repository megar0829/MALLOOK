import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/profile/model/member_detail.dart';

class ProfileApiService {
  static final _dio = DioService();

  static Future<MemberDetail> getMemberDetail() async {
    return await _dio.baseGet<MemberDetail>(
      "/api/members",
      fromJsonT: (json) => MemberDetail.fromJson(json),
    );
  }
}
