class Product {
  Product({
    this.mainCategory,
    this.subCategory,
    this.name,
    this.price,
    this.quantity,
    this.brandName,
    this.size,
    this.fee,
    this.image,
    this.code,
    this.url,
  });

  Product.fromJson(dynamic json) {
    mainCategory = json['mainCategory'];
    subCategory = json['subCategory'];
    name = json['name'];
    price = json['price'];
    quantity = json['quantity'];
    brandName = json['brandName'];
    size = json['size'];
    fee = json['fee'];
    image = json['image'];
    code = json['code'];
    url = json['url'];
  }
  String? mainCategory;
  String? subCategory;
  String? name;
  int? price;
  int? quantity;
  String? brandName;
  String? size;
  int? fee;
  String? image;
  String? code;
  String? url;

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
