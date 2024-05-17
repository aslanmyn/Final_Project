package com.aslan.okko.servis

import com.aslan.okko.api.Constants
import com.aslan.okko.model.detail.MovieDetailResult
import com.aslan.okko.model.Popular.Popular
import com.aslan.okko.model.trend.Trend
import com.aslan.okko.model.tvAiringToday.TvAiringToday
import com.aslan.okko.model.tvDetail.TvDetail
import com.aslan.okko.model.tvPopular.TvPopular
import com.aslan.okko.model.tvTopRated.TvTopRated
import com.aslan.okko.model.upComing.UpComing
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmAPIServis{

    private val BASE_URL = "https://api.themoviedb.org/3/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().addInterceptor { chain ->
            val request =
                chain.request().newBuilder().addHeader("Authorization", "Bearer ${Constants.accessToken}").build()
            chain.proceed(request)
        }.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FilmAPI::class.java)

    fun getDataPopular(): Call<Popular> {
        return  api.getPopular()
    }

    fun getDataTrend(takenMedia:String,takenWindow:String):Call<Trend>{
        return api.getTrend(takenMedia,takenWindow)
    }

    fun getDataUpComing():Call<UpComing>{
        return api.getUpcomig()
    }

    fun getDataDetail(takenDetail:String):Call<MovieDetailResult>{
        return api.getDetail(takenDetail)
    }

    fun getDataTvPopular(takenPage:Int):Call<TvPopular>{
        return api.getTvPopular(takenPage)
    }

    fun getDataTvTopRated():Call<TvTopRated>{
        return api.getTvTopRated()
    }
    fun getDataTvAiringToday(takenPage: Int):Call<TvAiringToday>{
        return api.getTvAiringToday(takenPage)
    }
    fun getDataTvShowDetail(tvTakenDetail:String):Call<TvDetail>{
        return api.getTvDetail(tvTakenDetail)
    }

}