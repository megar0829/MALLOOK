import 'package:mallook/feature/profile/model/address.dart';

class MemberDetail {
  MemberDetail({
    this.nickname,
    this.nicknameTag,
    this.birth,
    this.gender,
    this.phone,
    this.grade,
    this.expRange,
    this.point,
    this.exp,
    this.address,
    this.memberCoupon,
    this.coupon,
    this.cartProduct,
    this.orders,
  });

  MemberDetail.fromJson(dynamic json) {
    nickname = json['nickname'];
    nicknameTag = json['nicknameTag'];
    birth = json['birth'];
    gender = json['gender'];
    phone = json['phone'];
    grade = json['grade'];
    expRange = json['expRange'] != null ? json['expRange'].cast<int>() : [];
    point = json['point'];
    exp = json['exp'];
    address =
        json['address'] != null ? Address.fromJson(json['address']) : null;
    memberCoupon = json['memberCoupon'];
    coupon = json['coupon'];
    cartProduct = json['cartProduct'];
    orders = json['orders'];
  }

  String? nickname;
  String? nicknameTag;
  String? birth;
  String? gender;
  String? phone;
  String? grade;
  List<int>? expRange;
  int? point;
  int? exp;
  Address? address;
  int? memberCoupon;
  int? coupon;
  int? cartProduct;
  int? orders;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['nickname'] = nickname;
    map['nicknameTag'] = nicknameTag;
    map['birth'] = birth;
    map['gender'] = gender;
    map['phone'] = phone;
    map['grade'] = grade;
    map['expRange'] = expRange;
    map['point'] = point;
    map['exp'] = exp;
    if (address != null) {
      map['address'] = address?.toJson();
    }
    map['memberCoupon'] = memberCoupon;
    map['coupon'] = coupon;
    map['cartProduct'] = cartProduct;
    map['orders'] = orders;
    return map;
  }
}
