package com.example.prakashjobapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prakashjobapp.R
import com.example.prakashjobapp.adapters.AppliedJobAdapter
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.AppliedJobs
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AppliedFragment : Fragment(R.layout.fragment_applied) {
    lateinit var AppliedJobJB :RecyclerView
    lateinit var adapterAppliedJonList: AppliedJobAdapter
    lateinit var appliedJobList :RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val AppliedJobList = ArrayList<String>()
//        AppliedJobList.add("Applied Job 2")
//        AppliedJobList.add("Applied Job 3")
//        AppliedJobList.add("Applied Job 4")
//        AppliedJobList.add("Applied Job 5")
//        AppliedJobList.add("Applied Job 6")

        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_applied, container, false)
        AppliedJobJB =view.findViewById(R.id.AppliedJob_recyclerView)
      //  adapterAppliedJonList = AppliedJobAdapter(appliedJobList)
      //  AppliedJobJB.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
     //   AppliedJobJB.adapter = adapterAppliedJonList
        AppliedJob()

        return view

    }
    fun AppliedJob(){
        RetrofitBuilder.JsonServices.jsonInstance.appliedjob(80).enqueue(object : Callback<AppliedJobs?> {
            override fun onResponse(call: Call<AppliedJobs?>, response: Response<AppliedJobs?>) {

                Log.d("TAG", "Applied Jobs: " + response.body()!!.Data.size)
                try {
                    val applyjob1 = response.body()
                    if (applyjob1 != null){

                        adapterAppliedJonList = activity?.let { AppliedJobAdapter(it, applyjob1.Data) }!!
                        AppliedJobJB.adapter = adapterAppliedJonList
                        AppliedJobJB.layoutManager = LinearLayoutManager(activity)

                    }

                }catch (e : JSONException){
                    e.printStackTrace()
                }
                catch (e : NullPointerException){
                    e.printStackTrace()
                }

            }

            override fun onFailure(call: Call<AppliedJobs?>, t: Throwable) {
                Log.d("TAG", " Got Error " + t.localizedMessage)
            }
        })
    }
}