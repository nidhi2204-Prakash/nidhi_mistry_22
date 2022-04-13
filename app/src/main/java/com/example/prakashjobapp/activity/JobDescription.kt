package com.example.prakashjobapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.prakashjobapp.R
import com.example.prakashjobapp.api.KeyClass

class JobDescription : AppCompatActivity() {
    lateinit var backarrow: ImageView
    lateinit var job_title: TextView
    lateinit var company_name: TextView
    lateinit var location_textview: TextView
    lateinit var text_part_time: TextView
    lateinit var no_of_position: TextView
    lateinit var btn_description: Button
    lateinit var btn_company: Button
    lateinit var Job_description: TextView
    lateinit var req_years: TextView
    lateinit var Jobd_text : TextView
    lateinit var req_years_text : TextView
    lateinit var job_type_text : TextView
    lateinit var skill_required: TextView
    lateinit var job_type: TextView
    lateinit var openingDate : TextView
    lateinit var closing_date  : TextView
    lateinit var closing_date_text  : TextView
    lateinit var opening_date_Text  : TextView
    lateinit var apply_now: Button


    companion object{

        var companyName1 :String = String()
        var jobTitle :String = String()
        var employmentType :String = String()
        var jobDescription :String = String()
        var jobType : String = String()
        var openSate :String = String()
        var closeDate :String = String()
        var requiredYearExpirence :String = String()
        var noOfposition :String = String()
        var location :String = String()

    }

    fun getDate(date:String): String {
        val input = date
        val output = input.substring(0, 10) // Output : 2012/01/20
        return output.toString();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_description)

        opening_date_Text = findViewById(R.id.opening_date_Text)
        closing_date_text = findViewById(R.id.closing_date_text)
        req_years_text = findViewById(R.id.req_years_text)
        openingDate = findViewById(R.id.openingDate)
        closing_date = findViewById(R.id.closing_date)
        job_type_text = findViewById(R.id.job_type_text)
        Jobd_text = findViewById(R.id.Jobd_text)
        backarrow = findViewById(R.id.backarrow)
        company_name = findViewById(R.id.company_name)
        location_textview = findViewById(R.id.location_textview)
        text_part_time = findViewById(R.id.text_part_time)
        no_of_position = findViewById(R.id.no_of_position)
        btn_description = findViewById(R.id.btn_description)
        btn_company = findViewById(R.id.btn_company)
        Job_description = findViewById(R.id.Job_description)
        req_years = findViewById(R.id.req_years)
        skill_required = findViewById(R.id.skill_required)
        job_type = findViewById(R.id.job_type)
        job_title = findViewById(R.id.job_title)
        apply_now = findViewById(R.id.apply_now)

        //GetData
        val bundle : Bundle = intent.extras!!
        companyName1 = bundle.getString(KeyClass.KEY_COMPANY_NAME)!!
        company_name.text = companyName1
        jobTitle =  bundle.getString(KeyClass.KEY_JOB_TITLE)!!
        job_title.text = jobTitle
        jobType  =  bundle.getString(KeyClass.KEY_JOB_TYPE)!!
        job_type_text.text =jobType
        openSate  =  bundle.getString(KeyClass.KEY_OPENING_DATE)!!
        opening_date_Text.text = getDate(openSate)
        closeDate  =  bundle.getString(KeyClass.KEY_CLOSING_DATE)!!
        closing_date_text.text = getDate(closeDate)
        requiredYearExpirence  =  bundle.getString(KeyClass.KEY_REQUIRED_YEAR_EXPIRENCE)!!
        req_years_text.text =requiredYearExpirence
        noOfposition  =  bundle.getString(KeyClass.KEY_REQUIRED_YEAR_EXPIRENCE)!!
        no_of_position.text = noOfposition
        jobDescription  =  bundle.getString(KeyClass.KEY_JOB_DESCRIPTION)!!
        Jobd_text.text =jobDescription
        employmentType  =  bundle.getString(KeyClass.KEY_EMPLOYMENT_TYPE)!!
        text_part_time.text = employmentType
        location = bundle.getString(KeyClass.KEY_LOCATION)!!
        location_textview.text = location


        backarrow.setOnClickListener {
            val intent = Intent(this, JobVacancyPage::class.java)
            startActivity(intent)
        }
        apply_now.setOnClickListener {
            val intent = Intent(this, JobApply::class.java)
            startActivity(intent)

        }
        btn_description.setOnClickListener {
//            val intent = Intent(this, JobApply::class.java)
//           startActivity(intent)

        }
        btn_company.setOnClickListener {
//            val intent = Intent(this, JobApply::class.java)
//            startActivity(intent)

        }
    }
}