import 'package:flutter/material.dart';

import 'package:location_services/location_services_plugin.dart';

void main() => runApp(new MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Location Services'),
        ),
        body: Center(
          child: FutureBuilder<bool>(
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
          ),
        ),
      ),
    );
  }
}
