import 'package:mallook/feature/product/model/product.dart';

class ProductCursorResponse {
  ProductCursorResponse({
    this.content,
    this.nextCursor,
  });

  ProductCursorResponse.fromJson(dynamic json) {
    if (json['content'] != null) {
      content = [];
      json['content'].forEach((v) {
        content?.add(Product.fromJson(v));
      });
    }
    nextCursor = json['nextCursor'];
  }

  List<Product>? content;
  String? nextCursor;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    if (content != null) {
      map['content'] = content?.map((v) => v.toJson()).toList();
    }
    map['nextCursor'] = nextCursor;
    return map;
  }
}
