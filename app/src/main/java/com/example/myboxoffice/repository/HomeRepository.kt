package com.example.myboxoffice.repository

import com.example.myboxoffice.listeners.RepositoryListener
import com.example.myboxoffice.network.RetrofitCalling
import com.example.myboxoffice.network.RetrofitController


class HomeRepository(repositoryListener: RepositoryListener) :
    BaseRepository(repositoryListener) {

    fun getMoveList(page: Int,key: String) {
        val apiService = RetrofitController.apiService.getUpcomingMovieList(
            "ac6ba41373aea74a06ef617eb98d66a8",
            "en-US",
            page
        )
        val retrofitCalling = RetrofitCalling(this, key, apiService)
        retrofitCalling.apiCalling()
    }


}