# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Administrator\AppData\Local\Android\Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

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
-dontwarn android.os.**
-dontwarn com.android.internal.**
-keep class cn.fraudmetrix.sdk.**{*;}


-dontskipnonpubliclibraryclassmembers

-optimizations !code/simplification/arithmetic
-allowaccessmodification
-keepattributes *Annotation*
-keepattributes EnclosingMethod
# 混淆时不使用重载机制，正式发版时可以去掉此行以提高混淆强度
-useuniqueclassmembernames
-dontwarn com.squareup.**
-dontwarn okio.**
-dontwarn com.sina.weibo.sdk.**
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keep class com.sina.weibo.sdk.** { *;}
-dontwarn org.springframework.**
-keep class org.springframework.** { *;}
-keep class com.renn.rennsdk.**{*;}
-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}
-keep class com.tencent.mm.sdk.openapi.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.openapi.** implements com.tencent.mm.sdk.openapi.WXMediaMessage$IMediaObject {*;}
-dontwarn com.tendcloud.tenddata.**
-keep class com.tendcloud.tenddata.** {*;}
#-libraryjars libs/weibosdk.jar
#-libraryjars libs/weibosdkcore.jar
-ignorewarnings

#360登录
-dontwarn com.qihoo.**
-keep class cn.pp.** {*;}
-keep class com.yeepay.** {*;}
-keep class com.alipay.** {*;}
-keep class com.qihoo.** {*;}
-keep class com.qihoo360.** {*;}
-keep class com.qihoopp.** {*;}
-dontwarn org.apache.http.conn.ssl.SSLSocketFactory


 # 保持哪些类不被混淆：四大组件，应用类，配置类等等
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService



#for umeng begin
-keepclassmembers class * {
    public <init>(org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#for umeng end

#for dm begin
-dontwarn cn.dm.**
-keep class cn.dm.** {*;}
-keep class cn.dm.android.ui.interaction.** {*;}
-keepattributes *Annotation*
#for dm end

-dontwarn com.xiaomi.**
-keep class com.xiaomi.**{*;}
-keep class org.apache.thrift.**{*;}
-dontwarn org.apache.thrift.**


复制代码到剪切板
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**


# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.

-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
  public static android.os.Parcelable$Creator *;
}

-keepclassmembers class **.R$* {
    public static <fields>;
}

# The support library contains references to newer money versions.
# Don't warn about those in case this app is linking against an older
# money version.  We know about them, and they are safe.
#-libraryjars libs/android-support-v4.jar
-dontwarn android.support.**
-keep class android.support.** {*;}

-keep class org.jsoup.** {*;}
-keep interface org.jsoup.** {*;}

-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *;}
-keepnames class * {@butterknife.InjectView *;}

-keep class com.activeandroid.** { *; }
-dontwarn com.ikoding.app.biz.dataobject.**
-keep public class com.ikoding.app.biz.dataobject.** { *;}
-keepattributes *Annotation*

-keepclassmembers class ** {
    public void onEvent*(**);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepclassmembers @interface * {
    public ** value();
}

# Remove log codes
-assumenosideeffects public class android.util.Log {
    public static int d(java.lang.String, java.lang.String);
    public static int i(java.lang.String, java.lang.String);
    public static int e(java.lang.String, java.lang.String);
    public static int w(java.lang.String, java.lang.String);
    public static int v(java.lang.String, java.lang.String);
    public static int println(int, java.lang.String, java.lang.String);
}


-dontwarn com.android.volley.jar.**
-keep class android.os.SystemProperties {*;}
-keep class com.android.letvmanager.**{*;}
-keep class com.android.volley.**{*;}
-keep class com.letv.**{*;}
-keep class com.media.**{*;}


-keep class com.baidu.** { *; }
-keep class vi.com.gdi.bgl.android.**{*;}

#for gson
-keep class com.google.**{*;}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}


##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe {*;}
#-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class * extends com.asm.base.android.util.volley.api.BaseItem {*;}  ##这里需要改成解析到哪个  javabean

##---------------End: proguard configuration for Gson  ----------

-keepclasseswithmembers class com.facebook.** {*;}
-keepclasseswithmembers class com.squareup.** {*;}



-keep public class smileflowpig.platform.moneyplatfrom.R$*{
    public static final int *;
}

-keep class com.amap.** {*;}
-keep class com.aps.** {*;}
-keep class com.autonavi.amap.** {*;}

-keepclassmembers class ** {
    public void onEvent*(**);
}

-keep class com.alipay.** {*;}
 -keep class com.ta.utdid2.** {*;}
 -keep class com.ut.device.** {*;}
 -keep class org.json.alipay.** {*;}
 -keep class com.tencent.mm.** {*;}

 -dontshrink
 -dontoptimize
 -dontwarn com.google.android.maps.**
 -dontwarn android.webkit.WebView
 -dontwarn com.umeng.**
 -dontwarn com.tencent.weibo.sdk.**
 -dontwarn com.facebook.**


 -keep enum com.facebook.**
 -keepattributes Exceptions,InnerClasses,Signature
 -keepattributes *Annotation*
 -keepattributes SourceFile,LineNumberTable

 -keep public interface com.facebook.**
 -keep public interface com.tencent.**
 -keep public interface com.umeng.socialize.**
 -keep public interface com.umeng.socialize.sensor.**
 -keep public interface com.umeng.scrshot.**

 -keep public class com.umeng.socialize.* {*;}
 -keep public class javax.**
 -keep public class android.webkit.**

 -keep class com.facebook.**
 -keep class com.umeng.scrshot.**
 -keep public class com.tencent.** {*;}
 -keep class com.umeng.socialize.sensor.**

 -keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}

 -keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

 -keep class im.yixin.sdk.api.YXMessage {*;}
 -keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}



  -keep class org.vudroid.**{*;}
  -keep class com.joanzapata.pdfview.**{*;}

 -keep class com.lecloud.**{*;}
 -keep class com.letv.**{*;}
 -keep class com.avdmg.**{*;}
 -keep class com.letvcloud.**{*;}
 -keep class com.lidroid.**{*;}
 -keep class cn.mmachina.**{*;}
 -keep class cn.com.iresearch.**{*;}

 -keep class com.umeng.** {*;}
 -keep class u.upd.** {*;}

 -keep class khandroid.ext.apache.http.** {*;}
 -keep class org.apache.http.entity.mime.** {*;}


 -keep class uk.co.senab.photoview.** {*;}

 -keep class com.h6ah4i.android.widget.** {*;}
 -keep class com.bruce.pickerview.** {*;}
 -keep class com.google.gson.stream.** { *; }
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 -keepclassmembers class * {
    public <init>(org.json.JSONObject);
 }

 -keep class smileflowpig.platform.moneyplatfrom.MiPushReceiver {*;}
 -keep class smileflowpig.platform.factory.** { *; }

 -keep public class * implements com.bumptech.glide.module.GlideModule
 -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
   **[] $VALUES;
   public *;
 }

 -keepattributes *Annotation*
 -keepclassmembers class ** {
     @org.greenrobot.eventbus.Subscribe <methods>;
 }
 -keep enum org.greenrobot.eventbus.ThreadMode { *; }

 # Only required if you use AsyncExecutor
 -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
     <init>(java.lang.Throwable);
 }

 -keepclassmembers class smileflowpig.platform.moneyplatfrom.web.WebActivity$AndroidtoJs {
     <methods>;
 }


 #不混淆org.apache.http.legacy.jar
 -dontwarn android.net.compatibility.**
 -dontwarn android.net.http.**
 -dontwarn com.android.internal.http.multipart.**
 -dontwarn org.apache.commons.**
 -dontwarn org.apache.http.**
 -keep class android.net.compatibility.**{*;}
 -keep class android.net.http.**{*;}
 -keep class com.android.internal.http.multipart.**{*;}
 -keep class org.apache.commons.**{*;}
 -keep class org.apache.http.**{*;}



#友盟统计
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}



#推送
 -dontwarn com.vivo.push.**
  -keep class com.vivo.push.**{*; }
   -keep class com.vivo.vms.**{*; }
 -keep class com.smileflowpig.money.push.reciver.PushMessageReceiverImpl{*;}


 #这里com.xiaomi.mipushdemo.DemoMessageRreceiver改成app中定义的完整类名
 -keep class com.smileflowpig.money.push.reciver.MiPushReciver{*;}
 #可以防止一个误报的 warning 导致无法成功编译，如果编译使用的 Android 版本是 23。
 -dontwarn com.xiaomi.push.**




-ignorewarning
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}

-keep class com.huawei.android.hms.agent.**{*;}
-keep class com.huawei.gamebox.plugin.gameservice.**{*;}

