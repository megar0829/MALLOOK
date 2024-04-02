class WorldcupCody {
  WorldcupCody({
    this.id,
    this.name,
    this.heartCount,
    this.memberNickname,
    this.imageUrl,
    this.keywordList,
  });

  WorldcupCody.fromJson(dynamic json) {
    id = json['id'];
    name = json['name'];
    heartCount = json['heartCount'];
    memberNickname = json['memberNickname'];
    imageUrl = json['imageUrl'];
    keywordList =
        json['keywordList'] != null ? json['keywordList'].cast<String>() : [];
  }

  int? id;
  String? name;
  int? heartCount;
  String? memberNickname;
  String? imageUrl;
  List<String>? keywordList;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['id'] = id;
    map['name'] = name;
    map['heartCount'] = heartCount;
    map['memberNickname'] = memberNickname;
    map['imageUrl'] = imageUrl;
    map['keywordList'] = keywordList;
    return map;
  }
}
