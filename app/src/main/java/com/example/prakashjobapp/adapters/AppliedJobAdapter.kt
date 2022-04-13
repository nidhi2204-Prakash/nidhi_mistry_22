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
     //   holder.bindItems(AppliedJobList[position])
        val appliedJob = appliedJobList[position]
        holder.jobtitle.text = appliedJob.job_title
        holder.companyname.text = appliedJob.company_name
//        holder.first_name.text = appliedJob.first_name
    }

    override fun getItemCount(): Int {
       return appliedJobList.size
    }

    class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {
//        fun bindItems(AppliedJob: String) {
//            val Applied_Job = itemView.findViewById<TextView>(R.id.Applied_Job)
//            Applied_Job.text = AppliedJob
//        }
        val jobtitle = itemView.findViewById<TextView>(R.id.jobtitle)
        val companyname = itemView.findViewById<TextView>(R.id.company_name)
     //   val first_name = itemView.findViewById<TextView>(R.id.first_Nametext)

       // val post_date = itemView.findViewById<TextView>(R.id.post_date)

    }
}