class Address {
  Address({
    this.city,
    this.district,
    this.address,
    this.zipcode,
  });

  Address.fromJson(dynamic json) {
    city = json['city'];
    district = json['district'];
    address = json['address'];
    zipcode = json['zipcode'];
  }

  String? city;
  String? district;
  String? address;
  String? zipcode;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['city'] = city;
    map['district'] = district;
    map['address'] = address;
    map['zipcode'] = zipcode;
    return map;
  }
}
