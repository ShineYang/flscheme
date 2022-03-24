import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flscheme/flscheme.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> openWithScheme(String scheme) async {
    try {
      await Flscheme.launchScheme(scheme: scheme, packageName: 'com.linkedin.android');
    } catch (e) {
      print(e.toString());
    }
    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: TextButton(onPressed: () =>
              openWithScheme('https://www.linkedin.com/in/syedfaisal33'),
            child: const Text('launch scheme'),)
        ),
      ),
    );
  }
}
