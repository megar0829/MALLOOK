import 'package:mallook/models/json_convertable.dart';
import 'package:mallook/models/pageable.dart';
import 'package:mallook/models/sort.dart';

class PageResponse<T extends JsonConvertor<T>> {
  PageResponse({
    required this.totalPages,
    required this.totalElements,
    required this.size,
    required this.content,
    required this.number,
    required this.sort,
    required this.numberOfElements,
    required this.pageable,
    required this.first,
    required this.last,
    required this.empty,
  });

  factory PageResponse.fromJson(dynamic json, T Function(dynamic) fromJsonT) {
    return PageResponse<T>(
      totalPages: json['totalPages'],
      totalElements: json['totalElements'],
      size: json['size'],
      content: json['content'] != null ? fromJsonT(json['content']) : null,
      number: json['number'],
      sort: json['sort'] != null ? Sort.fromJson(json['sort']) : null,
      numberOfElements: json['numberOfElements'],
      pageable:
          json['pageable'] != null ? Pageable.fromJson(json['pageable']) : null,
      first: json['first'],
      last: json['last'],
      empty: json['empty'],
    );
  }

  int? totalPages;
  int? totalElements;
  int? size;
  T? content;
  int? number;
  Sort? sort;
  int? numberOfElements;
  Pageable? pageable;
  bool? first;
  bool? last;
  bool? empty;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['totalPages'] = totalPages;
    map['totalElements'] = totalElements;
    map['size'] = size;
    if (content != null) {
      map['content'] = content?.toJson(); // Use content directly
    }
    map['number'] = number;
    if (sort != null) {
      map['sort'] = sort?.toJson();
    }
    map['numberOfElements'] = numberOfElements;
    if (pageable != null) {
      map['pageable'] = pageable?.toJson();
    }
    map['first'] = first;
    map['last'] = last;
    map['empty'] = empty;
    return map;
  }
}
