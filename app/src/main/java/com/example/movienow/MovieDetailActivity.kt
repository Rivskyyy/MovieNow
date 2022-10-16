package com.RivskyInc.movienow

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.RivskyInc.movienow.API_SERVICE.BASE_IMAGE_POSTER
import com.RivskyInc.movienow.API_SERVICE.Result
import com.RivskyInc.movienow.adapter.MoviesAdapter
import com.bumptech.glide.Glide

const val EXTRA_MOVIE: String = "movie"

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var posterImageView: ImageView
    private lateinit var nameOfMovie: TextView
    private lateinit var yearOfMovie: TextView
    private lateinit var descriptionOfMovie: TextView
    private lateinit var rating: TextView
    private lateinit var adapter: MoviesAdapter

    // private lateinit var movieDao : MovieDao
    private lateinit var imageFavorite: ImageButton
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        initView()

        var movie: Result? = null
        var movieId: Int? = intent.getIntExtra("movie.id", 0)
        var movieTitle = intent.getStringExtra("movie.title")
        var movieDescription = intent.getStringExtra("movie.overview")
        var movieRating = intent.getDoubleExtra("movie.rating", 0.0)
        var moviePoster = intent.getStringExtra("movie.poster")
        var movieYear = intent.getStringExtra("movie.year")



        Log.d("MOVIE_ID", movieId.toString())
        // var movieInfo  = movieId; movieTitle; movieDescription; movieRating; movieRating; moviePoster; movieYear as? Result
        // Log.d("MOVIE_INFO", movieInfo.toString())
        //  Log.d("MOVIE_TITLE",movie?.title.toString())
        //  Log.d("MOVIE_DESCRIPTION", movieDescription?.overview.toString())
        // Log.d("MOVIE_RATING", movieRating?.vote_average.toString())

        Glide.with(this).load(BASE_IMAGE_POSTER + moviePoster).into(posterImageView)
        nameOfMovie.text = movieTitle
        yearOfMovie.text = movieYear.toString()
        descriptionOfMovie.text = movieDescription
        rating.text = movieRating.toString()



        viewModel = MainViewModel(application)
        viewModel.getFavouriteMovie(movieId)?.observe(this, Observer {

            if (it?.id == null) {
                imageFavorite.setImageResource(R.drawable.heart_icon_empty_resize)

                imageFavorite.setOnClickListener {
                    if (movieId != null) {
                        viewModel.insertMovie(
                            Result(
                                movieId,
                                movieDescription,
                                moviePoster,
                                movieYear,
                                movieTitle,
                                movieRating
                            )
                        )
                    }
                    Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()

                }
            } else {
                imageFavorite.setImageResource(R.drawable.heart_button_red)
                imageFavorite.setOnClickListener {
                    viewModel.removeMovie(movieId)
                    Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()
                }
            }
        })


    }


    companion object {
        fun NewIntent(context: Context, movie: Result): Intent {

            var intent: Intent = Intent(context, MovieDetailActivity::class.java)
            //   intent.putExtra("movie", movie)
            intent.putExtra("movie.id", movie.id)
            intent.putExtra("movie.overview", movie.overview)
            intent.putExtra("movie.title", movie.title)
            intent.putExtra("movie.poster", movie.poster_path)
            intent.putExtra("movie.year", movie.release_date)
            intent.putExtra("movie.rating", movie.vote_average)
            return intent
        }
    }


    private fun initView() {
        posterImageView = findViewById(R.id.posterImageDetail)
        nameOfMovie = findViewById(R.id.nameOfMovieDetail)
        yearOfMovie = findViewById(R.id.yearOfMovieDetail)
        descriptionOfMovie = findViewById(R.id.descriptionOfMovieDetail)
        rating = findViewById(R.id.ratingDetail)
        imageFavorite = findViewById(R.id.imageButtonFavor)

    }


}