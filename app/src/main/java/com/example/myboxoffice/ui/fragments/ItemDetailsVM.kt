package com.example.myboxoffice.ui.fragments

import androidx.lifecycle.MutableLiveData
import com.example.myboxoffice.base.fragment.BaseFragmentVM
import com.example.myboxoffice.model.movie.MovieResponse

class ItemDetailsVM : BaseFragmentVM(){

    val item: MutableLiveData<MovieResponse.Item> by lazy {
        MutableLiveData<MovieResponse.Item>()
    }
    init {
        item.value = MovieResponse.Item()
    }


}