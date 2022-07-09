package com.example.myboxoffice.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myboxoffice.base.activity.BaseActivityVM
import com.example.myboxoffice.callbacks.HomeCallback

class HomeVM : BaseActivityVM() {
    private val TAG = HomeVM::class.java.simpleName


    private val callback: MutableLiveData<HomeCallback> by lazy {
        MutableLiveData<HomeCallback>()
    }

    fun getCallback(): LiveData<HomeCallback> {
        return callback
    }



}