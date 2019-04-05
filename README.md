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

Inside onCreate() in MainActivity make a setOnClickListener() on the send button from activity_main.xml

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

<img src="https://github.com/pernillelorup/AppDevelopmentFeatures/blob/master/Images/allowMessages1.png" width="300" height="170">

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

We use something called SmsManager which is an object that manages SMS operations like sending texts. By calling getDafult(), we get access to the operations. 

The sendTextMessage() is the method you use when sending a text based message. 
You need 5 parameters: 
* destination address
* service center address. Use null to use the current default service center
* text message
* sentIntent: use null
* deliveryIntent: use null

In the button we add a Toast to give us a message, that the sms has been sent. 

<img src="https://github.com/pernillelorup/AppDevelopmentFeatures/blob/master/Images/messageSent.png" width="200" height="400">

The last thing to do is to add permission to the manifest file, because the SmsManager needs SEND_SMS permission.

```kotlin
<uses-permission android:name="android.permission.SEND_SMS"/>
```

Now you should be ready to run the app and press the send button. When you see the toast, you can open your messages on your device and see that you have received a text message.

<img src="https://github.com/pernillelorup/AppDevelopmentFeatures/blob/master/Images/messageReceived.png" width="200" height="400">
 
 
 
 
 
 
### Receive SMS

#### MainActivity

In the onCreate() method in MainActivity, add the following code.
It does the same as in the previous example. It will show a box on your screen asking if you want the app to send and view SMS messages on your device. Press allow.

```kotlin
class MainActivity : AppCompatActivity() {

    private val requestReceiveSms = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) !=
            PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), requestReceiveSms)
        }
    }
}
```

In the AndroidManifest.xml add the code below.
```xml
<uses-permission android:name="android.permission.RECEIVE_SMS"/>
```

Find where the activity ends in AndroidManifest.xml and add the code below. 
```xml
<receiver android:name=".SmsReceiver">
        <intent-filter>
             <action android:name="android.provider.Telephony.SMS_RECEIVED"/>
        </intent-filter>
</receiver>
```
The receiver name *.SmsReceiver* will be red. 
Add a new Kotlin class with the same name (SmsReceiver) and add the following code. 
```kotlin

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val extras = intent.extras

        if(extras != null) {
            val sms = extras.get("pdus") as Array<Any>
            for(i in sms.indices) {
                val format = extras.getString("format")
                val smsMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    SmsMessage.createFromPdu(sms[i] as ByteArray, format)
                } else {
                    SmsMessage.createFromPdu(sms[i] as ByteArray)
                }
                val phoneNumber = smsMessage.originatingAddress
                val messageText = smsMessage.messageBody.toString()

                Toast.makeText(
                    context,
                    "phoneNumber: (private)\n" +
                            "messageText: $messageText",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

```
We start by adding a new value called extras. extras is a Bundle of additional information from Intent. This is used to provide extended information to the component. For example, if we have an action to send an e-mail or text message, we could also include extra pieces of data here to supply a subject, body etc.

**Indsæt forklaring på resten**
Eksemplet med at modtage beskeder virker ikke helt. 
Jeg kan heller ikke få Anders' eksempel til at virke. 




## Advanced properties
* field - Getter og setter
* late init
* by (property delegation) (lazy)

A property in kotlin consists of a field plus a getter and optionally a setter. This means that once a property is specified in a class, default get and set methods are created behind the scenes, and access to the variable from the outside is done by dot-notation. This is as opposed to Java, where the code for a simple Java 'beans' class is very verbose in comparison with Kotlin.

The type of a property does not need to be explicit, if it can be deferred from the initializer.

Properties are defined as either val or var, the former one being immutable.

```kotlin
class Person (val name: String, var age: Int) {
	…
}
```

In this example, a constructor is provided containing the parameters passed when creating an instance of the class. Because val and var are specified, properties are implicitly created in the class, and therefore also the default get/set methods. From the 'outside' of the class, the properties can be accessed as follows:

```kotlin
p = Person('Joe', 35)
nameOfPerson = p.name
```

Custom get and set methods can be provided. When specifying the behaviour of the custom get or set methods, it is done by reference to the backing field by using the keyword 'field'.

```kotlin
val name: String
	get() = 'My name is ' + field
var age: Int
	set(value) {
	field += value
	}
```

Backing fields are created when needed, meaning that equivalent Java-code would need a named property for using the get and set methods. A backing field is always created when at least one default get or set method is created, or when a custom get or set method uses the 'field' keyword to reference the property.

#### Lateinit

Can be used on properties declared inside the body of a class - not in constructors. The property must not have a custom getter or setter. Also for top-level properties.
Lateinit is useful for Android development when declaring properties whose value will be set in the lifecycle methods, and which we thereby can be sure will not be null once accessed.

For example, a Button declared in an activity might first be initialized in the Activity's onCreate() method, so it must be declared as nullable:

```kotlin
var submitButton: Button? = null
```

Instead of this, we can declare the variable as lateinit, meaning that we 'guarantee' that the variable will be assigned a value before it is used.

```kotlin
lateinit var submitButton: Button
```

It is possible to check if a lateinit property has been initialized using the following syntax:

```kotlin
if(::propertyToCheck.isInitialized)
	{ … code goes here}
```

#### By lazy and delegated properties

Delegated properties:
Delegating a property means implementing a class which will be responsible for returning a value when the property's get method is called. 

The following example is taken from Kotlin's own documentation:

```kotlin
class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }
 
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}
```

What we should notice here, is that when get and set methods, which are delegated, are called, a reference to the object calling the get/set method is passed as a parameter along with a description of the property.

Output when calling get() in this case according to Kotlin's documentation:

```kotlin
val e = Example()
println(e.p)
Example@33a17727, thank you for delegating ‘p’ to me!
```

And when calling set(), a 3rd parameter is provided, namely the value being assigned:

```kotlin
e.p = "NEW"
NEW has been assigned to ‘p’ in Example@33a17727.
```

There are some standard delegates in Kotlin, among which are 'by lazy.'

When a property is declared by lazy, a function is provided which will be called upon the first access of the property, which is then initialized this way. The function is only called once (the first time the property is accessed), and then a value specified inside the function is stored as the value of the property.



https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet
