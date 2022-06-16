package com.example.prakashjobapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prakashjobapp.R
import com.example.prakashjobapp.models.AppliedJobData

class AppliedJobAdapter(val context: Context, val appliedJobList: List<AppliedJobData>): RecyclerView.Adapter<AppliedJobAdapter.MyViewHolder>()   {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val View = LayoutInflater.from(parent.context).inflate(R.layout.applied_job_1,parent,false)
        return MyViewHolder(View)
    }
    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val appliedJob = appliedJobList[position]
        holder.jobtitle.text = appliedJob.job_title
        holder.companyname.text = appliedJob.company_name
    }

    override fun getItemCount(): Int {

       return appliedJobList.size
    }

    class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {

        val jobtitle = itemView.findViewById<TextView>(R.id.jobtitle)
        val companyname = itemView.findViewById<TextView>(R.id.company_name)
    }
}