package com.RivskyInc.movienow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {

    private lateinit var backInfo : Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        backInfo = findViewById(R.id.buttonBackFromInfo)

        backInfo.setOnClickListener{
            finish()
        }


    }
    companion object{
        fun newIntent(context: Context) : Intent {
            return    Intent(context, InfoActivity::class.java)

        }
    }
}