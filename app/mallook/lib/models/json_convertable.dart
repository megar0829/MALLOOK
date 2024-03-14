abstract class JsonConvertor<T> {
  T fromJson(dynamic json);
  Map<String, dynamic> toJson();
}
