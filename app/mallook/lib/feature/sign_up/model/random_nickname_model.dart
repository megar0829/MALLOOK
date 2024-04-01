class RandomNicknameModel {
  RandomNicknameModel({
    this.nickname,
    this.nicknameTag,
  });

  RandomNicknameModel.fromJson(dynamic json) {
    nickname = json['nickname'];
    nicknameTag = json['nicknameTag'];
  }
  String? nickname;
  String? nicknameTag;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['nickname'] = nickname;
    map['nicknameTag'] = nicknameTag;
    return map;
  }

  @override
  String toString() {
    return 'RandomNicknameModel{nickname: $nickname, nicknameTag: $nicknameTag}';
  }
}
