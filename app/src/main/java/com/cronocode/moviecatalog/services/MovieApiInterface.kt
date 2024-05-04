package com.cronocode.moviecatalog.services

import com.cronocode.moviecatalog.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("/3/movie/popular?api_key=44f514f3a7416ef8cd68b3bcf7211caa")
    fun getMovieList(): Call<MovieResponse>
}