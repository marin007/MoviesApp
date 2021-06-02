package com.example.moviesapp.ui.utils

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.widget.TextView
import android.widget.Toast


fun showErrorToast(context: Context, message: String) {
    val toast: Toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
    val view = toast.view
    view?.background?.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
    val text = view?.findViewById<TextView>(android.R.id.message)
    text?.setTextColor(Color.WHITE)
    toast.show()
}

fun verifyAvailableNetwork(context: Context):Boolean{
    val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo=connectivityManager.activeNetworkInfo
    return  networkInfo!=null && networkInfo.isConnected
}
