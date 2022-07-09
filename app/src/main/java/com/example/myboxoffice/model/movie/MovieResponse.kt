package com.example.myboxoffice.model.movie

import android.graphics.Bitmap
import com.example.myboxoffice.model.base.BaseResponse
import com.example.myboxoffice.network.APILinks
import java.io.Serializable
import android.graphics.BitmapFactory
import android.util.Log
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.net.URLConnection


class MovieResponse: BaseResponse<ArrayList<MovieResponse.Item>>() {

    data class Item(
        val title: String? = "",
        val overview: String? = "",
        val release_date: String? = "",
        val poster_path: String? = ""
    ) : Serializable {
        fun getImageUrl(): String {
               return APILinks.IMAGE_BASE_URL+poster_path

        }
    }
}

