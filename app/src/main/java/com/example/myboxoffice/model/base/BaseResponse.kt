package com.example.myboxoffice.model.base

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import com.google.gson.annotations.Expose




open class BaseResponse<T>(t: T? = null) : Serializable {



    @SerializedName("results")
    open val results: T? = t

    @SerializedName("dates")
    private val dates: Dates? = null

    @SerializedName("page")
    open val page: Int? = null

    @SerializedName("total_pages")
    open val totalPages: Int? = null

    @SerializedName("total_results")
    private val totalResults: Int? = null

}