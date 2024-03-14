class Sort {
  Sort({
    required this.empty,
    required this.sorted,
    required this.unsorted,
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
