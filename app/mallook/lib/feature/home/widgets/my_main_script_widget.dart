import 'package:flutter/material.dart';
import 'package:mallook/feature/home/models/script.dart';
import 'package:mallook/feature/home/widgets/my-script-box.dart';

class MyMainScriptWidget extends StatelessWidget {
  const MyMainScriptWidget({
    super.key,
    required Future<Script> script,
  }) : _script = script;

  final Future<Script> _script;

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: _script,
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          return MyScriptBox(script: snapshot.data!);
        }
        return const Center(
          child: CircularProgressIndicator(),
        );
      },
    );
  }
}
