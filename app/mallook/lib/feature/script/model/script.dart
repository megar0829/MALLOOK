class Script {
  Script({
    this.id,
    this.name,
    this.heartCount,
    this.nickname,
    this.imageUrl,
  });

  Script.fromJson(dynamic json) {
    id = json['id'];
    name = json['name'];
    heartCount = json['heartCount'];
    nickname = json['nickname'];
    imageUrl = json['imageUrl'];
  }

  int? id;
  String? name;
  int? heartCount;
  String? nickname;
  String? imageUrl;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = id;
    map['name'] = name;
    map['heartCount'] = heartCount;
    map['nickname'] = nickname;
    map['imageUrl'] = imageUrl;
    return map;
  }
}
