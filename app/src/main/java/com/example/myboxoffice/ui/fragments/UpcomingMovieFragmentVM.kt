package com.example.myboxoffice.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myboxoffice.base.fragment.BaseFragmentVM
import com.example.myboxoffice.callbacks.FragmentVMCallback
import com.example.myboxoffice.callbacks.HomeFragmentCallback
import com.example.myboxoffice.model.base.BaseResponse
import com.example.myboxoffice.model.movie.MovieResponse
import com.example.myboxoffice.repository.HomeRepository
import com.example.myboxoffice.utils.CustomToast
import com.example.myboxoffice.utils.Utils

class UpcomingMovieFragmentVM : BaseFragmentVM() {
    var itemsResponse: MovieResponse = MovieResponse()
    var itemsResponseFilter: ArrayList<MovieResponse.Item> = ArrayList()
     var available: Int? = 0
    private var homeRepository: HomeRepository = HomeRepository(this)


    val callback: MutableLiveData<HomeFragmentCallback> by lazy {
        MutableLiveData<HomeFragmentCallback>()
    }
    fun getCallback(): LiveData<HomeFragmentCallback> {
        return callback
    }
    fun remove() {
        callback.value = null
    }
    val KEY_HOME_API: String = "home_api"


    fun onItemClicked(position: Int) {
        vmFragmentCB.value = FragmentVMCallback.onFragmentChanged(
            MovieDetailsFragment.newInstance(
                itemsResponse.results!![position]
            )
        )
    }

    fun getItemsApi(page:Int) {
        vmFragmentCB.value = FragmentVMCallback.HideSoftKeyboard
        if (Utils.isNetworkAvailable()) {
            vmFragmentCB.value = FragmentVMCallback.showProgressBar(true)
            homeRepository.getMoveList(page,KEY_HOME_API)
        }
    }

    override fun <T : Any> onSuccessResponse(key: String, result: T) {
        //Success response
        if (key == KEY_HOME_API)
            onGetItemsApiResponse(result as MovieResponse)

    }

    private fun onGetItemsApiResponse(response: MovieResponse) {
        vmFragmentCB.value = FragmentVMCallback.showProgressBar(false)
        this.itemsResponse = response
        this.itemsResponseFilter.addAll(response.results!!)

        this.available=response.totalPages


        if (!itemsResponse.results.isNullOrEmpty()) {
            callback.value = HomeFragmentCallback.PopulateAdapter
        } else {
            callback.value = HomeFragmentCallback.NoDataFound
            CustomToast.makeToast("No Data Found")
        }
    }
}