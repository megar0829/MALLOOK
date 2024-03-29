import 'package:mallook/feature/profile/model/address.dart';

class MemberDetail {
  String? nickname;
  String? birth;
  String? gender;
  String? phone;
  int? point;
  int? exp;
  List<int>? expRange;
  Address? address;

  MemberDetail(
      {this.nickname,
      this.birth,
      this.gender,
      this.phone,
      this.point,
      this.exp,
      this.expRange,
      this.address});

  MemberDetail.fromJson(dynamic json) {
    nickname = json['nickname'];
    birth = json['birth'];
    gender = json['gender'];
    phone = json['phone'];
    point = json['point'];
    exp = json['exp'];
    expRange = json['expRange']?.cast<int>();
    address =
        json['address'] != null ? Address.fromJson(json['address']) : null;
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['nickname'] = nickname;
    data['birth'] = birth;
    data['gender'] = gender;
    data['phone'] = phone;
    data['point'] = point;
    data['exp'] = exp;
    data['expRange'] = expRange;
    if (address != null) {
      data['address'] = address!.toJson();
    }
    return data;
  }
}
