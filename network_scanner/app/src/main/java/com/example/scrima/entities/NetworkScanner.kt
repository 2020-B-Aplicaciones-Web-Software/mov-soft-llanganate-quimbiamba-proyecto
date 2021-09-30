package com.example.scrima.entities

import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager

class NetworkScanner {
    companion object{
        fun getGatewayInformation(context: Context): WifiInfo? {
            val wifiManager : WifiManager = (context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager)
            return wifiManager.connectionInfo
        }
    }
}