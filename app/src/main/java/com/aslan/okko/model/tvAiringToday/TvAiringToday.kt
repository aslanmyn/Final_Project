package com.aslan.okko.model.tvAiringToday


import com.google.gson.annotations.SerializedName

data class TvAiringToday(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TvAiringTodayResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)