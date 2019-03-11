package asia.ivity.flutter.location_services;

import android.content.ContentResolver;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** LocationServicesPlugin */
public class LocationServicesPlugin implements MethodCallHandler {
  private static final String TAG = "LocationServicesPlugin";
  /** Plugin registration. */
  private static ContentResolver contentResolver;

  private static LocationManager locationManager;

  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "location_services");
    channel.setMethodCallHandler(new LocationServicesPlugin());

    contentResolver = registrar.activeContext().getContentResolver();
    locationManager =
        (LocationManager) registrar.activeContext().getSystemService(Context.LOCATION_SERVICE);
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("isLocationServiceActivated")) {
      result.success(handleIsLocationServiceActivated());
    } else {
      result.notImplemented();
    }
  }

  private boolean handleIsLocationServiceActivated() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      try {
        return Settings.Secure.getInt(contentResolver, Settings.Secure.LOCATION_MODE)
            != Settings.Secure.LOCATION_MODE_OFF;
      } catch (Settings.SettingNotFoundException e) {
        Log.e(TAG, "Could not fetch the secure setting, fallback activated.");
      }
    }

    return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
  }
}
