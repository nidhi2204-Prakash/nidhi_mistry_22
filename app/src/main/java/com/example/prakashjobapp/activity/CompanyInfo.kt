package com.example.prakashjobapp.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isInvisible
import com.example.prakashjobapp.R
import com.example.prakashjobapp.SessionManager
import com.example.prakashjobapp.api.KeyClass
import com.example.prakashjobapp.api.RequestParameters
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.*
import com.google.gson.Gson
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
    lateinit var updatebutton :FrameLayout
    lateinit var next_button :AppCompatButton
    lateinit var update_textView : TextView
    lateinit var prg_bar :ProgressBar
    private lateinit var sessionManager: SessionManager
    private lateinit var objPersonalInfoData: PersonalInfoData
    private lateinit var objCompanyInfoData: CompanyInfoData
    private lateinit var objEducationInfoData: EducationInfoData


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
        fName = String()
        lName = String()
        email = String()
        password = String()
        mobileNo = String()
        address = String()
        city = String()
        state = String()
        country = String()
        dateOfBirth  = String()
        gapInedu  = String()
        gender  = String()
        knownLanguage = String()
        ProfileImage  = String()
         uploadResume =  String()
         qualification  = String()
         board_university  = String()
         passingYear  = String()
         percentage  = String()
         skill1  = String()
         skill2  = String()
         skill3  = String()

        prg_bar = findViewById(R.id.prg_bar)
        update_textView = findViewById(R.id.update_textview)
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

        }
//        val bundle : Bundle = intent.extras!!
        var objPersonalInfoData = bundle?.getParcelable<PersonalInfoData>(KeyClass.PERSONAL_INFO_DATA)

        back_Arrow_2.setOnClickListener {
            if (editbutton_2.isInvisible ){
                onBackPressed()
            }else{
                AlertDialog()
            }
        }

        previous_button.setOnClickListener {
            val intent = Intent(this, PersonalInfo::class.java)
            startActivity(intent)

        }

//       companyinfo_layout.alpha = 0.5f

//        editbutton_2.setOnClickListener {
//            companyinfo_layout.alpha = 1.0f
//
//            companyinfo_layout.isFocusable = true
//            companyName_text.isEnabled= true
//            current_desig_text.isEnabled = true
//            jobtype_text.isEnabled = true
//            employment_type_text.isEnabled = true
//            totalexp_text.isEnabled = true
//            Department_text.isEnabled = true
//            noticePeriod_text.isEnabled = true
//            gap_in_workExp.isEnabled = true
//            currentCTC_text.isEnabled = true
//            ExpectedCTC_text.isEnabled = true
//            next_button.isEnabled = true
//
//        }
        next_button.setOnClickListener {

           //            if(validationCompanyInfo()){
//                val companyNameCI = etCompanyNameCI.text.toString()
//                val currentDesignationCI = etCurrentDesCI.text.toString()
//                val jobTypeCI = etJobTypeCI.text.toString()
//                val employmentTypeCI = etEmploymentTypeCI.text.toString()
//                val totalExperienceCI = etTotalExp.text.toString()
//                val departmentCI = etDepartmentCI.text.toString()
//                val noticePeriodCI = etNoticePeriodCI.text.toString()
//                val gapInWorkExperience = etGapWorkExpCI.text.toString()
//                val currentCTCCI = etCurrentCTCCI.text.toString()
//                val expectedCTCCI = expectedCTC.text.toString()
//
//                val objPersonalInfoData = bundle.getParcelable<PersonalInfoData>(KeyClass.KEY_PERSONAL_INFO_DATA)
//                objCompanyInfoData = CompanyInfoData(
//                    companyName = companyNameCI,
//                    currentDesignation = currentDesignationCI,
//                    jobType = jobTypeCI,
//                    employmentType = employmentTypeCI,
//                    totalExp = totalExperienceCI,
//                    department = departmentCI,
//                    noticePeriod = noticePeriodCI,
//                    gapInWorkExpirence = gapInWorkExperience,
//                    currentCTC = currentCTCCI,
//                    expectedCTC = expectedCTCCI
//                )

                val companyNameCI = companyName_text.text.toString()
                val currentDesignination = current_desig_text.text.toString()
                val jobTypeCI = jobtype_text.text.toString()
                val employmentTypeCI = employment_type_text.text.toString()
                val totalExp = totalexp_text.text.toString()
                val department = Department_text.text.toString()
                val noticePeriod = noticePeriod_text.text.toString()
                val gapinWorkExp = gap_in_workExp.text.toString()
                val currentCTC = currentCTC_text.text.toString()
                val expectedCTC = ExpectedCTC_text.text.toString()
                val objCompanyInfoData = CompanyInfoData(
                    companyName = companyNameCI,
                    currentDesignation = currentDesignination,
                    jobType = jobTypeCI,
                    employmentType = employmentTypeCI,
                    totalExp = totalExp,
                    department = department,
                   noticePeriod = noticePeriod,
                    gapInWorkExpirence = gapinWorkExp,
                    currentCTC = currentCTC,
                    expectedCTC = expectedCTC
                )
            val gsonG = Gson()
            var jsonString = gsonG.toJson(objCompanyInfoData)
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
                bundle.putString(KeyClass.KEY_PROFILE_IMAGE, ProfileImage)
             val gson = Gson()
             val objPersonalInfoData  = gson.fromJson(KeyClass.PERSONAL_INFO_DATA, objPersonalInfoData!!::class.java)

//            val objPersonalInfoData = bundle.getParcelable<PersonalInfoData>(KeyClass.PERSONAL_INFO_DATA)
                val intent = Intent(this, EducationInfo::class.java)
                intent.putExtras(bundle)
                intent.putExtra(KeyClass.PERSONAL_INFO_DATA,objPersonalInfoData)
                intent.putExtra(KeyClass.COMPANY_INFO_DATA,jsonString)
                startActivity(intent)
                ValidationCompany()

        }
        updatebutton.setOnClickListener {

            prg_bar .visibility = View.VISIBLE
            update_textView.visibility = View.GONE
//            objPersonalInfoData = PersonalInfoData(
//                ProfileImage, fName, lName, email, password, mobileNo, dateOfBirth, address, city, state, country, gapInedu, gender, knownLanguage, skill1, uploadResume)
            objPersonalInfoData = PersonalInfoData(profilePhoto = EducationInfo.ProfileImage, firstName = EducationInfo.fName
                , lastName = EducationInfo.lName, email = EducationInfo.email, password = EducationInfo.password, mobileNo = EducationInfo.mobileNo, dateOfBirth = EducationInfo.dateOfBirth, address = EducationInfo.address, city = EducationInfo.city, state = EducationInfo.state, country = EducationInfo.country, gapInEducation = EducationInfo.gapInedu, gender = EducationInfo.gender, knownlanguage = EducationInfo.knownLanguage, skills = EducationInfo.skill1,
                resume = EducationInfo.uploadResume)
            objCompanyInfoData = CompanyInfoData(companyName = EducationInfo.companyNameCI, currentDesignation = EducationInfo.currentDesignination, jobType = JobDescription.jobType, employmentType = JobDescription.employmentType, totalExp = EducationInfo.totalExp, department = EducationInfo.department, noticePeriod = EducationInfo.noticePeriod, gapInWorkExpirence = EducationInfo.GapinWorkExp,
                currentCTC = EducationInfo.currentCTC, expectedCTC = EducationInfo.expectedCTC)

            updateProfile()
//           companyNameCI = companyName_text.text.toString()
//            currentDesignination = current_desig_text.text.toString()
//            jobTypwCI =jobtype_text.text.toString()
//            employmentTypeCI =employment_type_text.text.toString()
//            noticePeriod =noticePeriod_text.text.toString()
//            department = Department_text.text.toString()
//            currentCTC =currentCTC_text.text.toString()
//            expectedCTC = ExpectedCTC_text.text.toString()
//            totalExp =totalexp_text.text.toString()
//            GapinWorkExp =gap_in_workExp.text.toString()

        }
        displayUser()
    }
    fun fieldEnableCI(){
        companyinfo_layout.alpha = 0.5f

        editbutton_2.setOnClickListener {
            companyinfo_layout.alpha = 1.0f


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
    }
    fun ValidationCompany()  {
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
        sessionManager = SessionManager(this)
        val id: String? = sessionManager.getString(SessionManager.KEY_ID)
        val id1 : Int = id!!.toInt()
        RetrofitBuilder.JsonServices.jsonInstance.displayProfile(id1).enqueue(object :
            Callback<DisplayUser?> {
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
                Log.d("TAG", "Display User " + response.body()!!.Data)

                try {
                    val userDetail = response.body()

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

                        if (userDetail.Data.DateOfBirth != null ) {
                            fieldEnableCI()
                            editbutton_2.visibility = View.VISIBLE
                            updatebutton.visibility = View.VISIBLE
                        }else{
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
                            //buttons visiblle
                            editbutton_2.visibility = View.INVISIBLE
                            next_button.visibility = View.VISIBLE
                            previous_button.visibility = View.VISIBLE

                        }
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
        var personalInfoData: PersonalInfoData =
            PersonalInfoData("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
        var companyInfoData: CompanyInfoData =
            CompanyInfoData("", "", "", "", "", "", "", "", "", "")
        var educationInfoData: EducationInfoData = EducationInfoData("", "", "", "")
        val    companyNameCI = companyName_text.text.toString()
        val    currentDesignination = current_desig_text.text.toString()
        val    jobTypwCI =jobtype_text.text.toString()
        val   employmentTypeCI =employment_type_text.text.toString()
        val   noticePeriod =noticePeriod_text.text.toString()
        val   department = Department_text.text.toString()
        val   currentCTC =currentCTC_text.text.toString()
        val   expectedCTC = ExpectedCTC_text.text.toString()
        val   totalExp =totalexp_text.text.toString()
        val   GapinWorkExp =gap_in_workExp.text.toString()

        companyInfoData = CompanyInfoData(
            companyName = companyNameCI, currentDesignation = currentDesignination,
            jobType = jobTypwCI, employmentType = employmentTypeCI, noticePeriod = noticePeriod,
            department = department, currentCTC = currentCTC,
            expectedCTC = expectedCTC, totalExp = totalExp, gapInWorkExpirence = GapinWorkExp
        )

        val objPersonalInfoData = PersonalInfoData(
            profilePhoto = ProfileImage,
            firstName = fName,
            lastName = lName,
            email = email,
            password = password,
            mobileNo = mobileNo,
            gender = EducationInfo.gender,
            dateOfBirth = dateOfBirth,
            address = address,
            city = city,
            state = state,
            country = country,
            gapInEducation = gapInedu,
            knownlanguage = knownLanguage,
            skills = skill1,
            resume = uploadResume
        )
        personalInfoData = objPersonalInfoData
        val objEducationInfoData = EducationInfoData(
         Qualification = qualification,
         boardUniversity = board_university,
         passingYear = passingYear,
         percentage = percentage
        )
      educationInfoData = objEducationInfoData
        sessionManager = SessionManager(this)
        val id: String? = sessionManager.getString(SessionManager.KEY_ID)
        val id1 : Int = id!!.toInt()
       Log.d("Update_Image",ProfileImage)
        RetrofitBuilder.JsonServices.jsonInstance.updateProfile(  RequestParameters().userProfileUpdate(id1,
            personalinfo = personalInfoData, companyInfo = companyInfoData, eduationInfo = educationInfoData
      )).enqueue(object : Callback<DisplayUser?> {
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
                Log.d("TAG", "Display User " + response.body()!!.Data)

                try {
                        val userUpdate = response.body()
                        if (userUpdate != null && response.code() == 200) {

                            userUpdate.Data.UserCompanyInfoId
                            userUpdate.Data.UserEducationId
                            companyName_text.setText(userUpdate.Data.Companyname)
                            current_desig_text.setText(userUpdate.Data.Designation)
                            jobtype_text.setText(userUpdate.Data.Jobtype)
                            employment_type_text.setText(userUpdate.Data.EmploymentType)
                            noticePeriod_text.setText(userUpdate.Data.Noticeperiod)
                            Department_text.setText(userUpdate.Data.Department)
                            gap_in_workExp.setText(userUpdate.Data.GapInExp)
                            totalexp_text.setText(userUpdate.Data.TotalExp)
                            currentCTC_text.setText(userUpdate.Data.Current_CTC.toString())
                            ExpectedCTC_text.setText(userUpdate.Data.Expected_CTC.toString())
                            startActivity(Intent(this@CompanyInfo,DashboardActivity ::class.java))
                            Toast.makeText(
                                getApplicationContext(),
                                "Data Updated  successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            Toast.makeText(this@CompanyInfo, "Try Again", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    } catch (e: java.lang.NullPointerException) {
                        e.printStackTrace()
                    }
            }
            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {

                Log.d("TAG", " Got Error " + t.localizedMessage)
            }
        })
    }

    private fun AlertDialog(){
        val profileAlertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        profileAlertDialog.setTitle("Alert")
        profileAlertDialog.setMessage(R.string.alert_message)
        profileAlertDialog.setPositiveButton(("yes"), DialogInterface.OnClickListener { dialog, item ->
            onBackPressed()
        }).setNegativeButton(("no"), DialogInterface.OnClickListener { dialog, item ->
            dialog.dismiss()
        })
        profileAlertDialog.show()
    }
}