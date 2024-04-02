class ProductDetail {
  ProductDetail({
    this.id,
    this.mainCategory,
    this.subCategory,
    this.gender,
    this.name,
    this.price,
    this.color,
    this.size,
    this.brandName,
    this.fee,
    this.image,
    this.code,
    this.url,
    this.tags,
    this.detailImages,
    this.detailHtml,
    this.keywords,
    this.review,
  });

  ProductDetail.fromJson(dynamic json) {
    id = json['id'];
    mainCategory = json['mainCategory'];
    subCategory = json['subCategory'];
    gender = json['gender'];
    name = json['name'];
    price = json['price'];
    color = json['color'] != null ? json['color'].cast<String>() : [];
    size = json['size'] != null ? json['size'].cast<String>() : [];
    brandName = json['brandName'];
    fee = json['fee'];
    image = json['image'];
    code = json['code'];
    url = json['url'];
    tags = json['tags'] != null ? json['tags'].cast<String>() : [];
    detailImages =
        json['detailImages'] != null ? json['detailImages'].cast<String>() : [];
    detailHtml = json['detailHtml'];
    keywords = json['keywords'] != null ? json['keywords'].cast<String>() : [];
    review = json['review'] != null ? Review.fromJson(json['review']) : null;
  }

  String? id;
  String? mainCategory;
  String? subCategory;
  String? gender;
  String? name;
  int? price;
  List<String>? color;
  List<String>? size;
  String? brandName;
  int? fee;
  String? image;
  String? code;
  String? url;
  List<String>? tags;
  List<String>? detailImages;
  String? detailHtml;
  List<String>? keywords;
  Review? review;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = id;
    map['mainCategory'] = mainCategory;
    map['subCategory'] = subCategory;
    map['gender'] = gender;
    map['name'] = name;
    map['price'] = price;
    map['color'] = color;
    map['size'] = size;
    map['brandName'] = brandName;
    map['fee'] = fee;
    map['image'] = image;
    map['code'] = code;
    map['url'] = url;
    map['tags'] = tags;
    map['detailImages'] = detailImages;
    map['detailHtml'] = detailHtml;
    map['keywords'] = keywords;
    if (review != null) {
      map['review'] = review?.toJson();
    }
    return map;
  }
}

class Review {
  Review({
    this.count,
    this.averagePoint,
    this.reviewList,
  });

  Review.fromJson(dynamic json) {
    count = json['count'];
    averagePoint = json['averagePoint'];
    if (json['reviewList'] != null) {
      reviewList = [];
      json['reviewList'].forEach((v) {
        reviewList?.add(ReviewList.fromJson(v));
      });
    }
  }

  int? count;
  num? averagePoint;
  List<ReviewList>? reviewList;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['count'] = count;
    map['averagePoint'] = averagePoint;
    if (reviewList != null) {
      map['reviewList'] = reviewList?.map((v) => v.toJson()).toList();
    }
    return map;
  }
}

class ReviewList {
  ReviewList({
    this.contents,
    this.createdAt,
    this.images,
    this.point,
    this.productOption,
    this.userSize,
  });

  ReviewList.fromJson(dynamic json) {
    contents = json['contents'];
    createdAt = json['createdAt'];
    images = json['images'] != null ? json['images'].cast<String>() : [];
    point = json['point'];
    if (json['productOption'] != null) {
      productOption = [];
      json['productOption'].forEach((v) {
        productOption?.add(ProductOption.fromJson(v));
      });
    }
    userSize =
        json['userSize'] != null ? UserSize.fromJson(json['userSize']) : null;
  }

  String? contents;
  String? createdAt;
  List<String>? images;
  int? point;
  List<ProductOption>? productOption;
  UserSize? userSize;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['contents'] = contents;
    map['createdAt'] = createdAt;
    map['images'] = images;
    map['point'] = point;
    if (productOption != null) {
      map['productOption'] = productOption?.map((v) => v.toJson()).toList();
    }
    if (userSize != null) {
      map['userSize'] = userSize?.toJson();
    }
    return map;
  }
}

class UserSize {
  UserSize({
    this.height,
    this.weight,
  });

  UserSize.fromJson(dynamic json) {
    height = json['height'];
    weight = json['weight'];
  }

  String? height;
  String? weight;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['height'] = height;
    map['weight'] = weight;
    return map;
  }
}

class ProductOption {
  ProductOption({
    this.color,
    this.size,
  });

  ProductOption.fromJson(dynamic json) {
    color = json['color'];
    size = json['size'];
  }

  String? color;
  String? size;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['color'] = color;
    map['size'] = size;
    return map;
  }
}
