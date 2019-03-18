# Features in App Development

## SMS
https://medium.com/@chiragpatel_52497/send-sms-from-android-application-a8a9c1ada8b7

### Send SMS

In activity_main.xml add a button with id btn_send
```xml
<Button
            android:text="Send sms"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/btn_send"
            android:textSize="30sp"
            app:layout_constraintEnd_toEndOf="parent"
            android:layout_marginEnd="8dp"
            app:layout_constraintStart_toStartOf="parent"
            android:layout_marginStart="8dp"
            android:layout_marginTop="8dp"
            app:layout_constraintTop_toTopOf="parent"
            android:layout_marginBottom="8dp"
            app:layout_constraintBottom_toBottomOf="parent"/>
```
It should look something like this:

<img src="https://github.com/pernillelorup/AppDevelopmentFeatures/blob/master/Send_button.png" width="430" height="400">


SmsManager API needs SEND_SMS permission. Add permission to the manifest file:

```kotlin
<uses-permission android:name="android.permission.SEND_SMS"/>
```


## Advanced properties
* field - Getter og setter
* late init
* by (property delegation) (lazy) (med mere)
* (Extension properties)


https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet
