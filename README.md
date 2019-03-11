# location_services

Determine if location services are enabled on Android or iOS.

Users may choose to disable ANY access to location services within their phone's
settings pages. This plugin will read the public or secure settings (Android)
and simply inform.

Sample usage in a `FutureBuilder`:

``` dart
import 'package:location_services/location_services_plugin.dart';
```

``` dart
FutureBuilder<bool>(
    future: LocationServicesPlugin.isLocationServiceActivated,
    builder: (context, snapshot) {
        if (snapshot.hasError) {
            return Text('Error: ${snapshot.error}');
        }
        if (!snapshot.hasData) {
            return Container(
                width: 32,
                height: 32,
                child: CircularProgressIndicator(),
            );
        }

        return Text('Enabled: ${snapshot.data}');
    },
)
```