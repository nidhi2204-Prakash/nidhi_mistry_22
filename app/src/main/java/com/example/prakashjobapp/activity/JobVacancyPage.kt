package com.example.prakashjobapp.activity

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prakashjobapp.R
import com.example.prakashjobapp.adapters.JobVacancyAdapter
import com.example.prakashjobapp.api.KeyClass
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.Vacancy
import com.example.prakashjobapp.models.VacancyData
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobVacancyPage : AppCompatActivity()  {
    lateinit var jobvacancyJV  :RecyclerView
    lateinit var  adapterJobVacancy: JobVacancyAdapter
    lateinit var jobVacancyList : RecyclerView

    lateinit var Back_Arrow :ImageView
    lateinit var companyName_1: TextView
    lateinit var search_view :EditText
    lateinit var data_check :TextView
    var vacancydata = ArrayList<VacancyData>()

    companion object
    {
        var companyName1 : String = String()
        var id : Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_vacancy_page)

        search_view = findViewById(R.id.search_view)
        Back_Arrow  = findViewById(R.id.Back_Arrow)
        companyName_1 = findViewById(R.id.companyName_1)
        data_check = findViewById(R.id.data_check)

        val bundle : Bundle? = intent.extras
        companyName1  = bundle!!.getString(KeyClass.KEY_COMPANY_NAME)!!
        companyName_1.text = companyName1
        id = bundle.getInt(KeyClass.KEY_COMPANY_ID)
        jobvacancyJV = findViewById(R.id.vacancy_recyclerview)
         vacancyListdata()

        Back_Arrow.setOnClickListener{
            onBackPressed()
        }

        search_view.setOnClickListener {
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
        search_view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}


            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


            override fun afterTextChanged(s: Editable?) {
                // After changes
                filter(s.toString())
                val dataNull = (jobvacancyJV.adapter as JobVacancyAdapter).itemCount
                if (dataNull == 0){
                    data_check.visibility = View.VISIBLE
                }
                else{
                    data_check.visibility = View.GONE
                }
            }
        })
    }
    private fun filter(text :String){
       val filteredList = ArrayList<VacancyData>()
        vacancydata.filterTo(filteredList) {
            it.job_title!!.toLowerCase().contains(text.toLowerCase())
     }
     if (filteredList != null){
         adapterJobVacancy.filterList(filteredList)
        }
    }

    fun vacancyListdata(){
        RetrofitBuilder.JsonServices.jsonInstance.vacancyData(id).enqueue(object : Callback<Vacancy> {
            override fun onResponse(call: Call<Vacancy>, response: Response<Vacancy>) {

                try {
                    val vacancy1 = response.body()
                    if (vacancy1 != null) {

                        adapterJobVacancy = JobVacancyAdapter(this@JobVacancyPage,
                            vacancy1.Data as List<VacancyData>)
                        jobvacancyJV.adapter = adapterJobVacancy
                        vacancydata.addAll(response.body()!!.Data)
                        jobvacancyJV.adapter!!.notifyDataSetChanged()
                        //If data is null
                        val dataNull = (jobvacancyJV.adapter as JobVacancyAdapter).itemCount
                        if (dataNull == 0){
                            data_check.visibility = View.VISIBLE
                        }
                        else{
                            data_check.visibility = View.GONE
                        }
                        jobvacancyJV.layoutManager = LinearLayoutManager(this@JobVacancyPage)
                        adapterJobVacancy.setOnItemClickListener(object : JobVacancyAdapter.OnItemClickListener {
                            override fun onItemClick(position: Int) {

                               val intent = Intent(this@JobVacancyPage, JobDescription::class.java)
                               intent.putExtra(KeyClass.KEY_JOB_TITLE, vacancy1.Data[position].job_title)
                                intent.putExtra(KeyClass.KEY_VACANCY_ID,vacancy1.Data[position].id)
                                intent.putExtra(KeyClass.KEY_COMPANY_ID, vacancy1.Data[position].company_id)
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
