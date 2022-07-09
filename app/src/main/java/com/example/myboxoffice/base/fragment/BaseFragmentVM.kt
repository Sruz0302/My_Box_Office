package com.example.myboxoffice.base.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myboxoffice.callbacks.FragmentVMCallback
import com.example.myboxoffice.listeners.RepositoryListener
import com.example.myboxoffice.model.base.BaseResponse
import com.example.myboxoffice.utils.CustomToast

open class BaseFragmentVM: ViewModel(), RepositoryListener {
    protected val vmFragmentCB: MutableLiveData<FragmentVMCallback> by lazy {
        MutableLiveData<FragmentVMCallback>()
    }

    fun getFragmentCB(): LiveData<FragmentVMCallback> {
        return vmFragmentCB
    }

    fun clearObservers(vmObserver: Observer<FragmentVMCallback>) {
        vmFragmentCB.removeObserver(vmObserver)
        vmFragmentCB.value = FragmentVMCallback.showProgressBar(false)
    }

    override fun <T : Any> onSuccessResponse(key: String, result: T) {
        vmFragmentCB.value = FragmentVMCallback.showProgressBar(false)
    }

    override fun onFailureResponse(key: String, error: String) {
        vmFragmentCB.value = FragmentVMCallback.showProgressBar(false)
        CustomToast.makeToast(error)
    }

    override fun onUnAuthorize(error: String) {
        // TODO("Not yet implemented")
    }
}