package com.example.movienow

import android.R.attr.button
import android.content.Intent
import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MotionEventCompat.getY


class MainActivity : AppCompatActivity() {

    private lateinit var buttonStart: ImageButton
    private lateinit var buttonGenreHorror: ImageButton
    private lateinit var buttonGenreAction: ImageButton
    private lateinit var buttonGenreComedy: ImageButton
    private lateinit var buttonGenreDrama: ImageButton
    private lateinit var buttonGenreFantasy: ImageButton
    private lateinit var buttonGenreAnimation: ImageButton
    private lateinit var buttonGenreThriller: ImageButton
    private lateinit var horizontalScrollView: HorizontalScrollView
    private lateinit var buttonFavourite: ImageButton
    private lateinit var buttonInfo : ImageButton

    var averageMin = 5
    var averageMax = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        horizontalScrollView.post({
            horizontalScrollView.scrollTo(
                buttonStart.getX().toInt(),
                buttonStart.getY().toInt()
            )
        })

        buttonFavourite.setOnClickListener {
            var intentNew: Intent = FavouriteMoviesActivity.newIntent(this)
            startActivity(intentNew)
        }

        buttonInfo.setOnClickListener {
            val intentNewOne  = Intent(this, InfoActivity::class.java)
            startActivity(intentNewOne)
        }

        buttonStart.setOnClickListener {

            val setupPageForAllGenre = 1
            val genreALl = "null"

            val intent = Intent(this, FindMovieActivity::class.java)

            intent.putExtra("setupPageForAllGenre", setupPageForAllGenre)
            intent.putExtra("genreAll", genreALl)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)

        }

        buttonGenreHorror.setOnClickListener {

            val averageMinHorror = 5
            val averageMaxHorror = 10
            val genreHorror = "27"                      // -> need String format for getStringExtra
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreHorror)
            intent.putExtra("averageMin", averageMinHorror)
            intent.putExtra("averageMax", averageMaxHorror)

            startActivity(intent)
        }
        buttonGenreAction.setOnClickListener {
            val averageMin = 5
            val averageMax = 10
            val genreAction = "28"
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreAction)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }

        buttonGenreComedy.setOnClickListener {
            val averageMin = 5
            val averageMax = 10
            val genreComedy = "35"
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreComedy)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }

        buttonGenreDrama.setOnClickListener {
            val averageMin = 5
            val averageMax = 10
            val genreDrama = "18"
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreDrama)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }

        buttonGenreFantasy.setOnClickListener {
            val averageMin = 5
            val averageMax = 10
            val genreFantasy = "14"
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreFantasy)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }
        buttonGenreThriller.setOnClickListener {
            val averageMin = 5
            val averageMax = 10
            val genreThriller = "53"
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreThriller)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }
        buttonGenreAnimation.setOnClickListener {
            val averageMin = 5
            val averageMax = 10
            val genreAnimation = "16"
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreAnimation)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }

    }

    private fun initView() {
        buttonStart = findViewById(R.id.button_start)
        buttonGenreHorror = findViewById(R.id.button_catalog)
        buttonGenreAction = findViewById(R.id.button_action)
        buttonGenreComedy = findViewById(R.id.button_comedy)
        buttonGenreDrama = findViewById(R.id.button_drama)
        buttonGenreFantasy = findViewById(R.id.button_fantasy)
        buttonGenreThriller = findViewById(R.id.button_thriller)
        buttonGenreAnimation = findViewById(R.id.button_animation)
        horizontalScrollView = findViewById(R.id.horizontalScrollView)
        buttonFavourite = findViewById(R.id.button_favourite)
        buttonInfo = findViewById(R.id.button_info)

    }
}

