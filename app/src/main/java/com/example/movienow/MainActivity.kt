package com.example.movienow

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.movienow.API_SERVICE.API
import com.example.movienow.API_SERVICE.API_Data
import com.example.movienow.API_SERVICE.ApiFactory
import com.example.movienow.API_SERVICE.ApiFactory.Companion.toString
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.invoke.MethodHandleInfo.toString
import java.util.Objects.toString

class MainActivity : AppCompatActivity() {

    private lateinit var buttonStart: ImageButton
    private lateinit var buttonGenreHorror: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        buttonStart.setOnClickListener {

            intent = Intent(this, FindMovieActivity::class.java)
            startActivity(intent)
        }

        buttonGenreHorror.setOnClickListener {
            //add new intent for genre
        }
    }

    //need to add new buttons for all genres


    private fun initView() {
        buttonStart = findViewById(R.id.button_start)
        buttonGenreHorror = findViewById(R.id.button_catalog)
    }
}

