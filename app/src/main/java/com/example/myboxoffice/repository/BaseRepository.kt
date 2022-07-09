package com.example.myboxoffice.repository

import android.util.Log
import com.example.myboxoffice.listeners.RepositoryListener
import com.example.myboxoffice.listeners.RetrofitCallingListener
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

open class BaseRepository(private val repositoryListener: RepositoryListener) :
    RetrofitCallingListener {

    override fun <T : Any> onSuccessResponse(key: String, response: Response<T>?) {
        if (response!!.isSuccessful) {
            repositoryListener.onSuccessResponse(key, response.body()!!)
        } else {
            repositoryListener.onFailureResponse(key, getErrorMessage(response.errorBody()))
        }
    }

    override fun onFailureResponse(key: String, error: String) {
        repositoryListener.onFailureResponse(key, error)
    }

    // Extracting the error message
    private fun getErrorMessage(errorBody: ResponseBody?): String {
        return try {
            JSONObject(errorBody!!.string())["message"].toString()
        } catch (e: Exception) {
            Log.e("BaseRepository", "getErrorMessage: ${e.message}")
            "Error: Something went wrong..."
        }
    }

    override fun onUnAuthorize(error: String) {
        repositoryListener.onUnAuthorize(error)
    }
}