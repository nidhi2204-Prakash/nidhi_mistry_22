package com.example.prakashjobapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prakashjobapp.R
import com.example.prakashjobapp.SessionManager
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
    lateinit var data_empty :TextView
    lateinit var appliedJobList :RecyclerView
    private lateinit var sessionManager :SessionManager

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
        data_empty = view.findViewById(R.id.data_empty)
      //  adapterAppliedJonList = AppliedJobAdapter(appliedJobList)
      //  AppliedJobJB.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
     //   AppliedJobJB.adapter = adapterAppliedJonList
        AppliedJob()
        return view

    }
    fun AppliedJob(){
        sessionManager = SessionManager(requireActivity())
        val id = sessionManager.getString(SessionManager.KEY_ID).toString()
        val id3 = id.toInt()
        RetrofitBuilder.JsonServices.jsonInstance.appliedjob(id3).enqueue(object : Callback<AppliedJobs?> {
            override fun onResponse(call: Call<AppliedJobs?>, response: Response<AppliedJobs?>) {

                Log.d("TAG", "Applied Jobs: " + response.body()!!.Data.size)
                try {
                    val applyjob1 = response.body()
                    if (applyjob1 != null){
                        adapterAppliedJonList = activity?.let { AppliedJobAdapter(it, applyjob1.Data) }!!
                        AppliedJobJB.adapter = adapterAppliedJonList
                        AppliedJobJB.layoutManager = LinearLayoutManager(activity)
//                        if (applyjob1.Data.isEmpty()){
//
//                        }

                        val dataNull = (AppliedJobJB.adapter as AppliedJobAdapter).itemCount
                        if (dataNull == 0) {

                            data_empty.setVisibility(View.VISIBLE);
                        }
                        else {
                            data_empty.setVisibility(View.INVISIBLE);

                        }
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