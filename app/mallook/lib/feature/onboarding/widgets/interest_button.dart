import 'package:flutter/material.dart';
import 'package:mallook/constants/sizes.dart';

class InterestButton extends StatefulWidget {
  const InterestButton({
    super.key,
    required this.interest,
    required this.add,
    required this.remove,
  });

  final String interest;
  final Function add;
  final Function remove;

  void addInterest() {
    add(interest);
  }

  void removeInterest() {
    remove(interest);
  }

  @override
  State<InterestButton> createState() => _InterestButtonState();
}

class _InterestButtonState extends State<InterestButton> {
  bool _isSelected = false;

  void _onTap() {
    setState(() {
      // 선택되지 않았던 경우
      if (!_isSelected) {
        widget.addInterest();
      } else {
        widget.removeInterest();
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
          widget.interest,
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
