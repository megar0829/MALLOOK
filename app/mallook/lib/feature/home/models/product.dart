class Product {
  final String? mainCategory;
  final String? subCategory;
  final String name;
  final int price;
  final int? quantity;
  final String? brandName;
  final String? size;
  final int? fee;
  final String? image;
  final String? code;
  final String? url;

  Product({
    this.mainCategory,
    this.subCategory,
    required this.name,
    required this.price,
    this.quantity,
    this.brandName,
    this.size,
    this.fee,
    this.image,
    this.code,
    this.url,
  });

  Product.fromJson(dynamic json)
      : mainCategory = json['mainCategory'],
        subCategory = json['subCategory'],
        name = json['name'] ?? '',
        price = json['price'] ?? 0,
        quantity = json['quantity'],
        brandName = json['brandName'],
        size = json['size'],
        fee = json['fee'],
        image = json['image'],
        code = json['code'],
        url = json['url'];

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['mainCategory'] = mainCategory;
    map['subCategory'] = subCategory;
    map['name'] = name;
    map['price'] = price;
    map['quantity'] = quantity;
    map['brandName'] = brandName;
    map['size'] = size;
    map['fee'] = fee;
    map['image'] = image;
    map['code'] = code;
    map['url'] = url;
    return map;
  }
}
