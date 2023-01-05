package com.RivskyInc.movienow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.RivskyInc.movienow.API_SERVICE.Result
import com.RivskyInc.movienow.adapter.FavouriteMoviesAdapter
import com.example.movienow.MovieDetailActivity

class FavouriteMoviesActivity : AppCompatActivity() {
    private lateinit var textViewEmpty : TextView
    private lateinit var backButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_movies)

        textViewEmpty = findViewById(R.id.textViewEmpty)
        backButton = findViewById(R.id.buttonBackFromFavourite)
        backButton.setOnClickListener{  finish()}


        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewMovies)
        var moviesAdapter: FavouriteMoviesAdapter = FavouriteMoviesAdapter()



        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = moviesAdapter
//        if (moviesAdapter.setMovies() ){
//            textViewEmpty.setVisibility(View.VISIBLE)
//        }

        var viewModel: FavouriteViewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]
        moviesAdapter.onMovieClickListener_object = object : FavouriteMoviesAdapter.onMovieClickListener {
            override fun onMovieClick(movie: Result) {
           val  intent = MovieDetailActivity.NewIntent(this@FavouriteMoviesActivity, movie)
                startActivity(intent )
            }


        }


            viewModel.getMovies().observe(this, Observer {
               var showMovies =  moviesAdapter.setMovies(it)
                if (it.isEmpty()){
                    textViewEmpty.setVisibility(View.VISIBLE)
                }
                Log.d("show_favourite", showMovies.toString())
            })


    }
    companion object{
        fun newIntent (context: Context) : Intent {
            return Intent(context, FavouriteMoviesActivity::class.java)
        }
    }
}