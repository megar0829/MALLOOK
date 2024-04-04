import 'package:dropdown_button2/dropdown_button2.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:mallook/constants/sizes.dart';
import 'package:mallook/feature/coupon/model/page_my_coupon.dart';

class CartCouponDropdownWidget extends StatefulWidget {
  final List<MyCoupon> coupons;
  final Function onChange;
  final MyCoupon? selectedCoupon;
  final int totalPrice;

  const CartCouponDropdownWidget({
    super.key,
    required this.coupons,
    required this.onChange,
    required this.selectedCoupon,
    required this.totalPrice,
  });

  @override
  State<CartCouponDropdownWidget> createState() =>
      _CartCouponDropdownWidgetState();
}

class _CartCouponDropdownWidgetState extends State<CartCouponDropdownWidget> {
  static NumberFormat numberFormat = NumberFormat.currency(
    locale: 'ko_KR',
    symbol: '',
  );

  String _showCouponName(MyCoupon coupon) {
    if (coupon.name!.length <= 6) {
      return coupon.name!;
    }
    return '${coupon.name!.substring(0, 6)}...';
  }

  int _discountPrice(MyCoupon coupon) {
    // TODO: coupon dto update
    if (coupon.type == 'MONEY') {
      if (widget.totalPrice >= coupon.amount!) {
        return -(coupon.amount ?? 0).toInt();
      }
      return 0;
    }
    if (coupon.type == 'RATIO') {
      return -widget.totalPrice * (coupon.amount ?? 0) ~/ 100;
    }

    return 0;
  }

  List<DropdownMenuItem<MyCoupon>> _addDividersAfterItems(
      List<MyCoupon> items) {
    final List<DropdownMenuItem<MyCoupon>> menuItems = [];
    for (final item in items) {
      menuItems.addAll(
        [
          DropdownMenuItem<MyCoupon>(
            value: item,
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 8.0),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text(
                    _showCouponName(item),
                    style: const TextStyle(
                      fontSize: Sizes.size14,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  // TODO: Coupon dto update
                  Text(
                    item.type == 'MONEY'
                        ? '${numberFormat.format(item.amount)}₩'
                        : '${numberFormat.format(item.amount)}%',
                    style: const TextStyle(
                      color: Colors.blue,
                      fontWeight: FontWeight.bold,
                      fontSize: Sizes.size14,
                    ),
                  ),
                  Text(
                    '${numberFormat.format(_discountPrice(item))} ₩',
                    style: const TextStyle(
                      color: Colors.red,
                      fontWeight: FontWeight.bold,
                      fontSize: Sizes.size14,
                    ),
                  ),
                ],
              ),
            ),
          ),
          //If it's last item, we will not add Divider after it.
          if (item != items.last)
            const DropdownMenuItem<MyCoupon>(
              enabled: false,
              child: Divider(),
            ),
        ],
      );
    }
    return menuItems;
  }

  List<double> _getCustomItemsHeights() {
    final List<double> itemsHeights = [];
    for (int i = 0; i < (widget.coupons.length * 2) - 1; i++) {
      if (i.isEven) {
        itemsHeights.add(40);
      }
      //Dividers indexes will be the odd indexes
      if (i.isOdd) {
        itemsHeights.add(4);
      }
    }
    return itemsHeights;
  }

  @override
  Widget build(BuildContext context) {
    var deviceSize = MediaQuery.of(context).size;
    return DropdownButtonHideUnderline(
      child: DropdownButton2<MyCoupon>(
        isExpanded: true,
        hint: Text(
          '쿠폰 선택',
          style: TextStyle(
            fontSize: Sizes.size14,
            color: Theme.of(context).hintColor,
            fontWeight: FontWeight.bold,
          ),
        ),
        items: _addDividersAfterItems(widget.coupons),
        value: widget.selectedCoupon,
        onChanged: (MyCoupon? value) {
          setState(() {
            widget.onChange(value);
          });
        },
        buttonStyleData: ButtonStyleData(
          padding: const EdgeInsets.symmetric(horizontal: 16),
          height: 48,
          width: deviceSize.width * 3 / 4,
          decoration: BoxDecoration(
            border: Border.all(
              color: Colors.grey.shade400,
              width: Sizes.size1,
            ),
            borderRadius: BorderRadius.circular(
              Sizes.size14,
            ),
          ),
        ),
        dropdownStyleData: const DropdownStyleData(
          maxHeight: 200,
        ),
        menuItemStyleData: MenuItemStyleData(
          padding: const EdgeInsets.symmetric(horizontal: 8.0),
          customHeights: _getCustomItemsHeights(),
        ),
        iconStyleData: const IconStyleData(
          openMenuIcon: Icon(Icons.arrow_drop_up),
        ),
      ),
    );
  }
}
