class Product {
  Product({
    this.id,
    this.mainCategory,
    this.subCategory,
    this.gender,
    this.name,
    this.price,
    this.brandName,
    this.size,
    this.fee,
    this.tags,
    this.detailImages,
    this.code,
    this.url,
    this.image,
  });

  Product.fromJson(dynamic json) {
    id = json['id'];
    mainCategory = json['mainCategory'];
    subCategory = json['subCategory'];
    gender = json['gender'];
    name = json['name'];
    price = json['price'];
    brandName = json['brandName'];
    size = json['size'] != null ? json['size'].cast<String>() : [];
    fee = json['fee'];
    tags = json['tags'] != null ? json['tags'].cast<String>() : [];
    detailImages =
        json['detailImages'] != null ? json['detailImages'].cast<String>() : [];
    code = json['code'];
    url = json['url'];
    image = json['image'];
  }

  String? id;
  String? mainCategory;
  String? subCategory;
  String? gender;
  String? name;
  int? price;
  String? brandName;
  List<String>? size;
  int? fee;
  List<String>? tags;
  List<String>? detailImages;
  String? code;
  String? url;
  String? image;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = id;
    map['mainCategory'] = mainCategory;
    map['subCategory'] = subCategory;
    map['gender'] = gender;
    map['name'] = name;
    map['price'] = price;
    map['brandName'] = brandName;
    map['size'] = size;
    map['fee'] = fee;
    map['tags'] = tags;
    map['detailImages'] = detailImages;
    map['code'] = code;
    map['url'] = url;
    map['image'] = image;
    return map;
  }
}
