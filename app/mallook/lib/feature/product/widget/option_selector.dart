import 'package:dropdown_button2/dropdown_button2.dart';
import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';

class OptionSelector extends StatefulWidget {
  final List<String> items;
  final String? selectedItem;
  final String? hintText;
  final ValueChanged<String?> onChanged;
  final bool isEnable;

  const OptionSelector({
    super.key,
    required this.items,
    required this.onChanged,
    required this.hintText,
    required this.selectedItem,
    this.isEnable = true,
  });

  @override
  State<OptionSelector> createState() => _OptionSelectorState();
}

class _OptionSelectorState extends State<OptionSelector> {
  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        DropdownButtonHideUnderline(
          child: DropdownButton2<String>(
            isExpanded: true,
            hint: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                const Icon(
                  Icons.list,
                  size: Sizes.size20,
                  color: Colors.black,
                ),
                Gaps.h10,
                Expanded(
                  child: Text(
                    widget.hintText == null
                        ? 'Select Item!!!'
                        : widget.hintText!,
                    style: TextStyle(
                      fontSize: Sizes.size16,
                      fontWeight: FontWeight.bold,
                      color: Colors.grey.shade600,
                    ),
                    overflow: TextOverflow.ellipsis,
                  ),
                ),
              ],
            ),
            items: [
              for (int index = 0; index < widget.items.length; index++)
                DropdownMenuItem<String>(
                  value: widget.items[index],
                  child: Text(
                    widget.items[index],
                    maxLines: 3,
                    style: TextStyle(
                      fontSize: Sizes.size16,
                      fontWeight: FontWeight.bold,
                      color: Colors.grey.shade800,
                    ),
                    overflow: TextOverflow.ellipsis,
                  ),
                ),
            ],
            value: widget.selectedItem,
            onChanged: (value) {
              setState(() {
                widget.onChanged(value);
              });
            },
            buttonStyleData: ButtonStyleData(
              height: Sizes.size48,
              padding: const EdgeInsets.symmetric(
                horizontal: Sizes.size14,
              ),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(
                  Sizes.size14,
                ),
                border: Border.all(
                  color: Colors.black26,
                ),
                color: Colors.grey.shade50,
              ),
              elevation: 1,
            ),
            iconStyleData: IconStyleData(
              icon: const Icon(
                Icons.arrow_forward_ios_outlined,
              ),
              iconSize: Sizes.size18,
              iconEnabledColor: Theme.of(context).primaryColorDark,
            ),
            dropdownStyleData: DropdownStyleData(
              maxHeight: 200,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(
                  Sizes.size14,
                ),
                color: Colors.grey.shade50,
              ),
              offset: const Offset(0, -10),
              scrollbarTheme: ScrollbarThemeData(
                radius: const Radius.circular(
                  Sizes.size40,
                ),
                thickness: MaterialStateProperty.all(
                  Sizes.size8,
                ),
                thumbVisibility: MaterialStateProperty.all(
                  true,
                ),
                crossAxisMargin: Sizes.size4,
              ),
            ),
            menuItemStyleData: const MenuItemStyleData(
              height: Sizes.size40,
              padding: EdgeInsets.only(
                left: Sizes.size14,
                right: Sizes.size14,
              ),
            ),
          ),
        ),
        if (!widget.isEnable)
          IgnorePointer(
            ignoring: widget.isEnable,
            child: Container(
              height: Sizes.size48,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(
                  Sizes.size14,
                ),
                border: Border.all(
                  color: Colors.black26,
                ),
                color: Colors.black54.withOpacity(0.41),
              ),
            ),
          )
      ],
    );
  }
}
