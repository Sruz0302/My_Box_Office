package com.example.myboxoffice.network

import com.example.myboxoffice.model.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    // api_key=ac6ba41373aea74a06ef617eb98d66a8&language=en-US&page=2
    @GET(APILinks.UPCOMING_MOVIES)
    fun getUpcomingMovieList(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Call<MovieResponse>

}