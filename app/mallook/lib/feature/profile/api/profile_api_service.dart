import 'package:mallook/config/dio_service.dart';
import 'package:mallook/feature/profile/model/member_detail.dart';

class ProfileApiService {
  static final _dio = DioService();

  static Future<void> getMemberDetail() async {
    print('hihihihihihihihihih');
    print('hihihihihihihihihih');
    var response = await _dio.convertedGet<MemberDetail>(
      "/api/members",
      fromJsonT: (json) => MemberDetail.fromJson(json),
    );
    print(response.expRange);
  }
}
