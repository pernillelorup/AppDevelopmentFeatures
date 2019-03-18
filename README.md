# Features in App Development

## SMS

### Send SMS

#### activity_main.xml

In activity_main.xml add a button with the id: btn_send

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

<img src="https://github.com/pernillelorup/AppDevelopmentFeatures/blob/master/Images/Send_button.png" width="430" height="400">

#### MainActivity

Inside onCreate() in MainActivity make an OnClickListener() on the send button from activity_main.xml

```kotlin
private val requestSendSms: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_send.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SEND_SMS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), requestSendSms)
            } else {
                sendSms()
            }
        }
    }
```
The above code will show a box on your screen asking if you want the app to send and view SMS messages on your device. Press allow.

<img src="https://github.com/pernillelorup/AppDevelopmentFeatures/blob/master/Images/allowMessages.png" width="250" height="150">


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
