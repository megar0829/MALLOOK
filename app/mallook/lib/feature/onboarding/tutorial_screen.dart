import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';
import 'package:mallook/feature/sign_up/widgets/form_button.dart';

class TutorialScreen extends StatefulWidget {
  const TutorialScreen({
    super.key,
  });

  @override
  State<TutorialScreen> createState() => _TutorialScreenState();
}

class _TutorialScreenState extends State<TutorialScreen>
    with SingleTickerProviderStateMixin {
  final int totalPage = 3;
  late TabController _controller;
  int _currentIndex = 0;
  bool _isLastPage = false;

  @override
  void initState() {
    super.initState();
    _controller = TabController(length: totalPage, vsync: this);
    _controller.addListener(_handleTabSelection);
  }

  void _handleTabSelection() {
    setState(() {
      _currentIndex = _controller.index;
      _isLastPage = _currentIndex == (totalPage - 1);
    });
  }

  void _onNextTap() {
    Navigator.of(context).pushAndRemoveUntil(
      PageRouteBuilder(
        pageBuilder: (context, animation, secondaryAnimation) =>
            const MainNavigationScreen(),
        transitionsBuilder: (context, animation, secondaryAnimation, child) {
          var begin = const Offset(1.0, 0.0);
          var end = Offset.zero;
          var curve = Curves.ease;

          var tween =
              Tween(begin: begin, end: end).chain(CurveTween(curve: curve));

          return SlideTransition(
            position: animation.drive(tween),
            child: child,
          );
        },
        transitionDuration: const Duration(milliseconds: 500),
      ),
      (route) => false,
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SafeArea(
        child: TabBarView(
          controller: _controller,
          children: const [
            Padding(
              padding: EdgeInsets.symmetric(
                horizontal: Sizes.size24,
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Gaps.v52,
                  Text(
                    '스타일 선택',
                    style: TextStyle(
                      fontSize: Sizes.size40,
                      fontWeight: FontWeight.bold,
                      height: 1.2,
                    ),
                  ),
                  Gaps.v16,
                  Text(
                    '좋아하는 스타일의 옷을 선택해봐요!',
                    style: TextStyle(
                      color: Colors.black54,
                      fontSize: Sizes.size20,
                    ),
                  ),
                ],
              ),
            ),
            Padding(
              padding: EdgeInsets.symmetric(
                horizontal: Sizes.size24,
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Gaps.v52,
                  Text(
                    '스타일 선택',
                    style: TextStyle(
                      fontSize: Sizes.size40,
                      fontWeight: FontWeight.bold,
                      height: 1.2,
                    ),
                  ),
                  Gaps.v16,
                  Text(
                    '좋아하는 스타일의 옷을 선택해봐요!',
                    style: TextStyle(
                      color: Colors.black54,
                      fontSize: Sizes.size20,
                    ),
                  ),
                ],
              ),
            ),
            Padding(
              padding: EdgeInsets.symmetric(
                horizontal: Sizes.size24,
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Gaps.v52,
                  Text(
                    '쇼핑 시작',
                    style: TextStyle(
                      fontSize: Sizes.size40,
                      fontWeight: FontWeight.bold,
                      height: 1.2,
                    ),
                  ),
                  Gaps.v16,
                  Text(
                    '즐거운 쇼핑을 시작해 볼까요?',
                    style: TextStyle(
                      color: Colors.black54,
                      fontSize: Sizes.size20,
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
      bottomNavigationBar: BottomAppBar(
        height: Sizes.size96,
        surfaceTintColor: Colors.white,
        color: Colors.white,
        shadowColor: Colors.black,
        elevation: Sizes.size5,
        child: Container(
          padding: const EdgeInsets.symmetric(
            horizontal: Sizes.size20,
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Expanded(child: Container()),
              // 왼쪽 빈 공간
              Padding(
                padding: const EdgeInsets.symmetric(
                  horizontal: Sizes.size28,
                ),
                child: TabPageSelector(
                  controller: _controller,
                  indicatorSize: Sizes.size18,
                  borderStyle: BorderStyle.none,
                  color: Theme.of(context).primaryColorLight,
                  selectedColor: Theme.of(context).primaryColorDark,
                ),
              ),
              if (_isLastPage)
                Expanded(
                  child: FormButton(
                    onTap: () => _onNextTap(),
                    disabled: !_isLastPage,
                    text: "다음",
                  ),
                )
              else
                Expanded(child: Container()),
            ],
          ),
        ),
      ),
    );
  }
}
