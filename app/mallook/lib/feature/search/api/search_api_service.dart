import 'dart:math';

import 'package:mallook/feature/search/models/hot_keyword.dart';

class SearchApiService {
  static Future<List<HotKeyword>> getHotKeywords() async {
    List<HotKeyword> hotKeywords = [];

    for (int i = 0; i < 10; i++) {
      hotKeywords.add(
        HotKeyword(
          id: Random().nextInt(1000),
          rank: i + 1,
          change: Random().nextInt(100) - 50,
          name: "핫 키워드 $i",
        ),
      );
    }

    hotKeywords.sort((a, b) => a.rank.compareTo(b.rank));
    Future.delayed(const Duration(milliseconds: 500));

    return hotKeywords;
  }
}
