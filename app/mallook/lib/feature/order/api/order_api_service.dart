import 'dart:math';

import 'package:mallook/feature/order/model/order_detail_model.dart';
import 'package:mallook/feature/product/model/product.dart';
import 'package:mallook/global/cart/model/page_cart_item.dart';

class OrderApiService {
  static Future<OrderDetail> getOrderDetail(num orderId) async {
    OrderDetail orderDetail = OrderDetail(
      id: 1,
      items: [
        CartItem(
          product: Product(
            name: "데일리 바이오 셔츠 - 10 COLOR",
            price: Random().nextInt(300000),
            image:
                'https://image.msscdn.net/images/goods_img/20221226/2996536/2996536_16919781777813_125.jpg',
            brandName: "수아레",
          ),
          productId: "soifnsonfownownfo2non211",
          name: "데일리 바이오 셔츠 - 10 COLOR",
          price: Random().nextInt(300000),
          fee: Random().nextInt(10000),
          image:
              'https://image.msscdn.net/images/goods_img/20221226/2996536/2996536_16919781777813_125.jpg',
          count: 1,
          size: 'L',
          color: 'red',
        ),
        CartItem(
          product: Product(
            name: "데일리 바이오 셔츠 - 10 COLOR",
            price: Random().nextInt(300000),
            image:
                'https://image.msscdn.net/images/goods_img/20221226/2996536/2996536_16919781777813_125.jpg',
            brandName: "수아레",
          ),
          productId: "soifnsonfownownfo2non211",
          name: "데일리 바이오 셔츠 - 10 COLOR",
          price: Random().nextInt(300000),
          fee: Random().nextInt(10000),
          image:
              'https://image.msscdn.net/images/goods_img/20221226/2996536/2996536_16919781777813_125.jpg',
          count: 2,
          size: 'L',
          color: 'red',
        ),
        CartItem(
          product: Product(
            name: "데일리 바이오 셔츠 - 10 COLOR",
            price: Random().nextInt(300000),
            image:
                'https://image.msscdn.net/images/goods_img/20221226/2996536/2996536_16919781777813_125.jpg',
            brandName: "수아레",
          ),
          productId: "soifnsonfownownfo2non211",
          name: "데일리 바이오 셔츠 - 10 COLOR",
          price: Random().nextInt(300000),
          fee: Random().nextInt(10000),
          image:
              'https://image.msscdn.net/images/goods_img/20221226/2996536/2996536_16919781777813_125.jpg',
          count: 1,
          size: 'L',
          color: 'red',
        ),
      ],
      totalPrice: 10000,
      totalFee: 3000,
      totalCount: 2,
    );

    await Future.delayed(const Duration(milliseconds: 500));
    return orderDetail;
  }

  static Future<List<OrderDetail>> getOrderedList(int page) async {
    List<OrderDetail> orderDetailListInstance = [];
    return orderDetailListInstance;
  }
}
