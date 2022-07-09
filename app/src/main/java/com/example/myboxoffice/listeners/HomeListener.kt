package com.example.myboxoffice.listeners

import androidx.fragment.app.Fragment

interface HomeListener {

    fun onFragmentChangeListener(fragment: Fragment?)

    fun onFragmentChangeListener(popFragment: String, fragment: Fragment?)

    fun onProgressVisibility(isVisible: Boolean)

    fun forceLogOut()


}