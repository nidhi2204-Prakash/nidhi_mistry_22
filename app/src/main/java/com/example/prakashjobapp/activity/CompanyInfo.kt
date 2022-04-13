package com.example.prakashjobapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.prakashjobapp.R
import com.example.prakashjobapp.activity.EducationInfo.Companion.GapinWorkExp
import com.example.prakashjobapp.activity.EducationInfo.Companion.companyNameCI
import com.example.prakashjobapp.activity.EducationInfo.Companion.currentCTC
import com.example.prakashjobapp.activity.EducationInfo.Companion.currentDesignination
import com.example.prakashjobapp.activity.EducationInfo.Companion.department
import com.example.prakashjobapp.activity.EducationInfo.Companion.employmentTypeCI
import com.example.prakashjobapp.activity.EducationInfo.Companion.expectedCTC
import com.example.prakashjobapp.activity.EducationInfo.Companion.jobTypwCI
import com.example.prakashjobapp.activity.EducationInfo.Companion.noticePeriod
import com.example.prakashjobapp.activity.EducationInfo.Companion.totalExp
import com.example.prakashjobapp.api.KeyClass
import com.example.prakashjobapp.api.RequestParameters
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.DisplayUser
import com.example.prakashjobapp.models.PersonalInfoData
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CompanyInfo : AppCompatActivity() {
    lateinit var back_Arrow_2 :ImageView
    lateinit var editbutton_2 :ImageView
    lateinit var previous_button : AppCompatButton
    lateinit var companyinfo_layout :LinearLayout
    lateinit var companyName_text : EditText
    lateinit var current_desig_text :EditText
    lateinit var jobtype_text :EditText
    lateinit var employment_type_text :EditText
    lateinit var totalexp_text :EditText
    lateinit var Department_text :EditText
    lateinit var noticePeriod_text :EditText
    lateinit var gap_in_workExp :EditText
    lateinit var currentCTC_text :EditText
    lateinit var ExpectedCTC_text :EditText
    lateinit var updatebutton :AppCompatButton
    lateinit var next_button :AppCompatButton

    companion object{

      lateinit  var fName: String
      lateinit  var lName :String
      lateinit  var email :String
      lateinit var password :String
        lateinit var mobileNo :String
        lateinit var address  :String
        lateinit var city :String
        lateinit var state :String
        lateinit var country :String
        lateinit var dateOfBirth :String
        lateinit var gapInedu :String
        lateinit var gender :String
        lateinit var knownLanguage:String
        lateinit var ProfileImage :String
        lateinit var uploadResume :String

//        lateinit var companyNameCI :String
//        lateinit var currentDesignination :String
//        lateinit var jobTypwCI : String
//        lateinit var employmentTypeCI  : String
//        lateinit var totalExp  : String
//        lateinit var department  : String
//        lateinit  var noticePeriod  : String
//        lateinit var GapInEduCi  : String
//        lateinit var currentCTC  : String
//        lateinit var expectedCTC  : String
        var qualification :String = String()
        var board_university :String = String()
        var passingYear :String = String()
        var percentage :String = String()
        var skill1 :String = String()
        var skill2 :String =String()
        var skill3 :String = String()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_info)

        companyName_text = findViewById(R.id.companyName_text)
        current_desig_text = findViewById(R.id.current_desig_text)
        jobtype_text = findViewById(R.id.jobtype_text)
        employment_type_text = findViewById(R.id.employment_type_text)
        totalexp_text = findViewById(R.id.totalexp_text)
        Department_text = findViewById(R.id.Department_text)
        noticePeriod_text = findViewById(R.id.noticePeriod_text)
        gap_in_workExp = findViewById(R.id.gap_in_workExp)
        currentCTC_text = findViewById(R.id.currentCTC_text)
        ExpectedCTC_text = findViewById(R.id.ExpectedCTC_text)
        companyinfo_layout = findViewById(R.id.companyinfo_layout)
        back_Arrow_2 = findViewById(R.id.back_Arrow_2)
        editbutton_2 = findViewById(R.id.editbutton_2)
        previous_button = findViewById(R.id.previous_button)
        updatebutton = findViewById(R.id.updatebutton)
        next_button = findViewById(R.id.next_button)

        val bundle = intent.extras
        if (bundle != null) {
            fName = bundle.getString(KeyClass.KEY_FIRST_NAMEPI, fName)
            lName = bundle.getString(KeyClass.KEY_LAST_NAMEPI, lName)
            password = bundle.getString(KeyClass.KEY_PASSWORDPI,password)
            email = bundle.getString(KeyClass.KEY_EMAIL, email)
            mobileNo = bundle.getString(KeyClass.KEY_MOBILENO_PI, mobileNo)
            address = bundle.getString(KeyClass.KEY_ADDRESS, address)
            gender = bundle.getString(KeyClass.KEY_GENDER, gender)
            city = bundle.getString(KeyClass.KEY_CITY, city)
            state = bundle.getString(KeyClass.KEY_STATE,state)
            country = bundle.getString(KeyClass.KEY_COUNTRY, country)
            dateOfBirth = bundle.getString(KeyClass.KEY_BIRTH_DATE, dateOfBirth)
            gapInedu = bundle.getString(KeyClass.KEY_GAP_IN_EDU_PI, gapInedu)
            knownLanguage = bundle.getString(KeyClass.KEY_KNOWN_LANGUAGES, knownLanguage)
            ProfileImage = bundle.getString(KeyClass.KEY_PROFILE_IMAGE, ProfileImage)
            uploadResume = bundle.getString(KeyClass.KEY_RESUME_UPLOAD, uploadResume)

        }
//        val bundle : Bundle = intent.extras!!


        back_Arrow_2.setOnClickListener {

            onBackPressed()

        }
        previous_button.setOnClickListener {
            val intent = Intent(this, PersonalInfo::class.java)
            startActivity(intent)

        }

//        companyinfo_layout.alpha = 0.5f

        editbutton_2.setOnClickListener {
//            companyinfo_layout.alpha = 1.0f

            companyinfo_layout.isFocusable = true
            companyName_text.isEnabled= true
            current_desig_text.isEnabled = true
            jobtype_text.isEnabled = true
            employment_type_text.isEnabled = true
            totalexp_text.isEnabled = true
            Department_text.isEnabled = true
            noticePeriod_text.isEnabled = true
            gap_in_workExp.isEnabled = true
            currentCTC_text.isEnabled = true
            ExpectedCTC_text.isEnabled = true
            next_button.isEnabled = true

        }
        next_button.setOnClickListener {

            val companyNameCI = companyName_text.getText().toString().trim()
           val currentDesignination = current_desig_text.getText().toString().trim()
            val jobTypeCI = jobtype_text.getText().toString().trim()
            val employmentTypeCI = employment_type_text.getText().toString().trim()
           val totalExp = totalexp_text.getText().toString().trim()
            val department = Department_text.getText().toString().trim()
           val noticePeriod = noticePeriod_text.getText().toString().trim()
            val gapinWorkExp = gap_in_workExp.getText().toString().trim()
            val currentCTC = currentCTC_text.getText().toString().trim()
            val expectedCTC = ExpectedCTC_text.getText().toString().trim()


            val bundle = Bundle()
            bundle.putString(KeyClass.KEY_COMPANY_NAMECI,companyNameCI)
            bundle.putString(KeyClass.KEY_CURRENT_DESIGNINATION,currentDesignination)
            bundle.putString(KeyClass.KEY_JOBTYPE_CI,jobTypeCI)
            bundle.putString(KeyClass.KEY_EMPLOYMENTTYPE_CI,employmentTypeCI)
            bundle.putString(KeyClass.KEY_TOTAL_EXPIRENCE_CI,totalExp)
            bundle.putString(KeyClass.KEY_DEPARTMENT,department)
            bundle.putString(KeyClass.KEY_NOTICE_PERIOD_CI,noticePeriod)
            bundle.putString(KeyClass.KEY_WORK_EXPRIENCE,gapinWorkExp)
            bundle.putString(KeyClass.KEY_CURRENTCTC_CI,currentCTC)
            bundle.putString(KeyClass.KEY_EXPECTEDCTC_CI,expectedCTC)
            // Personalinfodata
            bundle.putString(KeyClass.KEY_FIRST_NAMEPI, fName)
            bundle.putString(KeyClass.KEY_LAST_NAMEPI, lName)
            bundle.putString(KeyClass.KEY_EMAIL, email)
            bundle.putString(KeyClass.KEY_PASSWORDPI, password)
            bundle.putString(KeyClass.KEY_MOBILENO_PI, mobileNo)
            bundle.putString(KeyClass.KEY_ADDRESS, address)
            bundle.putString(KeyClass.KEY_CITY, city)
            bundle.putString(KeyClass.KEY_STATE, state)
            bundle.putString(KeyClass.KEY_COUNTRY, country)
            bundle.putString(KeyClass.KEY_BIRTH_DATE, dateOfBirth)
            bundle.putString(KeyClass.KEY_GAP_IN_EDU_PI, gapInedu)
            bundle.putString(KeyClass.KEY_GENDER, gender)
            bundle.putString(KeyClass.KEY_KNOWN_LANGUAGES, knownLanguage)
            bundle.putString(KeyClass.KEY_RESUME_UPLOAD, uploadResume)
            bundle.putString(KeyClass.KEY_PROFILE_IMAGE,ProfileImage)

            val intent = Intent(this, EducationInfo::class.java)
//            intent.putExtra(Constant.KEY_COMPANY_NAMECI,companyNameCI)
//           intent.putExtra(Constant.KEY_CURRENT_DESIGNINATION,currentDesignination)
//           intent.putExtra(Constant.KEY_JOBTYPE_CI,jobTypeCI)
//           intent.putExtra(Constant.KEY_EMPLOYMENTTYPE_CI,employmentTypeCI)
//           intent.putExtra(Constant.KEY_TOTAL_EXPIRENCE_CI,totalExp)
//            intent.putExtra(Constant.KEY_DEPARTMENT,department)
//            intent.putExtra(Constant.KEY_NOTICE_PERIOD_CI,noticePeriod)
//           intent.putExtra(Constant.KEY_WORK_EXPRIENCE,gapinWorkExp)
//            intent.putExtra(Constant.KEY_CURRENTCTC_CI,currentCTC)
//            intent.putExtra(Constant.KEY_EXPECTEDCTC_CI,expectedCTC)
//            intent.putExtra(Constant.KEY_FIRST_NAMEPI, firstNamePI)
//            intent.putExtra(Constant.KEY_LAST_NAMEPI, lastNamePI)
//            intent.putExtra(Constant.KEY_PASSWORDPI, passwordPI)
//            intent.putExtra(Constant.KEY_EMAIL, emailPI)
//            intent.putExtra(Constant.KEY_MOBILENO_PI, mobileNo)
//            intent.putExtra(Constant.KEY_CITY, city)
//            intent.putExtra(Constant.KEY_STATE, state)
//            intent.putExtra(Constant.KEY_COUNTRY, country)
//            intent.putExtra(Constant.KEY_KNOWN_LANGUAGES, knownLanguage)
//            intent.putExtra(Constant.KEY_GAP_IN_EDU_PI, gapInedu)
//            intent.putExtra(Constant.KEY_BIRTH_DATE, dateOfBirth)
//            intent.putExtra(Constant.KEY_PROFILE_IMAGE, ProfileImage)
//            intent.putExtra(Constant.KEY_RESUME_UPLOAD, uploadResume)

           startActivity(intent)
            ValidationCompany()

        }
        updatebutton.setOnClickListener {
            companyNameCI = companyName_text.text.toString()
            currentDesignination = current_desig_text.text.toString()
            jobTypwCI =jobtype_text.text.toString()
            employmentTypeCI =employment_type_text.text.toString()
            noticePeriod =noticePeriod_text.text.toString()
            department = Department_text.text.toString()
            currentCTC =currentCTC_text.text.toString()
            expectedCTC = ExpectedCTC_text.text.toString()
            totalExp =totalexp_text.text.toString()
            GapinWorkExp =gap_in_workExp.text.toString()
            qualification =""
            board_university =""
            passingYear =""
            percentage =""
            updateProfile()
        }
        displayUser()
    }

    fun ValidationCompany(){
        if (companyName_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Company_Name_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (current_desig_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Current_Designation_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (jobtype_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Job_Type_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (employment_type_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Employment_Type_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (totalexp_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Total_Expirence_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (Department_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Department_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (noticePeriod_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Notice_Period_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (currentCTC_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Current_CTC_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (ExpectedCTC_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Expected_CTC_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    
    fun displayUser(){

        RetrofitBuilder.JsonServices.jsonInstance.displayProfile(128).enqueue(object :
            Callback<DisplayUser?> {
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
                Log.d("TAG", "Display User " + response.body()!!.Data)

                try {
                    val userDetail = response.body()
//                    if (user1 == null) {
//                        editbutton_2.isVisible = true
//                    } else {
//                        editbutton_2.isVisible = false
//                    }
                    if (userDetail !=null&& response.code() == 200){
                        companyName_text.setText( userDetail.Data.Companyname)
                        current_desig_text.setText( userDetail.Data.Designation)
                        jobtype_text.setText( userDetail.Data.Jobtype)
                        employment_type_text.setText(userDetail.Data.EmploymentType)
                        totalexp_text.setText(userDetail.Data.TotalExp)
                        Department_text.setText(userDetail.Data.Department)
                        noticePeriod_text.setText(userDetail.Data.Noticeperiod)
                        gap_in_workExp.setText(userDetail.Data.GapInExp)
                        currentCTC_text.setText(userDetail.Data.Current_CTC.toString())
                        ExpectedCTC_text.setText(userDetail.Data.Expected_CTC.toString())

                        if (userDetail.Data != null) {
                            editbutton_2.visibility = View.INVISIBLE
                            updatebutton.visibility = View.VISIBLE
                        }else{
                            editbutton_2.visibility = View.VISIBLE
                            next_button.visibility = View.VISIBLE
                            previous_button.visibility = View.VISIBLE
                        }
//                        intent.putExtra("LastNamePI",lastNamePI)
//                        intent.putExtra("Email",emailPI)
//                        intent.putExtra("Password",passwordPI)
//                        intent.putExtra("Mobile No",mobileNo)
//                        intent.putExtra("Address",addressPI)
//                        intent.putExtra("City",city)
//                        intent.putExtra("State",state)
//                        intent.putExtra("Country",country)
//                        intent.putExtra("gapInEducation",gapInEdu)
//                        intent.putExtra("uploadResumePI",uploadResume)
//                        intent.putExtra("BirthDate",birthDate)
//                        intent.putExtra("knowonLanguage",knowNlanguages)
//                        intent.putExtra("ProfileImage",profileImage)
                        }

                }catch (e: JSONException){
                    e.printStackTrace()

                }
            }

            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {
                Toast.makeText(
                    this@CompanyInfo,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun updateProfile(){
//        Log.d("Update_Image",EducationInfo.ProfileImage)
        RetrofitBuilder.JsonServices.jsonInstance.updateProfile(  RequestParameters().userProfileUpdate(128,personalinfo = PersonalInfoData(
       fName, lName, email, password, mobileNo, city, state, country, address,
            knownLanguage, companyNameCI, currentDesignination, jobTypwCI, employmentTypeCI, department,
            expectedCTC, currentCTC, passingYear, qualification, percentage, skill1,skill2,skill3,
            uploadResume, ProfileImage, gapInedu, board_university, totalExp,
            gapInedu, percentage ))).enqueue(object : Callback<DisplayUser?> {
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
                try {
                    val update = response.body()
                    if (update != null){

//                        companyName_text.setText(update.Data.Companyname)
//                        current_desig_text.setText(update.Data.Designation)
//                        jobtype_text.setText(update.Data.Jobtype)
//                        employment_type_text.setText(update.Data.EmploymentType)
//                        noticePeriod_text.setText(update.Data.Noticeperiod)
//                        Department_text.setText(update.Data.Department)
//                        gap_in_workExp.setText(update.Data.GapInExp)
//                        totalexp_text.setText(update.Data.TotalExp)
//                        currentCTC_text.setText(update.Data.Current_CTC.toString())
//                        ExpectedCTC_text.setText(update.Data.Expected_CTC.toString())

                    //    editbutton_2.visibility = View.VISIBLE
                        updatebutton.alpha = 0.7f
                        editbutton_2.visibility = View.VISIBLE
                        updatebutton.alpha = 1.0f

                        Toast.makeText(getApplicationContext(),"Data Updated  successfully", Toast.LENGTH_SHORT).show()

//                        if (update != null) {
//
//                            updatebutton.visibility = View.VISIBLE
//                            Toast.makeText(applicationContext, "visible", Toast.LENGTH_SHORT).show()
//                        }
//                        else {
//                            next_button.visibility = View.VISIBLE
//                            previous_button.visibility = View.VISIBLE
//                            Toast.makeText(applicationContext, "Visible", Toast.LENGTH_SHORT).show()
//                        }
                    }

                }catch (e : JSONException){
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {

                Log.d("TAG", " Got Error " + t.localizedMessage)
            }
        })
    }
}