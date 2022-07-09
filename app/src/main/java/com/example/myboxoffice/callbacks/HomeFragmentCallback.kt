package com.example.myboxoffice.callbacks

sealed class HomeFragmentCallback{
    object PopulateAdapter : HomeFragmentCallback()
    object NoDataFound : HomeFragmentCallback()
}
