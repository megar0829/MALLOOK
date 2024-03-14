import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';

class SearchScreen extends StatefulWidget {
  const SearchScreen({super.key});

  @override
  State<SearchScreen> createState() => _SearchScreenState();
}

class _SearchScreenState extends State<SearchScreen> {
  final TextEditingController _textEditingController = TextEditingController();

  String _searchWord = "";

  @override
  void initState() {
    super.initState();
    _textEditingController.addListener(() {
      setState(() {
        _searchWord = _textEditingController.text;
      });
    });
  }

  void _onSearchSubmitted() {
    if (_searchWord != "") {
      print(_searchWord); // 검색어 전달자 사전작업
    }
  }

  void _onClearTap() {
    _textEditingController.clear();
  }

  void _onStopSearch() {
    FocusScope.of(context).unfocus();
  }

  @override
  void dispose() {
    _textEditingController.dispose();

    super.dispose();
  } // Controller 를 사용시는 반드시 dispose 를 하여야 한다 (resource 문제)

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: _onStopSearch,
      child: Scaffold(
        backgroundColor: Colors.white,
        resizeToAvoidBottomInset: false,
        appBar: AppBar(
          backgroundColor: Colors.white,
          surfaceTintColor: Colors.white,
          automaticallyImplyLeading: false,
          elevation: 1,
          shadowColor: Colors.grey.shade400,
          title: TextField(
            textInputAction: TextInputAction.search,
            onEditingComplete: _onSearchSubmitted,
            controller: _textEditingController,
            decoration: InputDecoration(
              hintText: "내 스타일 옷 검색...",
              border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(
                  Sizes.size8,
                ),
                borderSide: BorderSide.none,
              ),
              filled: true,
              fillColor: Colors.grey.shade200,
              contentPadding: EdgeInsets.zero,
              prefixIcon: const Row(
                mainAxisSize: MainAxisSize.min,
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Padding(
                    padding: EdgeInsets.only(left: Sizes.size16),
                    child: FaIcon(
                      FontAwesomeIcons.magnifyingGlass,
                      color: Colors.black,
                      size: Sizes.size18,
                    ),
                  ),
                ],
              ),
              suffixIcon: Row(
                mainAxisSize: MainAxisSize.min,
                mainAxisAlignment: MainAxisAlignment.end,
                children: [
                  if (_searchWord.isNotEmpty)
                    Padding(
                      padding: const EdgeInsets.only(right: Sizes.size10),
                      child: GestureDetector(
                        onTap: _onClearTap,
                        child: FaIcon(
                          FontAwesomeIcons.solidCircleXmark,
                          color: Colors.grey.shade600,
                        ),
                      ),
                    ),
                ],
              ),
            ),
          ),
          actions: [
            InkWell(
              borderRadius: BorderRadius.circular(
                Sizes.size20,
              ),
              onTap: () {},
              child: Text(
                '검색',
                style: TextStyle(
                  color: Theme.of(context).primaryColorDark,
                  fontWeight: FontWeight.bold,
                  fontSize: Sizes.size20,
                ),
              ),
            ),
            Gaps.h20,
          ],
        ),
      ),
    );
  }
}
