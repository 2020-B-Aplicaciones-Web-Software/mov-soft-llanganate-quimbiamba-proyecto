package com.example.scrima.ui

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiInfo
import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scrima.R
import com.example.scrima.entities.NetworkScanner
import com.example.scrima.entities.ScanRecord
import com.example.scrima.ui.adapters.ScanRecordAdapter

class ScanFragment : Fragment(R.layout.fragment_scan) {  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_scan, container, false)

        view.findViewById<ProgressBar>(R.id.progressBar_scan).visibility = View.INVISIBLE
        view.findViewById<TextView>(R.id.tv_scan).visibility = View.INVISIBLE

        view.findViewById<Button>(R.id.btn_scan_network).setOnClickListener {
            view.findViewById<ProgressBar>(R.id.progressBar_scan).visibility = View.VISIBLE
            view.findViewById<TextView>(R.id.tv_scan).visibility = View.VISIBLE
            if(container !=null){
                val information = NetworkScanner.getGatewayInformation(container.context)
                openActivityWithParams(container.context, ResultsScannerActivity::class.java, arrayListOf(
                    Pair("networkInfo", information)
                )
                )
            }
        }
        return view
    }

    fun openActivityWithParams(context: Context, classReference: Class<*>, params:  ArrayList<Pair<String, WifiInfo?>>) {
        val intent = Intent(
            context,
            classReference
        )
        params.forEach {
                param ->
            var (key, value) = param
            intent.putExtra(key, value)
        }
        context.startActivity(intent)
    }
}