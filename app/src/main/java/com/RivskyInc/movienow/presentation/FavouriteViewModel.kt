package com.RivskyInc.movienow.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.RivskyInc.movienow.data.database.MovieDao
import com.RivskyInc.movienow.data.database.MovieDataBase
import com.RivskyInc.movienow.API_SERVICE.Result



class FavouriteViewModel(application: Application) : AndroidViewModel(application) {

    private val movieDao : MovieDao = MovieDataBase.getInstance(application).movieDao()

    fun getMovies() : LiveData<List<Result>>{
        return movieDao.getAllFavouriteMovie()
    }

}

