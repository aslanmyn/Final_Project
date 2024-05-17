package com.aslan.okko.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aslan.okko.model.tvAiringToday.TvAiringToday
import com.aslan.okko.model.tvPopular.TvPopular
import com.aslan.okko.model.tvTopRated.TvTopRated
import com.aslan.okko.servis.FilmAPIServis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvShowsViewModel:ViewModel() {
     private  var service=FilmAPIServis()
     var tvResultData=MutableLiveData<TvPopular>()
     var tvTopratedData=MutableLiveData<TvTopRated>()
    var tvAiringTodayData=MutableLiveData<TvAiringToday>()
    val load=MutableLiveData<Boolean>()


    fun getTvPopular(gettingPage:Int){

        load.value=true
        val response=service.getDataTvPopular(gettingPage)

        response.enqueue(object :Callback<TvPopular>{
            override fun onResponse(call: Call<TvPopular>, response: Response<TvPopular>) {

                if (response.isSuccessful){
                    tvResultData.value=response.body()
                    load.value=false
                }

            }

            override fun onFailure(call: Call<TvPopular>, t: Throwable) {
                Log.i("getTvPopular service", "onFailure: $t")

            }

        })

    }

    fun getTvToprated(){
        load.value=true
        val response=service.getDataTvTopRated()
        response.enqueue(object :Callback<TvTopRated>{
            override fun onResponse(call: Call<TvTopRated>, response: Response<TvTopRated>) {

                if (response.isSuccessful){
                    tvTopratedData.value=response.body()
                    load.value=false
                }
            }

            override fun onFailure(call: Call<TvTopRated>, t: Throwable) {
                Log.i("getTvToprated service", "onFailure: $t")
            }

        })
    }

    fun getTvAiringToday(gettingPage: Int){
        load.value=true
        val response=service.getDataTvAiringToday(gettingPage)
        response.enqueue(object :Callback<TvAiringToday>{
            override fun onResponse(call: Call<TvAiringToday>, response: Response<TvAiringToday>) {
                if (response.isSuccessful){
                    tvAiringTodayData.value=response.body()
                    load.value=false
                }

            }

            override fun onFailure(call: Call<TvAiringToday>, t: Throwable) {
                Log.i("getTvAiringToday service", "onFailure: $t")
            }

        })



    }


}