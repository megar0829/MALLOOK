class Script {
  Script({
    required this.id,
    required this.name,
    this.heartCount,
    this.imageUrl,
  });

  Script.fromJson(dynamic json) {
    id = json['id'];
    name = json['name'];
    heartCount = json['heartCount'];
    imageUrl = json['imageUrl'];
  }

  int? id;
  String? name;
  int? heartCount;
  String? imageUrl;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = id;
    map['name'] = name;
    map['heartCount'] = heartCount;
    map['imageUrl'] = imageUrl;
    return map;
  }
}
