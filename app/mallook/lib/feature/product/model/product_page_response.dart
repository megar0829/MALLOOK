import 'package:mallook/feature/product/model/product.dart';

class ProductPageResponse {
  ProductPageResponse({
    this.content,
    this.currentPage,
    this.totalPage,
  });

  ProductPageResponse.fromJson(dynamic json) {
    if (json['content'] != null) {
      content = [];
      json['content'].forEach((v) {
        content?.add(Product.fromJson(v));
      });
    }
    currentPage = json['currentPage'];
    totalPage = json['totalPage'];
  }

  List<Product>? content;
  int? currentPage;
  int? totalPage;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    if (content != null) {
      map['content'] = content?.map((v) => v.toJson()).toList();
    }
    map['currentPage'] = currentPage;
    map['totalPage'] = totalPage;
    return map;
  }
}
