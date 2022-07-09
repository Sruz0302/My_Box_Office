package com.example.myboxoffice.network

import com.example.myboxoffice.listeners.RetrofitCallingListener
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitCalling<T : Any>(
    var retrofitCallingListener: RetrofitCallingListener,
    var key: String,
    var call: Call<T>
) {

    var job: CompletableJob = Job()

    fun apiCalling() {
        CoroutineScope(Dispatchers.IO + job).launch {

            call.enqueue(object : Callback<T> {

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    job.complete()
                    retrofitCallingListener.onSuccessResponse(
                        key,
                        response
                    )
                }

                override fun onFailure(call: Call<T>?, t: Throwable?) {
                    job.complete()
                    retrofitCallingListener.onFailureResponse(key, t.toString())
                }
            })
        }
    }
}