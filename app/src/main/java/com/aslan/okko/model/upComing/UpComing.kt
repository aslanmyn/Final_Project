package com.aslan.okko.model.upComing


import com.aslan.okko.model.detail.Dates
import com.google.gson.annotations.SerializedName

data class UpComing(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<UpComingResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)