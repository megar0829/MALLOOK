import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';

class CustomCircularWaitWidget extends StatefulWidget {
  Function? onTap;

  CustomCircularWaitWidget({
    super.key,
    this.onTap,
  });

  @override
  State<CustomCircularWaitWidget> createState() =>
      _CustomCircularWaitWidgetState();
}

class _CustomCircularWaitWidgetState extends State<CustomCircularWaitWidget> {
  bool _showWaitWidget = true;
  bool _showButton = true;

  @override
  void initState() {
    super.initState();
    Future.delayed(const Duration(seconds: 3), () {
      setState(() {
        _showWaitWidget = false;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    var size = MediaQuery.of(context).size;
    return _showWaitWidget
        ? Padding(
            padding: const EdgeInsets.symmetric(
              vertical: Sizes.size32,
            ),
            child: Center(
              child: Column(
                children: [
                  CircularProgressIndicator(
                    color: Theme.of(context).primaryColorLight,
                  ),
                  Gaps.v10,
                  Text(
                    '쪼매 기다리쇼 금방 돼여',
                    style: TextStyle(
                      fontSize: Sizes.size14,
                      color: Theme.of(context).primaryColor,
                      fontWeight: FontWeight.bold,
                    ),
                  )
                ],
              ),
            ),
          )
        : widget.onTap == null
            ? const SizedBox.shrink()
            : _showButton
                ? Container(
                    padding: EdgeInsets.symmetric(
                      vertical: Sizes.size12,
                      horizontal: size.width / 3,
                    ),
                    child: ElevatedButton(
                      onPressed: () {
                        widget.onTap!();
                        setState(() {
                          _showButton = false;
                        });
                      },
                      child: const Text('더보기'),
                    ),
                  )
                : const SizedBox.shrink();
  }
}
