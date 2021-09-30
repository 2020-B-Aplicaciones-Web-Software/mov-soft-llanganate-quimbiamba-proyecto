package com.example.scrima.ui

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.scrima.R
import com.example.scrima.general.FirebaseConnection
import com.example.scrima.general.Settings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue

class ResultsScannerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results_scanner)
        setTitle("Gateway encontrado")

        val network = intent.extras!!.getParcelable<WifiInfo>("networkInfo")
        setTextViews(network)

        findViewById<Button>(R.id.btn_save_results).setOnClickListener {
            saveNetwork(network)
        }
        findViewById<Button>(R.id.btn_results_back).setOnClickListener {
            openActivity(this, HomeActivity::class.java)
        }
    }

    fun setTextViews(network: WifiInfo?){
        findViewById<TextView>(R.id.tv_results_ip_address).setText("${network!!.ipAddress}")
        findViewById<TextView>(R.id.tv_results_frequency).setText("${network.frequency}")
        findViewById<TextView>(R.id.tv_results_ip_speed).setText("${network.linkSpeed}")
        findViewById<TextView>(R.id.tv_results_mac_address).setText("${network.macAddress}")
        findViewById<TextView>(R.id.tv_results_ssid).setText("${network.ssid}")
        findViewById<TextView>(R.id.tv_results_rssi).setText("${network.rssi}")
    }

    fun saveNetwork(network: WifiInfo?){

        val auth = FirebaseAuth.getInstance()

        val newGateway = hashMapOf<String, Any>(
            "ipAddress" to network!!.ipAddress.toString(),
            "frequency" to network.frequency.toString(),
            "linkSpeed" to network.linkSpeed.toString(),
            "macAddress" to network.macAddress.toString(),
            "ssid" to network.ssid.toString(),
            "rssi" to network.rssi.toString(),
            "uid" to auth.currentUser!!.uid,
            "timestamp" to FieldValue.serverTimestamp()
        )

        FirebaseConnection.getInstance()
            .collection("gateways")
            .add(newGateway)

        FirebaseConnection.getInstance()
            .collection("users")
            .whereEqualTo("uid", auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener { result ->
                for(document in result){
                    FirebaseConnection.getInstance().collection("users").document(document.id)
                        .update(mapOf(
                            "totalScans" to "${document.get("totalScans")}".toInt()+1
                        ))
                }
                Settings.showMessage(this, "Se ha actualizado correctamente")
            }
    }

    fun openActivity(context: Context, classRef: Class<*>) {
        val intentExplicito = Intent(
            context,
            classRef
        )
        startActivity(intentExplicito)
    }
}