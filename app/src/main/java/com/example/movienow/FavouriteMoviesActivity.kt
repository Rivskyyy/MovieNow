package com.example.movienow

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movienow.API_SERVICE.Result
import com.example.movienow.adapter.FavouriteMoviesAdapter

class FavouriteMoviesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite_movies)

        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewMovies)
        var moviesAdapter: FavouriteMoviesAdapter = FavouriteMoviesAdapter()

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = moviesAdapter

        var viewModel: FavouriteViewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]



            viewModel.getMovies().observe(this, Observer {
               var showMovies =  moviesAdapter.setMovies(it)
                Log.d("show_favourite", showMovies.toString())
            })


    }
    companion object{
        fun newIntent (context: Context) : Intent {
            return Intent(context, FavouriteMoviesActivity::class.java)
        }
    }
}