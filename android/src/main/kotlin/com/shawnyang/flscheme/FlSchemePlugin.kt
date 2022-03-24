package com.shawnyang.flscheme

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.lang.Exception

/** FlschemePlugin */
class FlSchemePlugin: FlutterPlugin, MethodCallHandler {
  private lateinit var channel : MethodChannel
  private lateinit var context: Context

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flscheme")
    channel.setMethodCallHandler(this)
    context = flutterPluginBinding.applicationContext
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "launchScheme") {
      launchScheme(context,
        call.argument<String>("scheme"),
        call.argument<String>("packageName"),
        result)
    }
  }

  private fun launchScheme(context: Context, scheme: String?, packageName: String?, result: Result){
    if(scheme != null){
      val intent = Intent()
      if(packageName != null){
        intent.setPackage(packageName)
      }
      intent.data = Uri.parse(scheme)
      intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
      try {
        context.startActivity(intent)
        result.success("Target launched")
      }catch (e: Exception){
        when(e){
          is ActivityNotFoundException ->
            result.error("ActivityNotFoundException", "ActivityNotFoundException", "Target activity not found")
          else ->
            result.error("Exception", "Launch Intent Exception", "${e.message}")
        }
      }
    }
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
