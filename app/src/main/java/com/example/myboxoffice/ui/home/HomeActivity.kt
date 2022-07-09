package com.example.myboxoffice.ui.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myboxoffice.R
import com.example.myboxoffice.base.activity.BaseActivity
import com.example.myboxoffice.callbacks.HomeCallback
import com.example.myboxoffice.databinding.ActivityHomeBinding
import com.example.myboxoffice.listeners.HomeListener
import com.example.myboxoffice.ui.fragments.UpcomingMovieFragment

class HomeActivity : BaseActivity(), HomeListener{

    private val homeVM: HomeVM by lazy { ViewModelProvider(this).get(HomeVM::class.java) }

    private lateinit var mBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding =
            DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
                .apply {
                    this.lifecycleOwner = this@HomeActivity
                    this.viewModel = homeVM
                }

        setContentView(mBinding.root)
        init()



    }

    private fun init() {
        homeVM.getActivityCB().observe(this, vmCallbackObserver)
        homeVM.getCallback().observe(this, callbackObserver)
        onFragmentChangeListener(UpcomingMovieFragment())
        mBinding!!.navigationView.setOnItemSelectedListener{
            when (it.itemId) {
                R.id.home_upcoming -> {
                    onFragmentChangeListener(UpcomingMovieFragment())
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

//        binding.navigationView.setOnNavigationItemSelectedListener(this)



    }

    private val callbackObserver = Observer<HomeCallback> { callBack ->
        when (callBack) {
            is HomeCallback.OnLeftBtnClicked -> {
            }
            is HomeCallback.CloseDrawer -> {
            }
        }
    }


    override fun onFragmentChangeListener(fragment: Fragment?) {
          onFragmentChange(fragment)
    }

    override fun onFragmentChangeListener(popFragment: String, fragment: Fragment?) {
        onFragmentChangeWithPop(popFragment, fragment)
    }



    override fun onProgressVisibility(isVisible: Boolean) {
       progressDialogueVisibility(isVisible)
    }

    override fun forceLogOut() {
        TODO("Not yet implemented")
    }
}