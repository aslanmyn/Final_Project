package com.aslan.okko.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aslan.okko.model.FilmFavDataBase
import com.aslan.okko.model.fav.FavList
import com.aslan.okko.model.tvDetail.TvDetail
import com.aslan.okko.servis.FilmAPIServis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowsDetailViewModel : ViewModel() {
    private val service = FilmAPIServis()
    var getTvDetailData =MutableLiveData<TvDetail>()
    val load=MutableLiveData<Boolean>()

    fun getDataTvShowDetail(gettingId: String) {
        load.value=true
        val response=service.getDataTvShowDetail(gettingId)
        response.enqueue(object :Callback<TvDetail>{
            override fun onResponse(call: Call<TvDetail>, response: Response<TvDetail>) {
                if (response.isSuccessful){
                    getTvDetailData.value=response.body()
                    Log.i("TV Detail Data", "${response.body()}")
                    load.value=false
                }
                else{
                    Log.i("FailDataTv", "API Error: ${response.code()}")
                }

            }

            override fun onFailure(call: Call<TvDetail>, t: Throwable) {
                Log.i("getTvDetailData service", "onFailure: $t")
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