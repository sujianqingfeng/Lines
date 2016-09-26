# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/baixiaokang/Library/Android/sdk/tools/proguard/proguard-android.txt
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
#-------------------------------------------定制化区域----------------------------------------------
#---------------------------------1.实体类---------------------------------

-keep class com.sujian.lines.** { *; }

#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------


    #如果用用到Gson解析包的，直接添加下面这几行就能成功混淆，不然会报错。
    #gson
    #-libraryjars libs/gson-2.2.2.jar
    -keepattributes Signature
    # Gson specific classes
    -keep class sun.misc.Unsafe { *; }
    # Application classes that will be serialized/deserialized over Gson
    -keep class com.google.gson.examples.android.model.** { *; }


    #butterknife混淆
    -keep class butterknife.** { *; }
    -dontwarn butterknife.internal.**
    -keep class **$$ViewBinder { *; }

    -keepclasseswithmembernames class * {
        @butterknife.* <fields>;
    }

    -keepclasseswithmembernames class * {
        @butterknife.* <methods>;
    }

    #glide
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }


    #图片选择器rxgalleryfinal
    #1.support-v7-appcompat
    -keep public class android.support.v7.widget.** { *; }
    -keep public class android.support.v7.internal.widget.** { *; }
    -keep public class android.support.v7.internal.view.menu.** { *; }

    -keep public class * extends android.support.v4.view.ActionProvider {
        public <init>(android.content.Context);
    }

    #2.rxjava
    -keep class rx.schedulers.Schedulers {
        public static <methods>;
    }
    -keep class rx.schedulers.ImmediateScheduler {
        public <methods>;
    }
    -keep class rx.schedulers.TestScheduler {
        public <methods>;
    }
    -keep class rx.schedulers.Schedulers {
        public static ** test();
    }
    -dontwarn sun.misc.**
    -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
        long producerIndex;
        long consumerIndex;
    }
    -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
        rx.internal.util.atomic.LinkedQueueNode producerNode;
    }
    -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
        rx.internal.util.atomic.LinkedQueueNode consumerNode;
    }

    #3.retrolambda
    -dontwarn java.lang.invoke.*

    #4.support-v4
    -keep class android.support.v4.** { *; }
    -keep interface android.support.v4.** { *; }

    #5.ucrop
    -dontwarn com.yalantis.ucrop**
    -keep class com.yalantis.ucrop** { *; }
    -keep interface com.yalantis.ucrop** { *; }

    #6.photoview
    -keep class uk.co.senab.photoview** { *; }
    -keep interface uk.co.senab.photoview** { *; }

    #7.rxgalleryfinal
    -keep class cn.finalteam.rxgalleryfinal.ui.widget** { *; }

    -keepclassmembers class * extends android.app.Activity {
       public void *(android.view.View);
    }
    -keepclassmembers enum * {
        public static **[] values();
        public static ** valueOf(java.lang.String);
    }
    -keep class * implements android.os.Parcelable {
      public static final android.os.Parcelable$Creator *;
    }
    -keepclassmembers class **.R$* {
        public static <fields>;
    }

    -keepattributes *Annotation*
    -keepclasseswithmembernames class * {
        native <methods>;
    }
    -keepclassmembers public class * extends android.view.View {
       void set*(***);
       *** get*();
    }



    #okio
    -dontwarn com.facebook.android.BuildConfig
    -dontwarn rx.**
    -dontwarn okio.**
    -dontwarn com.squareup.okhttp.*
    -dontwarn retrofit.appengine.UrlFetchClient
    -keepattributes Annotation
    -keep class retrofit.** { *; }
    -keepclasseswithmembers class * {
    @retrofit.http.* <methods>; }
    -keepattributes Signature


#-------------------------------------------------------------------------

#---------------------------------3.与js互相调用的类------------------------



#-------------------------------------------------------------------------

#---------------------------------4.反射相关的类和方法-----------------------

  #反射工具类
    -keep public class com.sujian.lines.base.util.TUtil

#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------

#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------



