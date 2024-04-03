class ScriptDetail {
  ScriptDetail({
    this.name,
    this.heartCount,
    this.nickname,
    this.hasLiked,
    this.image,
  });

  ScriptDetail.fromJson(dynamic json) {
    name = json['name'];
    heartCount = json['heartCount'];
    nickname = json['nickname'];
    hasLiked = json['hasLiked'];
    image = json['image'];
  }
  String? name;
  int? heartCount;
  String? nickname;
  bool? hasLiked;
  String? image;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['name'] = name;
    map['heartCount'] = heartCount;
    map['nickname'] = nickname;
    map['hasLiked'] = hasLiked;
    map['image'] = image;
    return map;
  }

  @override
  String toString() {
    return 'ScriptDetail{name: $name, heartCount: $heartCount, nickname: $nickname, hasLiked: $hasLiked, image: $image}';
  }
}
