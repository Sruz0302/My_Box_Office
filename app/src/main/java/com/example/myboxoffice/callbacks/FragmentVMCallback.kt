package com.example.myboxoffice.callbacks

import androidx.fragment.app.Fragment

sealed class FragmentVMCallback {

    data class ShowProgressBar(var isVisible: Boolean) : FragmentVMCallback()
    data class OnFragmentChanged(val fragment: Fragment) : FragmentVMCallback()
    object HideSoftKeyboard : FragmentVMCallback()
    companion object {
        fun showProgressBar(isVisible: Boolean): FragmentVMCallback = ShowProgressBar(isVisible)
        fun onFragmentChanged(fragment: Fragment): FragmentVMCallback = OnFragmentChanged(fragment)
    }
}