package com.example.myboxoffice.network

object APILinks {

    //https://api.themoviedb.org/3/movie/upcoming?api_key=ac6ba41373aea74a06ef617eb98d66a8&language=en-US&page=1
    //https://api.themoviedb.org/3/movie/upcoming?api_key=ac6ba41373aea74a06ef617eb98d66a8&language=en-US&page=2
    const val BASE_URL: String = "https://api.themoviedb.org/3/"
    //https://image.tmdb.org/t/p/w440_and_h660_face/qvqyDj34Uivokf4qIvK4bH0m0qF.jpg
    //movie
    const val UPCOMING_MOVIES: String = "movie/upcoming"
    const val IMAGE_BASE_URL: String="https://image.tmdb.org/t/p/w440_and_h660_face"
}