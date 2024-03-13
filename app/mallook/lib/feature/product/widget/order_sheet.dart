import 'package:flutter/material.dart';
import 'package:mallook/constants/gaps.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/product/widget/option_selector.dart';

class OrderSheet extends StatefulWidget {
  final String? title;
  final List<String> sizes;
  final List<String> colors;

  const OrderSheet(
      {super.key, required this.sizes, required this.colors, this.title});

  @override
  State<OrderSheet> createState() => _OrderSheetState();
}

class _OrderSheetState extends State<OrderSheet> {
  String? _selectedSize;

  String? _selectedColor;

  void _updateSize(String? newValue) {
    setState(() {
      _selectedSize = newValue;
    });
  }

  void _updateColor(String? newValue) {
    setState(() {
      _selectedColor = newValue;
    });
  }

  @override
  Widget build(BuildContext context) {
    var deviceSize = MediaQuery.of(context).size;
    return Container(
      height: deviceSize.height * 0.6,
      clipBehavior: Clip.hardEdge,
      decoration: BoxDecoration(
        color: Colors.grey.shade300,
        border: Border.all(
          color: Colors.grey.shade600,
          width: Sizes.size1,
        ),
        borderRadius: const BorderRadius.only(
          topLeft: Radius.circular(
            Sizes.size24,
          ),
          topRight: Radius.circular(
            Sizes.size24,
          ),
        ),
      ),
      child: Scaffold(
        backgroundColor: Colors.white,
        appBar: AppBar(
          surfaceTintColor: Colors.white,
          backgroundColor: Colors.white,
          automaticallyImplyLeading: false,
          elevation: 1,
          shadowColor: Colors.black,
          title: Center(
            child: Text(
              widget.title == null ? '' : widget.title!,
              style: const TextStyle(
                color: Colors.black,
                fontSize: Sizes.size18,
                overflow: TextOverflow.fade,
              ),
            ),
          ),
          actions: [
            IconButton(
              onPressed: () => Navigator.of(context).pop(),
              icon: Icon(
                Icons.close,
                color: Theme.of(context).primaryColor,
                size: Sizes.size24,
              ),
            )
          ],
        ),
        body: Padding(
          padding: const EdgeInsets.symmetric(
            vertical: Sizes.size20,
            horizontal: Sizes.size24,
          ),
          child: Column(
            children: [
              Container(
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size6,
                  horizontal: Sizes.size10,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      '사이즈',
                      style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: Sizes.size14,
                      ),
                    ),
                    Gaps.v6,
                    OptionSelector(
                      items: widget.sizes,
                      hintText: "사이즈 선택!",
                      onChanged: _updateSize,
                      selectedItem: _selectedSize,
                    )
                  ],
                ),
              ),
              Gaps.v20,
              Container(
                padding: const EdgeInsets.symmetric(
                  vertical: Sizes.size6,
                  horizontal: Sizes.size10,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      '컬러',
                      style: TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: Sizes.size14,
                      ),
                    ),
                    Gaps.v6,
                    OptionSelector(
                      items: widget.colors,
                      hintText: "컬러 선택!",
                      onChanged: _updateColor,
                      selectedItem: _selectedColor,
                    )
                  ],
                ),
              ),
            ],
          ),
        ),
        bottomNavigationBar: BottomAppBar(
          surfaceTintColor: Colors.white,
          color: Colors.white,
          elevation: 5,
          shadowColor: Colors.black,
          padding: const EdgeInsets.symmetric(
            vertical: Sizes.size12,
            horizontal: Sizes.size20,
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              OutlinedButton(
                style: OutlinedButton.styleFrom(
                  side: BorderSide(
                    color: Colors.grey.shade300,
                  ),
                  padding: const EdgeInsets.symmetric(
                    horizontal: Sizes.size32,
                    vertical: Sizes.size12,
                  ),
                ),
                onPressed: () {},
                child: Text(
                  '바로구매',
                  style: TextStyle(
                    color: Theme.of(context).primaryColor,
                    fontSize: Sizes.size18,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
              Gaps.h20,
              ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: Theme.of(context).primaryColor,
                  padding: const EdgeInsets.symmetric(
                    horizontal: Sizes.size32,
                    vertical: Sizes.size12,
                  ),
                ),
                onPressed: () {},
                child: const Text(
                  '장바구니',
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: Sizes.size18,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
