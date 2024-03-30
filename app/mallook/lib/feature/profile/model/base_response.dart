class BaseResponse<T> {
  final int status;
  final String message;
  final T? result;

  BaseResponse({
    required this.status,
    required this.message,
    required this.result,
  });

  factory BaseResponse.fromJson(
      Map<String, dynamic> json, T Function(dynamic)? fromJsonT) {
    return BaseResponse(
      status: json['status'] as int,
      message: json['message'] as String,
      result: json['result'] == null ? null : fromJsonT!(json['result']),
    );
  }

  Map<String, dynamic> toJson(Map<String, dynamic> Function(T) toJsonT) {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['status'] = this.status;
    data['message'] = this.message;
    data['result'] = null;
    if (result != null) {
      data['result'] = toJsonT(result as T);
    }
    return data;
  }
}
