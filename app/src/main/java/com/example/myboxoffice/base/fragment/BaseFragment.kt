package com.example.myboxoffice.base.fragment

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.myboxoffice.callbacks.FragmentVMCallback
import com.example.myboxoffice.listeners.HomeListener
import com.example.myboxoffice.utils.Utils

open class BaseFragment : Fragment() {
    protected lateinit var mContext: Context
    protected lateinit var homeListener: HomeListener
    protected var mView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        this.homeListener = context as HomeListener
    }

    protected val vmCallbackObserver = Observer<FragmentVMCallback> { callBack ->

        when (callBack) {
//            ProgressBar Visibility Handing
            is FragmentVMCallback.ShowProgressBar -> {
                homeListener.onProgressVisibility((callBack.isVisible))
            }
            is FragmentVMCallback.OnFragmentChanged -> {
                homeListener.onFragmentChangeListener(callBack.fragment)
            }

            is FragmentVMCallback.HideSoftKeyboard -> {
                Utils.hideKeyboard(mContext, mView)
            }

        }
    }
}