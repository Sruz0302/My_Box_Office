package com.example.myboxoffice.callbacks

sealed class HomeCallback{
    object OnLeftBtnClicked : HomeCallback()
    object CloseDrawer : HomeCallback()
}
