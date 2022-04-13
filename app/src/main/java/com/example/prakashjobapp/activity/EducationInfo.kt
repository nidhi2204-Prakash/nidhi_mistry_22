package com.example.prakashjobapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.prakashjobapp.R
import com.example.prakashjobapp.api.KeyClass
import com.example.prakashjobapp.api.RequestParameters
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.DisplayUser
import com.example.prakashjobapp.models.InserUserData
import com.example.prakashjobapp.models.PersonalInfoData
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EducationInfo : AppCompatActivity() {
    lateinit var backarrow_edit :ImageView
    lateinit var editbutton :ImageView
    lateinit var qualification_text :EditText
    lateinit var board_university_text :EditText
    lateinit var passing_year_text :EditText
    lateinit var percent_text :EditText
    lateinit var EducationInfo_Layout :LinearLayout
    lateinit var linear_button :LinearLayout
    lateinit var submit_button :Button
    lateinit var update_button :AppCompatButton
    lateinit var previous_button_edu : AppCompatButton

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
            lateinit var profile_photo :String
            lateinit var resume_upload :String
            lateinit  var companyNameCI :String
            lateinit  var currentDesignination  :String
            lateinit  var jobTypwCI : String
            lateinit  var employmentTypeCI  : String
            lateinit  var totalExp  : String
            lateinit  var department  : String
            lateinit  var noticePeriod  : String
            lateinit  var GapinWorkExp  : String
            lateinit  var currentCTC  : String
            lateinit  var expectedCTC  : String
            lateinit  var qualification :String
            lateinit  var board_university :String
            lateinit  var passingYear :String
            lateinit  var percentage :String
            lateinit  var skill1 :String
            lateinit  var skill2 :String
            lateinit  var skill3 :String
        //     lateinit var firstNamePI: String
//            lateinit  var lastNamePI :String
//            lateinit   var passsword :String
//            lateinit   var emailPI  :String
//            lateinit    var mobileNo :String
//            lateinit   var address  :String
//            lateinit   var city :String
//            lateinit    var state :String
//            lateinit    var country :String
//            lateinit   var dateOfBirth :String
//            lateinit   var gapInedu :String
//            lateinit   var gender :String
//            lateinit   var knownLanguage:String
//            lateinit   var ProfileImage :String
//            lateinit   var uploadResume :String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education_info)

        update_button = findViewById(R.id.update_button)
        previous_button_edu = findViewById(R.id.previous_button_edu)
        backarrow_edit = findViewById(R.id.backarrow_edit)
        submit_button = findViewById(R.id.submit_button)
        editbutton = findViewById(R.id.editbutton)
        qualification_text = findViewById(R.id.qualification_text)
        board_university_text = findViewById(R.id.board_university_text)
        passing_year_text = findViewById(R.id.passing_year_text)
        percent_text = findViewById(R.id.percent_text)
        EducationInfo_Layout = findViewById(R.id.EducationInfo_Layout)
        linear_button = findViewById(R.id.linear_button)


        val bundle = intent.extras
        if (bundle != null ){
            fName = bundle.getString(KeyClass.KEY_FIRST_NAMEPI)!!
            lName = bundle.getString(KeyClass.KEY_LAST_NAMEPI)!!
            password = bundle.getString(KeyClass.KEY_PASSWORDPI)!!
            email = bundle.getString(KeyClass.KEY_EMAIL)!!
            mobileNo = bundle.getString(KeyClass.KEY_MOBILENO_PI)!!
            address = bundle.getString(KeyClass.KEY_ADDRESS)!!
            gender = bundle.getString(KeyClass.KEY_GENDER)!!
            city = bundle.getString(KeyClass.KEY_CITY)!!
            state = bundle.getString(KeyClass.KEY_STATE)!!
            country = bundle.getString(KeyClass.KEY_COUNTRY)!!
            dateOfBirth = bundle.getString(KeyClass.KEY_BIRTH_DATE)!!
            gapInedu = bundle.getString(KeyClass.KEY_GAP_IN_EDU_PI)!!
            knownLanguage = bundle.getString(KeyClass.KEY_KNOWN_LANGUAGES)!!
            ProfileImage = bundle.getString(KeyClass.KEY_PROFILE_IMAGE)!!
            uploadResume = bundle.getString(KeyClass.KEY_RESUME_UPLOAD)!!
            companyNameCI = bundle.getString(KeyClass.KEY_COMPANY_NAMECI)!!
            currentDesignination = bundle.getString(KeyClass.KEY_CURRENT_DESIGNINATION)!!
            jobTypwCI = bundle.getString(KeyClass.KEY_JOBTYPE_CI)!!
            employmentTypeCI = bundle.getString(KeyClass.KEY_EMPLOYMENTTYPE_CI)!!
            totalExp = bundle.getString(KeyClass.KEY_TOTAL_EXPIRENCE_CI)!!
            department = bundle.getString(KeyClass.KEY_DEPARTMENT)!!
            noticePeriod = bundle.getString(KeyClass.KEY_NOTICE_PERIOD_CI)!!
            GapinWorkExp = bundle.getString(KeyClass.KEY_WORK_EXPRIENCE)!!
            currentCTC = bundle.getString(KeyClass.KEY_CURRENTCTC_CI)!!
            expectedCTC = bundle.getString(KeyClass.KEY_EXPECTEDCTC_CI)!!

        }


        backarrow_edit.setOnClickListener {
            onBackPressed()
        }

        previous_button_edu.setOnClickListener {
            val intent = Intent(this, CompanyInfo::class.java)
            startActivity(intent)
        }

//           EducationInfo_Layout.alpha = 0.5f
        editbutton.setOnClickListener {

            EducationInfo_Layout.alpha = 1.0f
            EducationInfo_Layout.isFocusable = true
            qualification_text.isEnabled = true
            board_university_text.isEnabled = true
            passing_year_text.isEnabled = true
            percent_text.isEnabled = true

        }
        submit_button.setOnClickListener {

            ValidEducation()
            userInsert()
        }
        update_button.setOnClickListener {

            qualification =qualification_text.text.toString()
            board_university =board_university_text.text.toString()
            passingYear =passing_year_text.text.toString()
            percentage =percent_text.text.toString()
            updateProfile()
        }

     //   displayUserProfile()
//        insertUser()
    }
    override fun onStart() {
        super.onStart()
        displayUserProfile()

        Toast.makeText(applicationContext, "Now onStart() calls", Toast.LENGTH_LONG).show() //onStart Called
    }
        fun ValidEducation(){

            if (qualification_text.getText().toString().isEmpty()){
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.Qualification_should_not_be_emptied),
                    Toast.LENGTH_SHORT
                ).show()
            }

            else if (board_university_text.getText().toString().isEmpty()){
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.University_should_not_be_emptied),
                    Toast.LENGTH_SHORT
                ).show()
            }

            else if (passing_year_text.getText().toString().isEmpty()){
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.Passing_year_should_not_be_emptied),
                    Toast.LENGTH_SHORT
                ).show()
            }

            else if (percent_text.getText().toString().isEmpty()){
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.Percentage_should_not_be_emptied),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

//    fun insertUser() {
//        RetrofitBuilder.JsonServices.jsonInstance.profileSubmit(RequestParameters().userProfile(128,personalinfo =  PersonalInfoData(
//            firstNamePI, lastNamePI, emailPI, passsword, mobileNo, dateOfBirth, address, city, state,
//            country, gapInedu, gender, uploadResume, knownLanguage, companyNameCI,
//            currentDesignination, jobTypwCI, employmentTypeCI, noticePeriod, department, currentCTC,
//            expectedCTC, ProfileImage, skill1, skill2,skill3, qualification, board_university,
//            passingYear, percentage))).enqueue(object : Callback<InserUserData?> {
//            override fun onResponse(call: Call<InserUserData?>, response: Response<InserUserData?>) {
//                try {
//                    val addUserdetail = response.body()
//                    if (addUserdetail != null){
//
//                        editbutton.visibility = View.VISIBLE
//
//                        Toast.makeText(getApplicationContext(),"Data inserted successfully", Toast.LENGTH_SHORT).show()
//                        startActivity(Intent(this@EducationInfo ,DashboardActivity::class.java))
//                        startActivity(intent)
//
//                    }
//
//                }catch (e : JSONException){
//                    e.printStackTrace()
//                }
//            }
//
//            override fun onFailure(call: Call<InserUserData?>, t: Throwable) {
//                Log.d("TAG", " Got Error " + t.localizedMessage)
//
//            }
//        })
//    }

    //InserUserProfile
    fun userInsert(){
        RetrofitBuilder.JsonServices.jsonInstance.profileSubmit( profile_image = profile_photo, resume = resume_upload, RequestParameters().userProfileSubmit(Firstname = fName,Lastname = lName,
            Email = email, Password = password, DOB = dateOfBirth, address = address, city = city, state = state, country = country,
            knownLanguages = knownLanguage, GapinEdu = gapInedu, Gender = gender, companyName = companyNameCI, currentDesignation = currentDesignination
        , jobType = jobTypwCI, employmentType = employmentTypeCI, totalExpirence = totalExp, department = department, noticePeriod = noticePeriod,
            currentCTC = currentCTC, expectedCTC = expectedCTC, gapinWorkExp = GapinWorkExp, qualification = qualification, percentage = percentage,
            boardUniversity = board_university, mobileNo = mobileNo, skills = skill1, passingYear = passingYear,  user_id = 128
        )).enqueue(object : Callback<InserUserData?> { override fun onResponse(
                call: Call<InserUserData?>,
                response: Response<InserUserData?>,
            ) {
                    try {
                        if (response.body()?.Status?.equals("200") == true) {
                            startActivity(Intent(this@EducationInfo, DashboardActivity::class.java))
                            finish()
                        } else if (response.body()?.Status?.equals("201") == true) {
                            Toast.makeText(
                                this@EducationInfo,
                                response.body()?.Message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }else {
                            Toast.makeText(
                                this@EducationInfo,
                                "Try Again !",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
//                        val insertUser = response.body()
//                        if (insertUser !== null){
//
//                            startActivity(Intent(this@EducationInfo ,DashboardActivity::class.java))
//                        }
                    }catch (e :JSONException){
                        e.printStackTrace()
                    }
            }

            override fun onFailure(call: Call<InserUserData?>, t: Throwable) {

                Log.d("TAG", " Got Error " + t.localizedMessage)
            }
        })
    }

    fun displayUserProfile(){
        RetrofitBuilder.JsonServices.jsonInstance.displayProfile(128).enqueue(object :
            Callback<DisplayUser?> {
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
                Log.d("TAG", "Display User " + response.body()!!.Data)

                try {
                    val user1 = response.body()
//                    if (user1 == null) {
//                        EducationInfo_Layout.alpha = 0.5f
//                        editbutton.isVisible = true
//                        EducationInfo_Layout.alpha = 0.0f
//                  } else {
//                        editbutton.isVisible = false
//                   }
                    if (user1 !=null&& response.code() == 200){

                        qualification_text.setText(user1.Data.Qualification)
                        board_university_text.setText(user1.Data.Board_University)
                        passing_year_text.setText(user1.Data.PassingYear.toString())
                        percent_text.setText(user1.Data.Percentage.toString())

                        if (user1 != null){
                            editbutton.visibility = View.INVISIBLE
                            update_button.visibility = View.VISIBLE
                            Toast.makeText(applicationContext, "Visible", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            editbutton.visibility = View.VISIBLE
                            submit_button.visibility = View.VISIBLE
                            previous_button_edu.visibility = View.VISIBLE
                            Toast.makeText(applicationContext, "Visible", Toast.LENGTH_SHORT).show()
                        }

//                        intent.putExtra(Constant.KEY_FIRST_NAMEPI,user1.Data.Firstname)
//                    intent.putExtra(Constant.KEY_LAST_NAMEPI,user1.Data.Lastname)
//                       intent.putExtra(Constant.KEY_EMAIL,user1.Data.Emailid)
//                       intent.putExtra(Constant.KEY_PASSWORDPI,user1.Data.Password)
//                       intent.putExtra(Constant.KEY_MOBILENO_PI,user1.Data.Mobileno)
//                       intent.putExtra(Constant.KEY_ADDRESS,user1.Data.Address)
//                       intent.putExtra(Constant.KEY_CITY,user1.Data.City)
//                       intent.putExtra(Constant.KEY_STATE,user1.Data.State)
//                      intent.putExtra(Constant.KEY_COUNTRY,user1.Data.Country)
//                        intent.putExtra(Constant.KEY_GAP_IN_EDU_PI,user1.Data.EducationGap)
//                       intent.putExtra(Constant.KEY_RESUME_UPLOAD,user1.Data.ResumeUpload)
//                       intent.putExtra(Constant.KEY_BIRTH_DATE,user1.Data.DateOfBirth)
//                      intent.putExtra(Constant.KEY_KNOWN_LANGUAGES,user1.Data.Languages)
//                       intent.putExtra(Constant.KEY_PROFILE_IMAGE,user1.Data.ProfilePhoto)
//
//                       startActivity(intent)
                    }

//                    if (user1 != null) {
//                        if(user1.Data == null){
//                            submit_button.visibility = View.VISIBLE
//
//                        }
//                        update_button.visibility = View.VISIBLE
//                    }

                }catch (e: JSONException){
                    e.printStackTrace()

                }
            }
            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {

                Log.d("TAG", " Got Error " + t.localizedMessage)

            }
        })
    }

    fun updateProfile(){
        Log.d("Update_Image",profile_photo)
        RetrofitBuilder.JsonServices.jsonInstance.updateProfile(RequestParameters().userProfileUpdate(128,personalinfo = PersonalInfoData(
            fName, lName, email, password, mobileNo, city, state, country, address,
            knownLanguage, companyNameCI, currentDesignination, jobTypwCI, employmentTypeCI, department,
            expectedCTC, currentCTC, passingYear, qualification, percentage, skill1, skill2, skill3,
            uploadResume, ProfileImage, gapInedu, board_university, totalExp,
            gapInedu, percentage ))).enqueue(object : Callback<DisplayUser?> {
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
                try {
                     val userUpdate = response . body()
                    if (userUpdate != null && response.code() == 200 ) {
                        System.out.println("Jinal Nidhi")
//                        passing_year_text.setText(userUpdate.Data.PassingYear.toString())
//                        qualification_text.setText(userUpdate.Data.Qualification)
//                        percent_text.setText(userUpdate.Data.Percentage.toString())
//                        board_university_text.setText(userUpdate.Data.Board_University)
//                        editbutton.visibility = View.INVISIBLE
//                        Toast.makeText(getApplicationContext(),"Data inserted successfully", Toast.LENGTH_SHORT).show()

                        update_button.alpha = 0.7f
                        editbutton.visibility = View.VISIBLE
                        update_button.alpha = 1.0f

                        Toast.makeText(getApplicationContext(),"Data Updated  successfully", Toast.LENGTH_SHORT).show()

//                        startActivity(Intent(this@EducationInfo ,EducationInfo::class.java))
//                        startActivity(intent)
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
