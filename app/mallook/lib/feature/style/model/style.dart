import 'package:mallook/feature/product/model/product.dart';

class Style {
  Style({
    this.id,
    this.name,
    this.heartCount,
    this.memberNickname,
    this.productsListDtoList,
  });

  Style.fromJson(dynamic json) {
    id = json['id'];
    name = json['name'];
    heartCount = json['heartCount'];
    memberNickname = json['memberNickname'];
    if (json['productsListDtoList'] != null) {
      productsListDtoList = [];
      json['productsListDtoList'].forEach((v) {
        productsListDtoList?.add(Product.fromJson(v));
      });
    }
  }
  int? id;
  String? name;
  int? heartCount;
  String? memberNickname;
  List<Product>? productsListDtoList;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = id;
    map['name'] = name;
    map['heartCount'] = heartCount;
    map['memberNickname'] = memberNickname;
    if (productsListDtoList != null) {
      map['productsListDtoList'] =
          productsListDtoList?.map((v) => v.toJson()).toList();
    }
    return map;
  }
}
