package com.example.prakashjobapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prakashjobapp.R

class PopularjobsAdapter :RecyclerView.Adapter<PopularjobsAdapter.MyViewHolder>() {


     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val View=LayoutInflater.from(parent.context).inflate(R.layout.row,parent,false)
            return MyViewHolder(View)
     }


     override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

     }

     override fun getItemCount(): Int {
        return  25
     }

     class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview) {

     }
 }