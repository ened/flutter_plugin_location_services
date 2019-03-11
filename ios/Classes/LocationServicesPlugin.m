#import "LocationServicesPlugin.h"
#import <CoreLocation/CoreLocation.h>

@implementation LocationServicesPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  FlutterMethodChannel* channel = [FlutterMethodChannel
      methodChannelWithName:@"location_services"
            binaryMessenger:[registrar messenger]];
  LocationServicesPlugin* instance = [[LocationServicesPlugin alloc] init];
  [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
  if ([@"isLocationServiceActivated" isEqualToString:call.method]) {
    result([NSNumber numberWithBool:[self handleIsLocationServiceActivated]]);
  } else {
    result(FlutterMethodNotImplemented);
  }
}

- (BOOL)handleIsLocationServiceActivated {
    return CLLocationManager.locationServicesEnabled;
}

@end
