package com.example.myboxoffice.base.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myboxoffice.callbacks.ActivityVMCallback
import com.example.myboxoffice.listeners.RepositoryListener
import com.example.myboxoffice.utils.CustomToast

open class BaseActivityVM : ViewModel(), RepositoryListener {

    /*Start Activity Elements*/
    protected val vmActivityCB: MutableLiveData<ActivityVMCallback> by lazy {
        MutableLiveData<ActivityVMCallback>()
    }

    fun getActivityCB(): LiveData<ActivityVMCallback> {
        return vmActivityCB
    }

    fun clearObservers(vmObserver: Observer<ActivityVMCallback>) {
        vmActivityCB.removeObserver(vmObserver)
        vmActivityCB.value = ActivityVMCallback.showProgressBar(false)
    }

    override fun <T : Any> onSuccessResponse(key: String, result: T) {
        vmActivityCB.value = ActivityVMCallback.showProgressBar(false)
    }

    override fun onFailureResponse(key: String, error: String) {
        vmActivityCB.value = ActivityVMCallback.showProgressBar(false)
        CustomToast.makeToast(error)
    }

    override fun onUnAuthorize(error: String) {
        vmActivityCB.value = ActivityVMCallback.showProgressBar(false)
        CustomToast.makeToast(error)
        vmActivityCB.value = ActivityVMCallback.ForceLogOut
    }
}