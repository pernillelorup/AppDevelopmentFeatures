# Features in App Development

## SMS
https://medium.com/@chiragpatel_52497/send-sms-from-android-application-a8a9c1ada8b7

### Send SMS

> AndroidManifest.xml
```Kotlin
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="com.example.smsexample">

    <uses-permission android:name="android.permission.SEND_SMS"/>

    <application
            android:allowBackup="true"
            android:icon="@mipmap/ic_launcher"
            android:label="@string/app_name"
            android:roundIcon="@mipmap/ic_launcher_round"
            android:supportsRtl="true"
            android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN"/>

                <category android:name="android.intent.category.LAUNCHER"/>
            </intent-filter>
        </activity>
    </application>

</manifest>
```

## Advanced properties
* field - Getter og setter
* late init
* by (property delegation) (lazy) (med mere)
* (Extension properties)


https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet
