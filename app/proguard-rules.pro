# Add project specific ProGuard rules here.
-keepattributes Signature
-keepattributes *Annotation*

# Keep Gson serialized classes
-keep class com.acurast.chat.data.model.** { *; }

# Keep OkHttp
-dontwarn okhttp3.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

# Keep Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.reflect.TypeToken { *; }
-keep class * extends com.google.gson.reflect.TypeToken