package com.cis357.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.amazonaws.mobile.client.AWSMobileClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI
        val greeting = findViewById<TextView>(R.id.usernameText)
        val signOutButton = findViewById<Button>(R.id.signOutButton)


        greeting.text = AWSMobileClient.getInstance().username

        signOutButton.setOnClickListener {
            AWSMobileClient.getInstance().signOut()
            greeting.text = "Guest"

            val i =
                Intent(this@MainActivity, AuthenticationActivity::class.java)
            startActivity(i)
        }
    }
}