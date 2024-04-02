class Keyword {
  Keyword({
    this.name,
  });

  Keyword.fromJson(dynamic json) {
    name = json['name'];
  }
  String? name;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['name'] = name;
    return map;
  }

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is Keyword &&
          runtimeType == other.runtimeType &&
          name == other.name;

  @override
  int get hashCode => name.hashCode;
}
