package com.aslan.okko.viewmodel


import android.app.Application
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslan.okko.model.FilmFavDataBase
import com.aslan.okko.model.fav.FavList

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritesViewModel : ViewModel() {
    val favouritesRoomLiveData = MutableLiveData<List<FavList>>()

    fun getFromRoom(application: Application): MutableLiveData<List<FavList>> {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataBase = FilmFavDataBase.getInstance(application)
                val filmDao = dataBase.favFilmDao()
                val favList = filmDao.getAllFav()
                favouritesRoomLiveData.postValue(favList)
            }
        }
        return favouritesRoomLiveData
    }

    fun deleteFromRoom(application: Application, filmId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataBase = FilmFavDataBase.getInstance(application)
                val filmDao = dataBase.favFilmDao()
                filmDao.deleteFav(filmId)
            }
        }
    }
}





