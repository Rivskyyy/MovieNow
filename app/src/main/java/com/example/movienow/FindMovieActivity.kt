package com.example.movienow


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movienow.API_SERVICE.ApiFactory
import com.example.movienow.API_SERVICE.Result
import com.example.movienow.adapter.MoviesAdapter
import com.example.movienow.database.MovieDao
import com.example.movienow.database.MovieDataBase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.random.Random


class FindMovieActivity : AppCompatActivity() {

    private lateinit var buttonNextMovie: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var dataBase: MovieDataBase
    private lateinit var movieDao: MovieDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_movie)



        initViewFindMovie()

        loadMovies()
        recyclerView.layoutManager = LinearLayoutManager(           // -> for future catalog

            this,
            LinearLayoutManager.HORIZONTAL, false
        )

        recyclerView.adapter = adapter

        adapter.onMovieClickListener_object = object : MoviesAdapter.onMovieClickListener {
            override fun onMovieClick(movie: Result) {
                intent = MovieDetailActivity.NewIntent(this@FindMovieActivity, movie).also {
                    intent.putExtra("movie.title", movie.title)
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

            loadMovies()


        }

    }


    private fun initViewFindMovie() {
        buttonNextMovie = findViewById(R.id.buttonNextMovie)
        recyclerView = findViewById(R.id.recyclerView)
        adapter = MoviesAdapter()

    }


    fun loadMovies() {
//        fun uploadOptimization(result: Result){
//            if ( result.overview.length==null){
//                loadMovies()
//            } else {
//                0
//            }
//        }
//        var starOff: = (
//            this,
//            R.drawable.heart_icon_empty_resize)
//
//        var starOn: Drawable? = ContextCompat.getDrawable(
//            this,
//            R.drawable.heart_button_red)

        val showResults = adapter.itemCount.toString()

        Log.d("TEST ITEMS", showResults.toString())

        val vote_min = 500
        val vote_max = 1000000

        val setupGenre: Int? = intent.getStringExtra("genre")?.toInt()
        val setupAverageMin = intent.getStringExtra("averageMin")?.toInt()
        val setupAverageMax = intent.getStringExtra("averageMax")?.toInt()
        val setupPageForAllGenre = intent.getStringExtra("setupPageForAllGenre")?.toInt()
        // val setupPageForAllGenres = intent.getStringExtra("genre")?.toInt() // use when genre is null

        val voteAverageMin = setupAverageMin
        val voteAverageMax = setupAverageMax
        val genre: Int? = setupGenre

        var page =
            try {

                let {
                    if (genre!!.equals(27)) {
                        Random.nextInt(1, 41)       // horror
                    } else if (genre.equals(28)) {
                        Random.nextInt(1, 40)         // action
                    } else if (genre.equals(35)) {
                        Random.nextInt(1, 40)        //comedy
                    } else if (genre.equals(18)) {
                        Random.nextInt(1, 40)        //drama
                    } else if (genre.equals(14)) {
                        Random.nextInt(1, 37)        //fantasy
                    } else if (genre.equals(53)) {
                        Random.nextInt(1, 40)        //thriller
                    } else if (genre.equals(16)) {
                        Random.nextInt(1, 27)        //animation
                        // all
                    } else {
                        0
                    }

                }
            } catch (e: Exception) {
                Log.d("EXCEPTION : ", e.message.toString())
            }

        if (genre == null) {
            page = Random.nextInt(1, 100)       //all
        }


        var language: String = Locale.getDefault().language.toString()
        if (language.equals("ru")) {
            language = "uk"
        } else {
            language
        }
        //val region: String = Locale.getDefault().country.toString()

        Log.d("Language: ", language.toString())
        // Log.d("Region:  ,", region.toString())
        // need more total results, something get wrong and results get less than must be
        // fixed, the region's fault

        //need to fix getStringExtra -> getIntExtra


        val apiFactory = ApiFactory.apiService.loadMovies(
            page,
            language,
            // region,
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

                Log.d("Test_Fail", it.message.toString())
            })

    }


}
