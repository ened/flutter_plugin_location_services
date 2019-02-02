import 'dart:async';

import 'package:flutter/services.dart';

class LocationServicesPlugin {
  static const MethodChannel _channel =
      const MethodChannel('location_services');

  static Future<bool> get isLocationServiceActivated async {
    String result = await _channel.invokeMethod('isLocationServiceActivated');
    return result == "true";
  }
}
