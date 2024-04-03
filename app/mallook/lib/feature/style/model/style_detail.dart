class StyleDetail {
  StyleDetail({
    this.memberNickname,
    this.name,
    this.heartCount,
    this.styleProducts,
    this.imageUrl,
  });

  StyleDetail.fromJson(dynamic json) {
    memberNickname = json['memberNickname'];
    name = json['name'];
    heartCount = json['heartCount'];
    if (json['styleProductResList'] != null) {
      styleProducts = [];
      json['styleProductResList'].forEach((v) {
        styleProducts?.add(StyleProduct.fromJson(v));
      });
    }
    imageUrl = json['imageUrl'];
  }
  String? memberNickname;
  String? name;
  int? heartCount;
  List<StyleProduct>? styleProducts;
  String? imageUrl;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['memberNickname'] = memberNickname;
    map['name'] = name;
    map['heartCount'] = heartCount;
    if (styleProducts != null) {
      map['styleProductResList'] =
          styleProducts?.map((v) => v.toJson()).toList();
    }
    map['imageUrl'] = imageUrl;
    return map;
  }
}

class StyleProduct {
  StyleProduct({
    this.productsId,
    this.name,
    this.price,
    this.brandName,
    this.image,
  });

  StyleProduct.fromJson(dynamic json) {
    productsId = json['productsId'];
    name = json['name'];
    price = json['price'];
    brandName = json['brandName'];
    image = json['image'];
  }
  String? productsId;
  String? name;
  int? price;
  String? brandName;
  String? image;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['productsId'] = productsId;
    map['name'] = name;
    map['price'] = price;
    map['brandName'] = brandName;
    map['image'] = image;
    return map;
  }
}
