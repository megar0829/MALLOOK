class ScriptDetail {
  ScriptDetail({
    this.name,
    this.heartCount,
    this.nickname,
    this.hasLiked,
    this.imageUrl,
    this.memberGrade,
  });

  ScriptDetail.fromJson(dynamic json) {
    name = json['name'];
    heartCount = json['heartCount'];
    nickname = json['nickname'];
    hasLiked = json['hasLiked'];
    imageUrl = json['imageUrl'];
    memberGrade = json['memberGrade'];
  }

  String? name;
  int? heartCount;
  String? nickname;
  bool? hasLiked;
  String? imageUrl;
  String? memberGrade;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['name'] = name;
    map['heartCount'] = heartCount;
    map['nickname'] = nickname;
    map['hasLiked'] = hasLiked;
    map['imageUrl'] = imageUrl;
    map['memberGrade'] = memberGrade;
    return map;
  }

  @override
  String toString() {
    return 'ScriptDetail{name: $name, heartCount: $heartCount, nickname: $nickname, hasLiked: $hasLiked, image: $imageUrl}';
  }
}
