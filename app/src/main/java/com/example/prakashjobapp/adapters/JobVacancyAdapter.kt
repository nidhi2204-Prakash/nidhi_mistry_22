package com.example.prakashjobapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prakashjobapp.R
import com.example.prakashjobapp.models.VacancyData


class JobVacancyAdapter(val context : Context, var jobVacancyList: List<VacancyData>): RecyclerView.Adapter<JobVacancyAdapter.MyViewHolder>() {

    private lateinit var mlistener:OnItemClickListener
    var post_date : String = String()
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

//    fun filter(filterString: String) {
//        var filterString = filterString
//        filterString = filterString.toLowerCase(Locale.getDefault())
//        ArrayList<VacancyData>.clear()
//        if (filterString.length == 0) {
//            arraylist.addAll(toDoArrayList)
//        } else {
//            for (toDO in toDoArrayList) {
//                if (toDO.getYourSearchValue().toLowerCase(Locale.getDefault())
//                        .contains(filterString)
//                ) {
//                    arraylist.add(toDO)
//                }
//            }
//        }
//        notifyDataSetChanged()
//    }
//    fun filterList(filteredList: ArrayList<VacancyData>) {
//        filteredList.also { it.also { jobVacancyList = it } }
//        notifyDataSetChanged()
//    }
    fun setOnItemClickListener(listener :OnItemClickListener){
        mlistener = listener
    }
    fun filterList(filteredList: ArrayList<VacancyData>) {
        filteredList.also { it.also { jobVacancyList = it } }
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val View = inflater.inflate(R.layout.job_vacancy,parent,false)
        return MyViewHolder(View,mlistener)
    }
    fun getDate(date: String): String {
        val input = date
        val output = input.substring(0, 10) // Output : 2012/01/20
        return output.toString()
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bindItems(jobVacancyList[position])
          val vacancy = jobVacancyList[position]
           holder.vacancy.text = vacancy.job_title
           holder.post_date.text = getDate(vacancy.job_posted_date)
           holder.Location_1.text = vacancy.location
    }

    override fun getItemCount(): Int {
        return jobVacancyList.size
    }
    inner class MyViewHolder(itemview: View,listner :OnItemClickListener):RecyclerView.ViewHolder(itemview) {

              val vacancy = itemView.findViewById<TextView>(R.id.vacancy)
              val post_date = itemView.findViewById<TextView>(R.id.post_date)
            val Location_1 = itemView.findViewById<TextView>(R.id.Location_1)




//        fun bindItems(Vacancy: VacancyData) {
//            val vacancy = itemView.findViewById<TextView>(R.id.vacancy)
//            val post_date = itemView.findViewById<TextView>(R.id.post_date)
//            val Location_1 = itemView.findViewById<TextView>(R.id.Location_1)
//            vacancy.text = Vacancy.job_title
//            post_date.text = Vacancy.job_posted_date
//           // Location_1.text = Vacancy.toString()
//        }

        init {
            itemview.setOnClickListener{
                listner.onItemClick(adapterPosition)
            }
        }

//        override fun onClick(p0: View?) {
//            val position = adapterPosition
//            if (position != RecyclerView.NO_POSITION) {
//                    Listner.onItemClick(position)
//
//            }
//        }
    }
//    interface OnItemClickListner {
//        fun onItemClick(position: Int)
//    }

}