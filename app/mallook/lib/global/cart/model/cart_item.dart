import 'package:mallook/feature/product/model/product.dart';

class CartItem {
  CartItem({
    this.cartId,
    this.cartProductId,
    this.productId,
    this.price,
    this.count,
    this.name,
    this.image,
    this.size,
    this.color,
    this.fee,
    this.product,
  });

  CartItem.fromJson(dynamic json) {
    cartId = json['cartId'];
    cartProductId = json['cartProductId'];
    productId = json['productId'];
    price = json['price'];
    count = json['count'];
    name = json['name'];
    image = json['image'];
    size = json['size'];
    color = json['color'];
    fee = json['fee'];
  }

  int? cartId;
  int? cartProductId; // 장바구니 내 상품 id
  String? productId; // 상품 id
  int? price;
  int? count;
  String? name;
  String? image;
  String? size;
  String? color;
  int? fee;
  Product? product;
  bool selected = true;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['cartId'] = cartId;
    map['cartProductId'] = cartProductId;
    map['productId'] = productId;
    map['price'] = price;
    map['count'] = count;
    map['name'] = name;
    map['image'] = image;
    map['size'] = size;
    map['color'] = color;
    map['fee'] = fee;
    return map;
  }
}
