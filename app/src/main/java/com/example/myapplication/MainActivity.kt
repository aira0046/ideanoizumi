package com.example.myapplication

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val realm:Realm= Realm.getDefaultInstance()
        originalbutton.setOnClickListener {
            val toRealmIdeaApplicationIntent = Intent(this, RealmIdeaApplication::class.java)
            startActivities(toRealmIdeaApplicationIntent)
        }
    }
}