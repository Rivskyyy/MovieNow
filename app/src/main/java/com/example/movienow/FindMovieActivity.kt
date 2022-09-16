package com.example.movienow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.HorizontalScrollView


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movienow.API_SERVICE.API
import com.example.movienow.API_SERVICE.API_Data

import com.example.movienow.API_SERVICE.ApiFactory
import com.example.movienow.API_SERVICE.Result
import com.example.movienow.adapter.MoviesAdapter

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.random.Random


class FindMovieActivity : AppCompatActivity() {

    private lateinit var buttonNextMovie : Button
    private var page : Int? = null
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : MoviesAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_movie)
        initViewFindMovie()

        loadMovies()

        recyclerView.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)

        recyclerView.adapter = adapter



        buttonNextMovie.setOnClickListener {
            loadMovies()
        }
    }

    private fun initViewFindMovie() {
        buttonNextMovie = findViewById(R.id.buttonNextMovie)
        recyclerView = findViewById(R.id.recyclerView)
        adapter = MoviesAdapter() // Необхідна ініціалізація задля прикріплення адаптера
    }

    fun loadMovies(){

        val apiFactory = ApiFactory.apiService.loadMovies(Random.nextInt(1,199) )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                Log.d("Test", it.toString())

                 adapter.setMovies(it.results)
            },{
                Log.d("Test_Fail", it.message.toString())
            })
    }


}