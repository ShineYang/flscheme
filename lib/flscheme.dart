
import 'dart:async';

import 'package:flutter/services.dart';

class Flscheme {
  static const MethodChannel _channel = MethodChannel('flscheme');
  static Future<String?> launchScheme({required String scheme, String? packageName}) async {
    return await _channel.invokeMethod('launchScheme', {
      "scheme": scheme,
      "packageName": packageName,
    });
  }
}
