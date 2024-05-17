package com.aslan.okko.model.Popular

import com.google.gson.annotations.SerializedName

data class Popular(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results : ArrayList<ResultResponse>,
    @SerializedName("total_result")
    val total_result : Int,
    @SerializedName("total_pages")
    val total_pages : Int
)
