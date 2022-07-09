package com.example.myboxoffice.listeners

interface RepositoryListener {
    fun <T : Any> onSuccessResponse(key: String, result: T)
    fun onFailureResponse(key: String, error: String)
    fun onUnAuthorize(error: String)

    //https://api.themoviedb.org/3/movie/upcoming?api_key=ac6ba41373aea74a06ef617eb98d66a8&language=en-US&page=1
    //https://api.themoviedb.org/3/movie/upcoming?api_key=ac6ba41373aea74a06ef617eb98d66a8&language=en-US&page=2
}