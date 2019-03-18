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

<img src="https://github.com/pernillelorup/AppDevelopmentFeatures/blob/master/Images/allowMessages.png" width="300" height="150">

Below onCreate(), make these two methods:

```kotlin
override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == requestSendSms) sendSms()
    }
```


```kotlin
    private fun sendSms() {
        val number = "1234345678"
        val text = "Hello World"

        SmsManager.getDefault().sendTextMessage(number, null, text, null, null)

        Toast.makeText(this, "Sms sent", Toast.LENGTH_SHORT).show()
    }
```
The onRequestPermissionsResult() is an interface and is the contract for receiving the results for permission requests.

The sendSms() function will make two instances; a number with a String containing the receiving phone number, and a text with the text-string you want to send. 

We use something called SmsManager which is an object that manages SMS operations like sending text. By calling getDafult(), we get access to the operations. 

The sendTextMessage() is the method you use when sending a text based message. 
You need 5 parameters: 
* destination address
* service center address. Use null to use the current default service center
* text message
* sentIntent: use null
* deliveryIntent: use null

In the button we add a Toast to give us a message, that the sms has been sent. 

<img scr="https://github.com/pernillelorup/AppDevelopmentFeatures/blob/master/Images/messageSent.png" width="430" height="400">

The last thing to do is to add permission to the manifest file, because the SmsManager needs SEND_SMS permission.

```kotlin
<uses-permission android:name="android.permission.SEND_SMS"/>
```


## Advanced properties
* field - Getter og setter
* late init
* by (property delegation) (lazy) (med mere)
* (Extension properties)


https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet
