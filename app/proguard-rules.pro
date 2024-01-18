# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class com.solo.solodemo.** { *; }
-keep class com.solodemo.** { *; }

-dontwarn org.slf4j.impl.StaticLoggerBinder
#
#-keepclassmembers class kotlinx.serialization.json.** {
#    *** Companion;
#}
#-keepclasseswithmembers class kotlinx.serialization.json.** {
#    kotlinx.serialization.KSerializer serializer(...);
#}
#-keepclasseswithmembers class .** {
#    kotlinx.serialization.KSerializer serializer(...);
#}
#
#-keep,includedescriptorclasses class my.package.**$$serializer { *; }
#-keep,allowobfuscation,allowshrinking interface retrofit2.Call
#-keep,allowobfuscation,allowshrinking class retrofit2.Response
#-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
#
#-dontwarn org.bouncycastle.jsse.BCSSLParameters
#-dontwarn org.bouncycastle.jsse.BCSSLSocket
#-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
#-dontwarn org.conscrypt.Conscrypt$Version
#-dontwarn org.conscrypt.Conscrypt
#-dontwarn org.conscrypt.ConscryptHostnameVerifier
#-dontwarn org.openjsse.javax.net.ssl.SSLParameters
#-dontwarn org.openjsse.javax.net.ssl.SSLSocket
#-dontwarn org.openjsse.net.ssl.OpenJSSE
#-dontwarn org.slf4j.impl.StaticLoggerBinder
#
#
-keep class com.google.gson.reflect.TypeToken
-keep class * extends com.google.gson.reflect.TypeToken
-keep public class * implements java.lang.reflect.Type

#-dontwarn com.airbnb.lottie.**
#-keep class com.airbnb.lottie.** {*;}
#
#-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
#-optimizationpasses 5
#-allowaccessmodification
#
#-dontusemixedcaseclassnames
#-dontskipnonpubliclibraryclasses
#-verbose
#
## Preserve some attributes that may be required for reflection.
#-keepattributes AnnotationDefault,
#                EnclosingMethod,
#                InnerClasses,
#                RuntimeVisibleAnnotations,
#                RuntimeVisibleParameterAnnotations,
#                RuntimeVisibleTypeAnnotations,
#                Signature
#
#-keep public class com.google.vending.licensing.ILicensingService
#-keep public class com.android.vending.licensing.ILicensingService
#-keep public class com.google.android.vending.licensing.ILicensingService
#-dontnote com.android.vending.licensing.ILicensingService
#-dontnote com.google.vending.licensing.ILicensingService
#-dontnote com.google.android.vending.licensing.ILicensingService
#
## For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
#-keepclasseswithmembernames,includedescriptorclasses class * {
#    native <methods>;
#}
#
## Keep setters in Views so that animations can still work.
#-keepclassmembers public class * extends android.view.View {
#    void set*(***);
#    *** get*();
#}
#
## We want to keep methods in Activity that could be used in the XML attribute onClick.
#-keepclassmembers class * extends android.app.Activity {
#    public void *(android.view.View);
#}
#
## For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#
#-keepclassmembers class * implements android.os.Parcelable {
#    public static final ** CREATOR;
#}
#
## Preserve annotated Javascript interface methods.
#-keepclassmembers class * {
#    @android.webkit.JavascriptInterface <methods>;
#}
#
## The support libraries contains references to newer platform versions.
## Don't warn about those in case this app is linking against an older
## platform version. We know about them, and they are safe.
#-dontnote android.support.**
#-dontnote androidx.**
#-dontwarn android.support.**
#-dontwarn androidx.**
#
## This class is deprecated, but remains for backward compatibility.
#-dontwarn android.util.FloatMath
#
## Understand the @Keep support annotation.
#-keep class android.support.annotation.Keep
#-keep class androidx.annotation.Keep
#
#-keep @android.support.annotation.Keep class * {*;}
#-keep @androidx.annotation.Keep class * {*;}
#
#-keepclasseswithmembers class * {
#    @android.support.annotation.Keep <methods>;
#}
#
#-keepclasseswithmembers class * {
#    @androidx.annotation.Keep <methods>;
#}
#
#-keepclasseswithmembers class * {
#    @android.support.annotation.Keep <fields>;
#}
#
#-keepclasseswithmembers class * {
#    @androidx.annotation.Keep <fields>;
#}
#
#-keepclasseswithmembers class * {
#    @android.support.annotation.Keep <init>(...);
#}
#
#-keepclasseswithmembers class * {
#    @androidx.annotation.Keep <init>(...);
#}
#
#-dontnote org.apache.http.**
#-dontnote android.net.http.**