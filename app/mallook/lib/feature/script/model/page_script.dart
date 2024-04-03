import 'package:mallook/feature/script/model/script.dart';

class PageScript {
  PageScript({
    this.content,
    this.pageable,
    this.size,
    this.number,
    this.sort,
    this.numberOfElements,
    this.first,
    this.last,
    this.empty,
  });

  PageScript.fromJson(dynamic json) {
    if (json['content'] != null) {
      content = [];
      json['content'].forEach((v) {
        content?.add(Script.fromJson(v));
      });
    }
    pageable =
        json['pageable'] != null ? Pageable.fromJson(json['pageable']) : null;
    size = json['size'];
    number = json['number'];
    sort = json['sort'] != null ? Sort.fromJson(json['sort']) : null;
    numberOfElements = json['numberOfElements'];
    first = json['first'];
    last = json['last'];
    empty = json['empty'];
  }

  List<Script>? content;
  Pageable? pageable;
  int? size;
  int? number;
  Sort? sort;
  int? numberOfElements;
  bool? first;
  bool? last;
  bool? empty;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    if (content != null) {
      map['content'] = content?.map((v) => v.toJson()).toList();
    }
    if (pageable != null) {
      map['pageable'] = pageable?.toJson();
    }
    map['size'] = size;
    map['number'] = number;
    if (sort != null) {
      map['sort'] = sort?.toJson();
    }
    map['numberOfElements'] = numberOfElements;
    map['first'] = first;
    map['last'] = last;
    map['empty'] = empty;
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

class Pageable {
  Pageable({
    this.pageNumber,
    this.pageSize,
    this.sort,
    this.offset,
    this.paged,
    this.unpaged,
  });

  Pageable.fromJson(dynamic json) {
    pageNumber = json['pageNumber'];
    pageSize = json['pageSize'];
    sort = json['sort'] != null ? Sort.fromJson(json['sort']) : null;
    offset = json['offset'];
    paged = json['paged'];
    unpaged = json['unpaged'];
  }

  int? pageNumber;
  int? pageSize;
  Sort? sort;
  int? offset;
  bool? paged;
  bool? unpaged;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['pageNumber'] = pageNumber;
    map['pageSize'] = pageSize;
    if (sort != null) {
      map['sort'] = sort?.toJson();
    }
    map['offset'] = offset;
    map['paged'] = paged;
    map['unpaged'] = unpaged;
    return map;
  }
}
