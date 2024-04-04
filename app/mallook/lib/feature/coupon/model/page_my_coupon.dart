class PageMyCoupons {
  PageMyCoupons({
    this.size,
    this.content,
    this.number,
    this.sort,
    this.first,
    this.last,
    this.numberOfElements,
    this.pageable,
    this.empty,
  });

  PageMyCoupons.fromJson(dynamic json) {
    size = json['size'];
    if (json['content'] != null) {
      content = [];
      json['content'].forEach((v) {
        content?.add(MyCoupon.fromJson(v));
      });
    }
    number = json['number'];
    sort = json['sort'] != null ? Sort.fromJson(json['sort']) : null;
    first = json['first'];
    last = json['last'];
    numberOfElements = json['numberOfElements'];
    pageable =
        json['pageable'] != null ? Pageable.fromJson(json['pageable']) : null;
    empty = json['empty'];
  }

  int? size;
  List<MyCoupon>? content;
  int? number;
  Sort? sort;
  bool? first;
  bool? last;
  int? numberOfElements;
  Pageable? pageable;
  bool? empty;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['size'] = size;
    if (content != null) {
      map['content'] = content?.map((v) => v.toJson()).toList();
    }
    map['number'] = number;
    if (sort != null) {
      map['sort'] = sort?.toJson();
    }
    map['first'] = first;
    map['last'] = last;
    map['numberOfElements'] = numberOfElements;
    if (pageable != null) {
      map['pageable'] = pageable?.toJson();
    }
    map['empty'] = empty;
    return map;
  }
}

class Pageable {
  Pageable({
    this.offset,
    this.sort,
    this.pageNumber,
    this.pageSize,
    this.paged,
    this.unpaged,
  });

  Pageable.fromJson(dynamic json) {
    offset = json['offset'];
    sort = json['sort'] != null ? Sort.fromJson(json['sort']) : null;
    pageNumber = json['pageNumber'];
    pageSize = json['pageSize'];
    paged = json['paged'];
    unpaged = json['unpaged'];
  }

  int? offset;
  Sort? sort;
  int? pageNumber;
  int? pageSize;
  bool? paged;
  bool? unpaged;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['offset'] = offset;
    if (sort != null) {
      map['sort'] = sort?.toJson();
    }
    map['pageNumber'] = pageNumber;
    map['pageSize'] = pageSize;
    map['paged'] = paged;
    map['unpaged'] = unpaged;
    return map;
  }
}

class Sort {
  Sort({
    this.empty,
    this.sorted,
    this.unsorted,
  });

  Sort.fromJson(dynamic json) {
    empty = json['empty'];
    sorted = json['sorted'];
    unsorted = json['unsorted'];
  }

  bool? empty;
  bool? sorted;
  bool? unsorted;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['empty'] = empty;
    map['sorted'] = sorted;
    map['unsorted'] = unsorted;
    return map;
  }
}

class MyCoupon {
  MyCoupon({
    this.myCouponId,
    this.name,
    this.type,
    this.amount,
    this.expiredTime,
  });

  MyCoupon.fromJson(dynamic json) {
    myCouponId = json['myCouponId'];
    name = json['name'];
    type = json['type'];
    amount = json['amount'];
    expiredTime = json['expiredTime'];
  }

  int? myCouponId;
  String? name;
  String? type;
  num? amount;
  String? expiredTime;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['myCouponId'] = myCouponId;
    map['name'] = name;
    map['type'] = type;
    map['amount'] = amount;
    map['expiredTime'] = expiredTime;
    return map;
  }
}
