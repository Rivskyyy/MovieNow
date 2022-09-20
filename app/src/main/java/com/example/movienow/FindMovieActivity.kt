package com.example.movienow

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.size


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

    private lateinit var buttonNextMovie: Button
    private var page: Int? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_movie)
        initViewFindMovie()

        loadMovies()

        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )

        recyclerView.adapter = adapter


        buttonNextMovie.setOnClickListener {
            loadMovies()

        }
    }


    private fun initViewFindMovie() {
        buttonNextMovie = findViewById(R.id.buttonNextMovie)
        recyclerView = findViewById(R.id.recyclerView)
        adapter = MoviesAdapter()
    }


    fun loadMovies() {

        val vote_min = 500
        val vote_max = 1000000



        var page: Int = Random.nextInt(1, 40) // -> var - need for another future functional
        var language: String = Locale.getDefault().language.toString()
        if (language.equals("ru")) {
            language = "uk"
        } else {
            language
        }
        val region: String = Locale.getDefault().country.toString()

        Log.d("Language: ", language.toString())
        Log.d("Region:  ,", region.toString())

        val apiFactory = ApiFactory.apiService.loadMovies(page, language, region, vote_min, vote_max)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {
                Log.d("Test", it.toString())

                adapter.setMovies(it.results)
            }, {

                Log.d("Test_Fail", it.message.toString())
            })
    }


}