package com.quickreach.sender

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val button = Button(this).apply {
            text = "Enable WhatsApp Auto-Send Permission"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
                Toast.makeText(this@MainActivity, "Turn ON QuickSender in Accessibility settings", Toast.LENGTH_LONG).show()
            }
        }
        setContentView(button)
    }
}