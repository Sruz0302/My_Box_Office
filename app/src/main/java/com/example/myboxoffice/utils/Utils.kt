package com.example.myboxoffice.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.myboxoffice.R
import java.io.*

@SuppressLint("StaticFieldLeak")
object Utils {
    private val TAG = this::class.java.simpleName
    private var context: Context? = null

    fun applicationReference(mContext: Context) {
        context = mContext
    }

    fun hideKeyboard(context: Context, view: View?) {
        val imm: InputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun showKeyboard(context: Context, view: View?) {
        val imm: InputMethodManager =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var value = false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork
            if (nw != null) {
                val actNw = connectivityManager.getNetworkCapabilities(nw)
                if (actNw != null) {
                    value = when {
                        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        //for other device how are able to connect with Ethernet
                        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        actNw.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                        //for check internet over Bluetooth
                        actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                        else -> false
                    }
                }
            }
        } else {
            @Suppress("DEPRECATION")
            value = connectivityManager.activeNetworkInfo?.isConnected ?: false
        }

        if (!value) {
            Toast.makeText(
                context,
                context!!.getString(R.string.string_no_internet),
                Toast.LENGTH_LONG
            ).show()
        }
        return value
    }




}