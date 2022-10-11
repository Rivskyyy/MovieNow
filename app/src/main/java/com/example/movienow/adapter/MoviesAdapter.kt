package com.example.movienow.adapter

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movienow.API_SERVICE.BASE_IMAGE_POSTER
import com.example.movienow.API_SERVICE.Result
import com.example.movienow.FindMovieActivity
import com.example.movienow.MainViewModel
import com.example.movienow.R
import com.example.movienow.database.MovieDao
import com.example.movienow.database.MovieDao_Impl
import com.example.movienow.database.MovieDataBase
import com.example.movienow.database.MovieDataBase_Impl
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

public class MoviesAdapter() : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {


    private var movies: List<Result> = ArrayList<Result>()


    var onMovieClickListener_object: onMovieClickListener? = null


    private lateinit var imageView: ImageView


    fun setMovies(movies: List<Result>) {
        this.movies = movies
        notifyDataSetChanged()
        Collections.shuffle(movies)
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LifecycleOwner {

        private lateinit var posterImageView: ImageView
        private lateinit var nameOfMovie: TextView
        //        private lateinit var yearOfMovie: TextView
//        private lateinit var descriptionOfMovie: TextView
        private lateinit var rating: TextView
        private lateinit var viewModel: MainViewModel

        fun bindMovie(movie: Result) {

            posterImageView = itemView.findViewById(R.id.posterImage)
            nameOfMovie = itemView.findViewById(R.id.nameOfMovie)
//            yearOfMovie = itemView.findViewById(R.id.yearOfMovie)
//            descriptionOfMovie = itemView.findViewById(R.id.descriptionOfMovie)
            rating = itemView.findViewById(R.id.rating)
//            imageFavorite = itemView.findViewById(R.id.imageButtonFavor)

            val getId = movie.id


            rating.text = movie.vote_average.toString()
//            descriptionOfMovie.text = movie.overview
//            yearOfMovie.text = movie.release_date
            nameOfMovie.text = movie.title
            var id = movie.id
            Glide.with(itemView).load(BASE_IMAGE_POSTER + movie.poster_path).into(posterImageView)

            fun getDataBaseMovie() {
                val itemMovieData = com.example.movienow.API_SERVICE.Result(
                    movie.id, movie.overview,
                    movie.poster_path, movie.release_date,
                    movie.title, movie.vote_average
                )
                Log.d("DATA", getDataBaseMovie().toString())

                fun getContext(context: Context) {
                    var movieDao: MovieDao
                    movieDao = MovieDataBase.getInstance(context).movieDao()
                    movieDao.getAllFavouriteMovie().observe(this, androidx.lifecycle.Observer {
                        movieDao.insertMovie(itemMovieData).subscribeOn(Schedulers.io()).subscribe()

                    })
                }
            }

        }

        override fun getLifecycle(): Lifecycle {

            return lifecycle
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_items, parent, false)
        return MyViewHolder(view)

    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindMovie(movies[position])
        val movie: Result = movies.get(position)

        holder.itemView.setOnClickListener {

            onMovieClickListener_object?.onMovieClick(movie)


        }


    }

    override fun getItemCount(): Int {
        return movies.size - 19
        // Log.d("getItemCount", (movies.size).toString())
    }


    interface onMovieClickListener {
        fun onMovieClick(movie: Result)
    }

}
