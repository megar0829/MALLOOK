import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/onboarding/model/keyword.dart';
import 'package:mallook/global/mallook_snackbar.dart';

class KeywordButton extends StatefulWidget {
  const KeywordButton({
    super.key,
    required this.keyword,
    required this.add,
    required this.remove,
    required this.selected,
  });

  final Keyword keyword;
  final Set<Keyword> selected;
  final Function add;
  final Function remove;

  void addKeyword() {
    add(keyword);
  }

  void removeKeyword() {
    remove(keyword);
  }

  @override
  State<KeywordButton> createState() => _KeywordButtonState();
}

class _KeywordButtonState extends State<KeywordButton> {
  bool _isSelected = false;

  void _onTap() {
    if (widget.selected.length >= 4) {
      ScaffoldMessenger.of(context).showSnackBar(
        mallookSnackBar(title: "최대 4개 까지 선택 가능합니다."),
      );
      return;
    }
    setState(() {
      // 선택되지 않았던 경우
      if (!_isSelected) {
        widget.addKeyword();
      } else {
        widget.removeKeyword();
      }
      _isSelected = !_isSelected;
    });
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: _onTap,
      child: AnimatedContainer(
        padding: const EdgeInsets.symmetric(
          vertical: Sizes.size16,
          horizontal: Sizes.size24,
        ),
        decoration: BoxDecoration(
          color:
              _isSelected ? Theme.of(context).primaryColorLight : Colors.white,
          borderRadius: BorderRadius.circular(Sizes.size32),
          border: Border.all(
            color: _isSelected
                ? Theme.of(context).primaryColor.withOpacity(0.2)
                : Colors.grey.shade300,
          ),
          boxShadow: [
            BoxShadow(
              color: _isSelected
                  ? Theme.of(context).primaryColorLight
                  : Colors.grey.shade200,
              blurRadius: 3,
              spreadRadius: 3,
            )
          ],
        ),
        duration: const Duration(milliseconds: 300),
        child: Text(
          widget.keyword.name!,
          style: TextStyle(
            fontWeight: FontWeight.bold,
            color: _isSelected
                ? Theme.of(context).primaryColorDark
                : Colors.black87,
          ),
        ),
      ),
    );
  }
}
