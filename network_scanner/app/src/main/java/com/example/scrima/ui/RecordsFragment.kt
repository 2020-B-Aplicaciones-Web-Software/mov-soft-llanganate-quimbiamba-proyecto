package com.example.scrima.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scrima.R
import com.example.scrima.entities.ScanRecord
import com.example.scrima.general.FirebaseConnection
import com.example.scrima.ui.adapters.ScanRecordAdapter
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.type.Date
import java.text.SimpleDateFormat

class RecordsFragment : Fragment(R.layout.fragment_records) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_records, container, false)
        val auth = FirebaseAuth.getInstance()

        FirebaseConnection.getInstance()
            .collection("gateways")
            .whereEqualTo("uid", auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener {  result ->
                val recordsList = arrayListOf<ScanRecord>()
                for(document in result){
                    val timestamp = document.get("timestamp") as com.google.firebase.Timestamp
                    val milliseconds = timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000
                    val sdf = SimpleDateFormat("MM/dd/yyyy")
                    val tf = SimpleDateFormat("HH:mm:ss")
                    val netDate = java.util.Date(milliseconds)
                    val date = sdf.format(netDate).toString()
                    val time = tf.format(netDate).toString()
                    recordsList.add(
                        ScanRecord(
                            "${document.get("ipAddress")}",
                            "${document.get("macAddress")}",
                             date,
                            time
                        )
                    )
                }

                val recyclerView= view.findViewById<RecyclerView>(
                    R.id.list_record_recyclerview
                )
                initRecyclerView(recordsList, HomeActivity(), recyclerView)

            }

        return view
    }

    fun initRecyclerView(
        list: List<ScanRecord>,
        activity: HomeActivity,
        recyclerView: RecyclerView
    ){
        val adapter = ScanRecordAdapter(
            activity,
            list,
            recyclerView
        )
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        adapter.notifyDataSetChanged()
    }
}