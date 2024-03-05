import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/main_navigation/main_navigation_screen.dart';

enum Direction { right, left }

enum Page { firstPage, secondPage }

class TutorialScreen extends StatefulWidget {
  const TutorialScreen({super.key});

  @override
  State<TutorialScreen> createState() => _TutorialScreenState();
}

class _TutorialScreenState extends State<TutorialScreen> {
  Direction _direction = Direction.right;
  Page _showInPage = Page.firstPage;

  void _onPanUpdate(DragUpdateDetails details) {
    if (details.delta.dx > 0) {
      // to the right
      setState(() {
        _direction = Direction.right;
      });
    } else {
      // to the left
      setState(() {
        _direction = Direction.left;
      });
    }
  }

  void _onEnterAppTap() {
    Navigator.of(context).pushAndRemoveUntil(
      MaterialPageRoute(
        builder: (context) => const MainNavigationScreen(),
      ),
      (route) => false,
    );
  }

  void _onPanEnd(DragEndDetails details) {
    if (_direction == Direction.left) {
      setState(() {
        _showInPage = Page.secondPage;
      });
    } else {
      setState(() {
        _showInPage = Page.firstPage;
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onPanUpdate: _onPanUpdate,
      onPanEnd: _onPanEnd,
      child: Scaffold(
        body: Padding(
          padding: const EdgeInsets.symmetric(
            horizontal: Sizes.size24,
          ),
          child: SafeArea(
            child: AnimatedCrossFade(
              firstChild: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Gaps.v80,
                  Text(
                    'Watch cool videos',
                    style: TextStyle(
                      color: Theme.of(context).colorScheme.primary,
                      fontSize: Sizes.size40,
                      fontWeight: FontWeight.bold,
                      height: 1.2,
                    ),
                  ),
                  Gaps.v16,
                  Text(
                    'Videos are personalized for you based on what you watch, like, and share.',
                    style: TextStyle(
                      color: Theme.of(context).colorScheme.secondary,
                      fontSize: Sizes.size20,
                    ),
                  ),
                ],
              ),
              secondChild: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Gaps.v80,
                  Text(
                    'Follow the rules',
                    style: TextStyle(
                      color: Theme.of(context).colorScheme.primary,
                      fontSize: Sizes.size40,
                      fontWeight: FontWeight.bold,
                      height: 1.2,
                    ),
                  ),
                  Gaps.v16,
                  Text(
                    'Take care of on another!',
                    style: TextStyle(
                      color: Theme.of(context).colorScheme.secondary,
                      fontSize: Sizes.size20,
                    ),
                  )
                ],
              ),
              crossFadeState: _showInPage == Page.firstPage
                  ? CrossFadeState.showFirst
                  : CrossFadeState.showSecond,
              duration: const Duration(milliseconds: 300),
            ),
          ),
        ),
        bottomNavigationBar: BottomAppBar(
          height: 100,
          color: Colors.white,
          surfaceTintColor: Colors.white,
          child: Padding(
            padding: const EdgeInsets.symmetric(
              vertical: Sizes.size14,
              horizontal: Sizes.size24,
            ),
            child: AnimatedOpacity(
              opacity: _showInPage == Page.firstPage ? 0 : 1,
              duration: const Duration(milliseconds: 300),
              child: CupertinoButton(
                alignment: Alignment.center,
                onPressed: _onEnterAppTap,
                color: Theme.of(context)
                    .colorScheme
                    .onPrimaryContainer
                    .withOpacity(0.7),
                child: Text(
                  'Enter the app!',
                  style: TextStyle(
                    color: Theme.of(context).colorScheme.onPrimary,
                    fontSize: Sizes.size16,
                  ),
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}

class TabWidgetV1 extends StatelessWidget {
  const TabWidgetV1({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 3,
      child: Scaffold(
        body: const SafeArea(
          child: TabBarView(
            children: [
              Padding(
                padding: EdgeInsets.symmetric(
                  horizontal: Sizes.size24,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Gaps.v52,
                    Text(
                      'Watch cool videos',
                      style: TextStyle(
                        fontSize: Sizes.size40,
                        fontWeight: FontWeight.bold,
                        height: 1.2,
                      ),
                    ),
                    Gaps.v16,
                    Text(
                      'Videos are personalized for you based on what you watch, like, and share.',
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
                      'Follow the rules of the app',
                      style: TextStyle(
                        fontSize: Sizes.size40,
                        fontWeight: FontWeight.bold,
                        height: 1.2,
                      ),
                    ),
                    Gaps.v16,
                    Text(
                      'Videos are personalized for you based on what you watch, like, and share.',
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
                      'Enjoy the ride',
                      style: TextStyle(
                        fontSize: Sizes.size40,
                        fontWeight: FontWeight.bold,
                        height: 1.2,
                      ),
                    ),
                    Gaps.v16,
                    Text(
                      'Videos are personalized for you based on what you watch, like, and share.',
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
          elevation: 5,
          child: Container(
            padding: const EdgeInsets.symmetric(
              vertical: Sizes.size48,
            ),
            child: const Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                TabPageSelector(
                  color: Colors.red,
                  selectedColor: Colors.black38,
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
