package com.aslan.okko.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aslan.okko.model.FilmFavDataBase
import com.aslan.okko.model.detail.MovieDetailResult
import com.aslan.okko.model.fav.FavList
import com.aslan.okko.servis.FilmAPIServis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailViewModel(application: Application) : AndroidViewModel(application) {

    val detailMoviesData = MutableLiveData<MovieDetailResult>()
    val load=MutableLiveData<Boolean>()
    private val service = FilmAPIServis()




    fun getDataDetail(gettingDetail: String) {

        load.value=true
        val response = service.getDataDetail(gettingDetail)

        response.enqueue(object : Callback<MovieDetailResult> {
            override fun onResponse(
                call: Call<MovieDetailResult>,
                response: Response<MovieDetailResult>
            ) {
                if (response.isSuccessful) {
                    detailMoviesData.value = response.body()
                    load.value=false
                }

            }

            override fun onFailure(call: Call<MovieDetailResult>, t: Throwable) {
                Log.i("getDataDetail service", "onFailure: $t")
            }

        })

    }



    fun saveRoom(favList: FavList, application: Application) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataBase = FilmFavDataBase.getInstance(application)
                val filmDao = dataBase.favFilmDao()

                val existingFilm = filmDao.getFilmByName(favList.id)
                if (existingFilm == null) {
                    filmDao.insertAll(favList)
                }
            }
        }
    }


}
