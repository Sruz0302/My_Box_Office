package com.example.myboxoffice.apps

import android.app.Application
import com.example.myboxoffice.utils.CustomToast
import com.example.myboxoffice.utils.Utils

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        CustomToast.applicationReference(this)
        Utils.applicationReference(this)



    }

    companion object {
        fun getInstance() : MyApplication.Companion {
            return this
        }
    }


}