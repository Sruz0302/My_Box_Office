package com.example.myboxoffice.network

import com.example.myboxoffice.model.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET(APILinks.UPCOMING_MOVIES)
    fun getUpcomingMovieList(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Call<MovieResponse>

}