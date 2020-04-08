package br.com.digitalhouse.projetomarvel.util

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object AppUtil {
    fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo?
        if (connectivityManager != null) {
            networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected &&
                    (networkInfo.type == ConnectivityManager.TYPE_WIFI
                            || networkInfo.type == ConnectivityManager.TYPE_MOBILE)
        }
        return false
    }

    fun salvarIdUsuario(context: Context, uiid: String?) {
        val preferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)
        preferences.edit().putString("UIID", uiid).apply()
    }

    fun getIdUsuario(context: Context): String? {
        val preferences = context.getSharedPreferences("APP", Context.MODE_PRIVATE)
        return preferences.getString("UIID", "")
    }

    fun printKeyHash(context: Context) {
        try {
            val info = context.packageManager.getPackageInfo(
                    context.packageName, PackageManager.GET_SIGNATURES) //Your package name here
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }
}