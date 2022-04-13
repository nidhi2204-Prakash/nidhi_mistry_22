package com.example.prakashjobapp.activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prakashjobapp.R
import com.example.prakashjobapp.adapters.JobVacancyAdapter
import com.example.prakashjobapp.api.KeyClass
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.Vacancy
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobVacancyPage : AppCompatActivity()  {
    lateinit var jobvacancyJV  :RecyclerView
    lateinit var  jobVacancyAdapter: JobVacancyAdapter
    lateinit var Back_Arrow :ImageView
    lateinit var companyName_1: TextView
    lateinit var search_view :EditText
    lateinit var buttonsearch :ImageButton
    lateinit var jobVacancyList : RecyclerView
//    lateinit var jobVacancyList :MutableList<VacancyData>

    companion object
    {
        var companyName1 : String = String()
    }
    fun getDate(date: String): String {
        val input = date
        val output = input.substring(0, 10) // Output : 2012/01/20
        return output.toString();
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_vacancy_page)

        buttonsearch = findViewById(R.id.buttonsearch)
        search_view = findViewById(R.id.search_view)
        Back_Arrow  = findViewById(R.id.Back_Arrow)
        companyName_1 = findViewById(R.id.companyName_1)

        val bundle : Bundle? = intent.extras
        companyName1  = bundle!!.getString(KeyClass.KEY_COMPANY_NAME)!!
        companyName_1.text = companyName1

//        val jobVacancyList = ArrayList<String>()
//        jobVacancyList.add("Job Vacancy 2")
//        jobVacancyList.add("Job Vacancy 3")
//        jobVacancyList.add("Job Vacancy 4")
//        jobVacancyList.add("Job Vacancy 5")
//        jobVacancyList.add("Job Vacancy 6")

//        jobVacancyList = ArrayList<VacancyData>()
        jobvacancyJV = findViewById(R.id.vacancy_recyclerview)
      //  jobVacancyAdapter = JobVacancyAdapter(this, jobVacancyList)
       // jobvacancyJV.layoutManager = LinearLayoutManager(this)
      //  jobvacancyJV.adapter = jobVacancyAdapter
         vacancyListdata()

        Back_Arrow.setOnClickListener{

         val intent = Intent(this, DashboardActivity::class.java)
          startActivity(intent)

        }

        buttonsearch.setOnClickListener {
            if (search_view.length()>=3){
                //api
            }
            else{
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.minimum_3_characters_are_required_to_search),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
//        search_view.setOnClickListener {
//            if (search_view.length()>=3){
//                //api
//                vacancyApi()
//            }
//            else{
//                Toast.makeText(
//                    getApplicationContext(),
//                    resources.getString(R.string.minimum_3_characters_are_required_to_search),
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
    }
//    override fun onItemClick(position: Int) {
//        val intent = Intent(this, JobDescription::class.java)
//        startActivity(intent)
//        vacancyListdata()
//    }

    fun vacancyListdata(){

        RetrofitBuilder.JsonServices.jsonInstance.vacancyData(17).enqueue(object : Callback<Vacancy> {
            override fun onResponse(call: Call<Vacancy>, response: Response<Vacancy>) {
                Log.d("TAG", "Total Vacancies: " + response.body()!!.Data.size)

                try {
                    val vacancy1 = response.body()
                    if (vacancy1 != null) {

                        jobVacancyAdapter = JobVacancyAdapter(this@JobVacancyPage,vacancy1.Data)
                        jobvacancyJV.adapter = jobVacancyAdapter
                        jobvacancyJV.layoutManager = LinearLayoutManager(this@JobVacancyPage)
                        jobVacancyAdapter.setOnItemClickListener(object : JobVacancyAdapter.OnItemClickListener {
                            override fun onItemClick(position: Int) {

                               val intent = Intent(this@JobVacancyPage, JobDescription::class.java)
                               intent.putExtra(KeyClass.KEY_JOB_TITLE, vacancy1.Data[position].job_title)
                               intent.putExtra(KeyClass.KEY_JOB_POSTED_DATE, vacancy1.Data[position].job_posted_date)
                                intent.putExtra(KeyClass.KEY_COMPANY_NAME, companyName1)
                               intent.putExtra(KeyClass.KEY_REQUIRED_YEAR_EXPIRENCE, vacancy1.Data[position].required_yr_experience)
                               intent.putExtra(KeyClass.KEY_OPENING_DATE, vacancy1.Data[position].job_start_date)
                               intent.putExtra(KeyClass.KEY_CLOSING_DATE, vacancy1.Data[position].job_end_date)
                               intent.putExtra(KeyClass.KEY_JOB_TYPE, vacancy1.Data[position].jobtype)
                               intent.putExtra(KeyClass.KEY_LOCATION, vacancy1.Data[position].location)
                               intent.putExtra(KeyClass.KEY_NO_OF_POSITION, vacancy1.Data[position].no_of_position)
                               intent.putExtra(KeyClass.KEY_EMPLOYMENT_TYPE, vacancy1.Data[position].employment_type)
                               intent.putExtra(KeyClass.KEY_JOB_DESCRIPTION, vacancy1.Data[position].job_description)

                                startActivity(intent)
                           }
                       })
                    }
//                    if (response.body()?.Data!!.size != null) {
//                        jobVacancyList.removeAll(jobVacancyList)
//                        jobVacancyList.addAll(response.body()!!.Data)
//                        jobVacancyAdapter.notifyDataSetChanged()
//                        jobvacancyJV.layoutManager = LinearLayoutManager(companyName_1.context)
//                        jobvacancyJV.adapter = jobVacancyAdapter


                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
            override fun onFailure(call: Call<Vacancy>, t: Throwable) {
                Log.e(ContentValues.TAG, "Got error : " + t.localizedMessage)
            }
        })
    }
}
