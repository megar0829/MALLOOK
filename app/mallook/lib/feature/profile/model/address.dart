class Address {
  String? city;
  String? district;
  String? address;
  String? zipcode;

  Address({this.city, this.district, this.address, this.zipcode});

  Address.fromJson(Map<String, dynamic> json) {
    city = json['city'];
    district = json['district'];
    address = json['address'];
    zipcode = json['zipcode'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['city'] = city;
    data['district'] = district;
    data['address'] = address;
    data['zipcode'] = zipcode;
    return data;
  }
}
