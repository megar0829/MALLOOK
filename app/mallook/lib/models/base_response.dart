import 'package:mallook/models/json_convertable.dart';

class BaseResponse<T extends JsonConvertor<T>> {
  BaseResponse({
    required this.status,
    required this.message,
    required this.result,
  });

  factory BaseResponse.fromJson(dynamic json, T Function(dynamic) fromJsonT) {
    return BaseResponse<T>(
      status: json['status'],
      message: json['message'],
      result: json['result'] != null ? fromJsonT(json['result']) : null,
    );
  }

  int? status;
  String? message;
  T? result;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['status'] = status;
    map['message'] = message;
    if (result != null) {
      map['result'] = result?.toJson();
    }
    return map;
  }
}
