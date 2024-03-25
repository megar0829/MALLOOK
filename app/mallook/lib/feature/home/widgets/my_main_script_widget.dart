import 'package:flutter/material.dart';
import 'package:mallook/feature/home/models/script.dart';
import 'package:mallook/feature/home/widgets/my-script-box.dart';
import 'package:mallook/feature/script/script_screen.dart';

class MyMainScriptWidget extends StatelessWidget {
  const MyMainScriptWidget({
    super.key,
    required Future<Script> script,
  }) : _script = script;

  final Future<Script> _script;

  void _moveToScriptDetail(BuildContext context, Script script) {
    Navigator.of(context).push(
      MaterialPageRoute(
        builder: (context) => ScriptScreen(
          scriptId: script.id!,
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
      future: _script,
      builder: (context, snapshot) {
        if (snapshot.hasData) {
          return GestureDetector(
            onTap: () => _moveToScriptDetail(
              context,
              snapshot.data!,
            ),
            child: MyScriptBox(script: snapshot.data!),
          );
        }
        return const Center(
          child: CircularProgressIndicator(),
        );
      },
    );
  }
}
