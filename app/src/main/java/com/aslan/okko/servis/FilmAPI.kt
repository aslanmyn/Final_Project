package com.aslan.okko.servis

import com.aslan.okko.model.detail.MovieDetailResult
import com.aslan.okko.model.Popular.Popular
import com.aslan.okko.model.trend.Trend
import com.aslan.okko.model.tvAiringToday.TvAiringToday
import com.aslan.okko.model.tvDetail.TvDetail
import com.aslan.okko.model.tvPopular.TvPopular
import com.aslan.okko.model.tvTopRated.TvTopRated
import com.aslan.okko.model.upComing.UpComing
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmAPI {


    //------------MOVIE-------------------

    @GET("movie/popular")
    fun getPopular():Call<Popular>

    @GET("trending/{media_type}/{time_window}")
    fun getTrend(
        @Path("media_type")media_type:String,
        @Path("time_window")time_window:String,):Call<Trend>

    @GET("movie/upcoming")
    fun getUpcomig():Call<UpComing>

    @GET("movie/{movie_id}")
    fun getDetail(
        @Path("movie_id")movie_id:String):Call<MovieDetailResult>

    //-----------TV--------------------

    @GET("tv/popular")
    fun getTvPopular(
        @Query("page")page:Int):Call<TvPopular>

    @GET("tv/top_rated")
    fun getTvTopRated():Call<TvTopRated>

    @GET("tv/airing_today")
    fun getTvAiringToday(
        @Query("page")page: Int):Call<TvAiringToday>
    @GET("tv/{tv_id}")
    fun getTvDetail(
        @Path("tv_id")tv_id:String):Call<TvDetail>





}