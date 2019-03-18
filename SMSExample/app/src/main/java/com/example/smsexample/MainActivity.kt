package com.example.smsexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val requestSendSms: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send_sms.setOnClickListener {
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

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == requestSendSms) sendSms()
    }

    private fun sendSms() {
        val number = "4512123434"
        val text = "Test message"

        SmsManager.getDefault().sendTextMessage(number, null, text, null, null)

        Toast.makeText(this, "Sms sent", Toast.LENGTH_SHORT).show()
    }
}
