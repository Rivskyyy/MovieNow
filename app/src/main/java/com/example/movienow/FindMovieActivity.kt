package com.RivskyInc.movienow


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.RivskyInc.movienow.R
import com.RivskyInc.movienow.API_SERVICE.ApiFactory
import com.RivskyInc.movienow.API_SERVICE.Result
import com.RivskyInc.movienow.adapter.MoviesAdapter
import com.RivskyInc.movienow.database.MovieDao
import com.RivskyInc.movienow.database.MovieDataBase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.droidsonroids.gif.GifImageView
import java.util.*
import kotlin.random.Random

class FindMovieActivity : AppCompatActivity() {

    private lateinit var buttonNextMovie: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var dataBase: MovieDataBase
    private lateinit var movieDao: MovieDao
    private lateinit var loadGif : GifImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_find_movie)

        initViewFindMovie()

        recyclerView.postDelayed(Runnable { recyclerView.setVisibility(View.VISIBLE) }, 750)
        val handler = Handler()
        loadGif.postDelayed(Runnable { loadGif.setVisibility(View.GONE) }, 800)
       // handler.postDelayed(Runnable { loadGif.setVisibility(View.VISIBLE) }, 5000)

        loadMovies()
        recyclerView.layoutManager = LinearLayoutManager(           // -> for future catalog

            this,
            LinearLayoutManager.HORIZONTAL, false
        )

        recyclerView.adapter = adapter

        adapter.onMovieClickListener_object = object : MoviesAdapter.onMovieClickListener {
            override fun onMovieClick(movie: Result) {
                val  intent = MovieDetailActivity.NewIntent(this@FindMovieActivity, movie).also {

                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)

                   // intent.putExtra("movie.title", movie.title)

                }
//                    intent.putExtra(EXTRA_MOVIE,movie.release_date)
//                    intent.putExtra(EXTRA_MOVIE, movie.id)
//                    intent.putExtra(EXTRA_MOVIE, movie.overview)
//                    intent.putExtra(EXTRA_MOVIE, movie.title)
//                    intent.putExtra(EXTRA_MOVIE, movie.poster_path)
//                    intent.putExtra(EXTRA_MOVIE, movie.release_date)
//                    intent.putExtra(EXTRA_MOVIE, movie.vote_average)
                startActivity(intent)
            }
        }
        buttonNextMovie.setOnClickListener {

            loadGif.setVisibility(View.VISIBLE)
            loadGif.postDelayed(Runnable { loadGif.setVisibility(View.GONE) }, 800)
            recyclerView.setVisibility(View.GONE)
            recyclerView.postDelayed(Runnable { recyclerView.setVisibility(View.VISIBLE) }, 800)

            loadMovies()


        }

    }


    private fun initViewFindMovie() {
        buttonNextMovie = findViewById(R.id.buttonNextMovie)
        recyclerView = findViewById(R.id.recyclerView)
        loadGif = findViewById<GifImageView>(R.id.gifLoadMovies)
        adapter = MoviesAdapter()

    }


    fun loadMovies() {

        val showResults = adapter.itemCount.toString()

        Log.d("TEST ITEMS", showResults.toString())

        val vote_min = 500
        val vote_max = 1000000

        var setupGenre: Int? = intent.getIntExtra(GENRE, 0)
        Log.d("REAL INTENT  GENRE ", setupGenre.toString())

        var  setupAverageMin = intent.getIntExtra("averageMin", 5 )
        var setupAverageMax = intent.getIntExtra("averageMax", 10)
       // val setupPageForAllGenre = intent.getStringExtra("setupPageForAllGenre")?.toInt()
        // val setupPageForAllGenres = intent.getStringExtra("genre")?.toInt() // use when genre is null

        val voteAverageMin = setupAverageMin
        val voteAverageMax = setupAverageMax
        var genre: Int? = setupGenre
        Log.d("GENRE ", setupGenre.toString())
        Log.d("real genre ", genre.toString())
        var region: String = Locale.getDefault().country.toString()
        var page = 0
        if (region.equals("UA")){                           // Algorithm for
                                                            //  Ukrainian localization
              page  =
                if (genre?.equals(27) == true) {
                    Random.nextInt(1, 10)       // horror
                } else if (genre?.equals(28) == true) {
                    Random.nextInt(1, 21)         // action
                } else if (genre?.equals(35) == true) {
                    Random.nextInt(1, 19)        //comedy
                } else if (genre?.equals(18) == true) {
                    Random.nextInt(1, 25)        //drama
                } else if (genre?.equals(14) == true) {
                    Random.nextInt(1, 9)        //fantasy
                } else if (genre?.equals(53) == true) {
                    Random.nextInt(1, 20)        //thriller
                } else if (genre?.equals(16) == true) {
                    Random.nextInt(1, 7)        //animation
                    // all
                } else {
                    genre = null
                    Random.nextInt(1, 63)
                }
        } else{
            page  =
                try {


                    if (genre?.equals(27) == true) {
                        Random.nextInt(1, 41)       // horror
                    } else if (genre?.equals(28) == true) {
                        Random.nextInt(1, 40)         // action
                    } else if (genre?.equals(35) == true) {
                        Random.nextInt(1, 40)        //comedy
                    } else if (genre?.equals(18) == true) {
                        Random.nextInt(1, 40)        //drama
                    } else if (genre?.equals(14) == true) {
                        Random.nextInt(1, 37)        //fantasy
                    } else if (genre?.equals(53) == true) {
                        Random.nextInt(1, 40)        //thriller
                    } else if (genre?.equals(16) == true) {
                        Random.nextInt(1, 27)        //animation
                        // all
                    } else {
                        genre = null
                        Random.nextInt(1, 100)
                    }


                } catch (e: Exception) {
                    Log.d("EXCEPTION : ", e.message.toString())
                }
        }



//        if (genre == null) {
//            page = Random.nextInt(1, 100)       //all
//        }


        var language: String = Locale.getDefault().language.toString()
        if (language.equals("ru")) {
            language = "uk"
        } else {
            language
        }

        Log.d("Region: ", region.toString())
        Log.d("Language: ", language.toString())
        // Log.d("Region:  ,", region.toString())
        // need more total results, something get wrong and results get less than must be
        // fixed, the region's fault

        //need to fix getStringExtra -> getIntExtra


        val apiFactory = ApiFactory.apiService.loadMovies(
            page,
            language,
             region,
            vote_min,
            vote_max,
            genre,
            voteAverageMin,
            voteAverageMax
        )

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer {

                Log.d("Test", it.toString())

                val dataMovies = adapter.setMovies(it.results)


            }, {
                loadMovies()
                Log.d("Test_Fail", it.message.toString())
            })

    }


}
