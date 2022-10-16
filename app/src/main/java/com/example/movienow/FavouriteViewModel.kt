package com.RivskyInc.movienow

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.RivskyInc.movienow.database.MovieDao
import com.RivskyInc.movienow.database.MovieDataBase
import com.RivskyInc.movienow.API_SERVICE.Result



class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    private val movieDao : MovieDao = MovieDataBase.getInstance(application).movieDao()

    fun getMovies() : LiveData<List<Result>>{
        return movieDao.getAllFavouriteMovie()
    }

}

