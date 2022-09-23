package com.example.movienow

import android.content.ClipData.newIntent
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
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var buttonStart: ImageButton
    private lateinit var buttonGenreHorror: ImageButton
    private val genreAction = "28" // -> need String format for getStringExtra
    private val genreHorror = "27"
    var averageMin = 5
    var averageMax = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()


        buttonStart.setOnClickListener {

            if ( buttonStart.isClickable) {
                intent = Intent(this, FindMovieActivity::class.java)
                intent.putExtra("averageMin", averageMin)
                intent.putExtra("averageMax", averageMax)

                startActivity(intent)
            }
        }

        buttonGenreHorror.setOnClickListener {
        if ( buttonGenreHorror.isClickable) {
            var averageMinHorror = 3
            var averageMaxHorror = 10

            intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreHorror)
            intent.putExtra("averageMin", averageMinHorror)
            intent.putExtra("averageMax", averageMaxHorror)

            startActivity(intent)
        }
        }

    }

    private fun initView() {
        buttonStart = findViewById(R.id.button_start)
        buttonGenreHorror = findViewById(R.id.button_catalog)
    }
}

