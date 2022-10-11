package com.example.movienow

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movienow.API_SERVICE.Result
import com.example.movienow.database.MovieDao
import com.example.movienow.database.MovieDataBase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.callbackFlow

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()

    private val movieDao: MovieDao = MovieDataBase.getInstance(application).movieDao()

    fun getFavouriteMovie(movieId: Int?): LiveData<Result> {

        return movieDao.getFavouriteById(movieId)
    }

    fun insertMovie(result: Result) {
        val disposable: Disposable = movieDao.insertMovie(result).subscribeOn(Schedulers.io()).subscribe()
        Log.d("insert", disposable.toString())
        compositeDisposable.add(disposable)
    }

    fun removeMovie(movieId: Int?) {
        val disposable: Disposable = movieDao
            .removeMovie(movieId!!)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}




