package com.RivskyInc.movienow.presentation

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.RivskyInc.movienow.R
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


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
    private lateinit var viewModel : MainViewModel
    private lateinit var mainLayout : ConstraintLayout
    private lateinit var yourBitmap: Bitmap
    private lateinit var swipeTip : LottieAnimationView
    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "MainActivity"


    private val buttonClick = AlphaAnimation(1f, 0.6f)


    var averageMin = 5
    var averageMax = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()


        MobileAds.initialize(this@MainActivity)

        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-7124048404999597/3355495921", adRequest, object : InterstitialAdLoadCallback() {
             override fun onAdFailedToLoad(adError: LoadAdError) {
                adError?.toString()?.let { Log.d(TAG, it) }
                mInterstitialAd = null
            }

             override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })
        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }



        val animButton = AnimationUtils.loadAnimation(this, com.airbnb.lottie.R.anim.abc_grow_fade_in_from_bottom)
        horizontalScrollView.post({
            horizontalScrollView.scrollTo(
                buttonStart.getX().toInt(),
                buttonStart.getY().toInt()
            )
        })

        swipeTip.postDelayed(Runnable {
            swipeTip.visibility = View.GONE
        }, 3000 )
        buttonFavourite.setOnClickListener {
            mInterstitialAd?.show(this)
            onClick(buttonFavourite)
            var intentNew: Intent = FavouriteMoviesActivity.newIntent(this)
            startActivity(intentNew)
        }

        buttonInfo.setOnClickListener {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
            onClick(buttonInfo)
            val intentNewOne  = Intent(this, InfoActivity::class.java)
            startActivity(intentNewOne)
        }

        buttonStart.setOnClickListener {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
           // onClick(buttonStart)
            buttonStart.startAnimation(animButton)

            val setupPageForAllGenre = 1
            val genreALl : Int? = null

            val intent = Intent(this, FindMovieActivity::class.java)

            intent.putExtra("setupPageForAllGenre", setupPageForAllGenre)
            intent.putExtra("genreAll", genreALl)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)

        }

        buttonGenreHorror.setOnClickListener {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
            //onClick(buttonGenreHorror)
            buttonGenreHorror.startAnimation(animButton)
            val averageMinHorror = 5
            val averageMaxHorror = 10
            val genreHorror : Int?  = 27                   // -> need String format for getStringExtra
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreHorror)
            intent.putExtra("averageMin", averageMinHorror)
            intent.putExtra("averageMax", averageMaxHorror)

            startActivity(intent)
        }
        buttonGenreAction.setOnClickListener {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
           // onClick(buttonGenreAction)
            buttonGenreAction.startAnimation(animButton)
            val averageMin = 5
            val averageMax = 10
            val genreAction = 28
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreAction)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }

        buttonGenreComedy.setOnClickListener {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
            //onClick(buttonGenreComedy)
            buttonGenreComedy.startAnimation(animButton)
            val averageMin = 5
            val averageMax = 10
            val genreComedy = 35
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreComedy)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }

        buttonGenreDrama.setOnClickListener {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
           // onClick(buttonGenreDrama)
            buttonGenreDrama.startAnimation(animButton)
            val averageMin = 5
            val averageMax = 10
            val genreDrama = 18
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreDrama)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }

        buttonGenreFantasy.setOnClickListener {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
           // onClick(buttonGenreFantasy)
            buttonGenreFantasy.startAnimation(animButton)
            val averageMin = 5
            val averageMax = 10
            val genreFantasy = 14
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreFantasy)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }
        buttonGenreThriller.setOnClickListener {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
           // onClick(buttonGenreThriller)
            buttonGenreThriller.startAnimation(animButton)
            val averageMin = 5
            val averageMax = 10
            val genreThriller = 53
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra("genre", genreThriller)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }

        buttonGenreAnimation.setOnClickListener {
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
            buttonGenreAnimation.startAnimation(animButton)
           // onClick(buttonGenreAnimation)

            val averageMin = 5
            val averageMax = 10
            val genreAnimation = 16
            val intent = Intent(this, FindMovieActivity::class.java)
            intent.putExtra(GENRE, genreAnimation)
            intent.putExtra("averageMin", averageMin)
            intent.putExtra("averageMax", averageMax)

            startActivity(intent)
        }

    }

    private fun startAnimationHole() {
       val main_layout = R.layout.activity_main
        val mainBack : ImageView = findViewById(R.id.mainImageBackground)


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
        mainLayout = findViewById(R.id.mainLayout)
        swipeTip = findViewById(R.id.swipe_tip)

    }

    override fun onBackPressed() {

        super.onBackPressed()
        this.finishAffinity()
    }

    private fun onClick(v: View) {
        v.startAnimation(buttonClick)
    }

}

