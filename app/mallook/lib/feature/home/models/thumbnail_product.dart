class ThumbnailProduct {
  final int id;
  final String name;
  final int price;
  final int discountRatio;
  final String shoppingMall;
  final String shoppingMallImgUrl;

  final String image;

  ThumbnailProduct({
    required this.id,
    required this.name,
    required this.price,
    required this.image,
    required this.discountRatio,
    required this.shoppingMall,
    required this.shoppingMallImgUrl,
  });
}
