package com.example.scrima.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scrima.entities.ScanRecord
import com.example.scrima.ui.HomeActivity
import android.widget.TextView
import com.example.scrima.R

class ScanRecordAdapter(
    private val context: HomeActivity,
    private val records: List<ScanRecord>,
    private val recyclerView: RecyclerView
) : RecyclerView.Adapter<ScanRecordAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ipAddressTextView: TextView
        val companyTextView: TextView
        val dateTextView: TextView
        val timeTextView: TextView
        init {
            ipAddressTextView = view.findViewById(R.id.tv_ip_list_record)
            companyTextView = view.findViewById(R.id.tv_producer_list_record)
            dateTextView = view.findViewById(R.id.tv_date_record)
            timeTextView = view.findViewById(R.id.tv_time_record)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.list_record,
                parent,
                false,
            )
        return MyViewHolder(itemView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val scanRecord = records[position]
        holder.ipAddressTextView.text = scanRecord.ipGateway
        holder.companyTextView.text = scanRecord.addressMAC
        holder.dateTextView.text = scanRecord.date
        holder.timeTextView.text = scanRecord.time
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int = records.size
}
