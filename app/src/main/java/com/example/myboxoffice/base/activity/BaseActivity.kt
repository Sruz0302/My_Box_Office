package com.example.myboxoffice.base.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.example.myboxoffice.R
import com.example.myboxoffice.callbacks.ActivityVMCallback
import com.example.myboxoffice.utils.Constants
import com.example.myboxoffice.utils.CustomToast
import com.example.myboxoffice.utils.ProgressDialogue
import com.example.myboxoffice.utils.Utils

open class BaseActivity : FragmentActivity() {

    var mIntent: Intent? = null
    var mContext: Context? = null
    var mView: View? = null

    private val progressDialogue by lazy {
        ProgressDialogue(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        mView = parent
        mContext = context
        return super.onCreateView(parent, name, context, attrs)
    }

    protected fun progressDialogueVisibility(isVisible: Boolean) {
        if (isVisible)
            progressDialogue.show()
        else
            progressDialogue.dismiss()
    }

    protected val vmCallbackObserver = Observer<ActivityVMCallback> { callBack ->
        mIntent = null

        when (callBack) {

//            ProgressBar Visibility Handing
            is ActivityVMCallback.ShowProgressBar -> {
                progressDialogueVisibility((callBack.isVisible))
            }

            is ActivityVMCallback.OnBackButtonPressed -> {
                onBackPressed()
            }

            is ActivityVMCallback.NoInternetAccess -> {
                CustomToast.makeToast(getString(R.string.string_no_internet))
            }

            is ActivityVMCallback.OnActivityChanged<*> -> {
                mIntent = Intent(this, callBack.cls)
            }

            is ActivityVMCallback.OnActivityChangedWithBundle<*> -> {
                mIntent = Intent(this, callBack.cls)
                mIntent!!.putExtra(Constants.BUNDLE_KEY, callBack.bundle)
            }

            is ActivityVMCallback.OnActivityChangedWithNewTask<*> -> {
                mIntent = Intent(this, callBack.cls)
                mIntent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            is ActivityVMCallback.OnFragmentChanged -> {
                onFragmentChange(callBack.fragment)
            }

            is ActivityVMCallback.HideSoftKeyboard -> {
                Utils.hideKeyboard(this, mView)
            }
        }

        if (mIntent != null) {
            startActivity(mIntent)
        }
    }


    protected fun onFragmentChange(fragment: Fragment?) {
        if (fragment != null) {
            val backStateName = fragment.javaClass.name
            val manager = supportFragmentManager
            var fragmentPopped: Boolean = false
            try {
                manager.popBackStackImmediate(backStateName, 0)
            } catch (e: Exception) {
                fragmentPopped = true
            }

            if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null) { //fragment not in back stack, create it.
                val ft = manager.beginTransaction()
                ft.replace(R.id.fragmentContainerView, fragment, backStateName)
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ft.addToBackStack(backStateName)
                ft.commit()
            }
        }
    }

    protected fun onFragmentChangeWithPop(popFragment: String, fragment: Fragment?) {
        if (fragment != null) {
            val backStateName: String = fragment::class.java.name
            val manager: FragmentManager = supportFragmentManager

            manager.popBackStackImmediate(
                popFragment,
                0
            )

            val fragmentTransaction: FragmentTransaction = manager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment, backStateName)
            fragmentTransaction.addToBackStack(backStateName)
            fragmentTransaction.commit()
        }
    }
}