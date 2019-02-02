package asia.ivity.flutter.location_services;

import android.content.ContentResolver;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** LocationServicesPlugin */
public class LocationServicesPlugin implements MethodCallHandler {
  /** Plugin registration. */
  private static ContentResolver contentResolver;
  private static LocationManager locationManager;

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel =
        new MethodChannel(registrar.messenger(), "location_services");
    channel.setMethodCallHandler(new LocationServicesPlugin());

    contentResolver = registrar.activeContext().getContentResolver();
    locationManager =
        (LocationManager) registrar.activeContext().getSystemService(Context.LOCATION_SERVICE);
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {

    if (call.method.equals("isLocationServiceActivated")) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        try {
          boolean yesNo = Settings.Secure.getInt(
              contentResolver,
              Settings.Secure.LOCATION_MODE
                                                ) != Settings.Secure.LOCATION_MODE_OFF;
          result.success("" + yesNo);
          return;
        } catch (Settings.SettingNotFoundException e) {
          //          RxBleLog.w(e, "Could not use LOCATION_MODE check. Falling back to legacy method.");
        }
      }
      boolean yesNo = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
          || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

      result.success("" + yesNo);
    } else {
      result.notImplemented();
    }
  }
}
