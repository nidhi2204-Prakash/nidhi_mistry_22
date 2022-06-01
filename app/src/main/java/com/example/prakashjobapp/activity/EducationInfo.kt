package com.example.prakashjobapp.activity

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isInvisible
import com.example.prakashjobapp.R
import com.example.prakashjobapp.SessionManager
import com.example.prakashjobapp.activity.JobDescription.Companion.employmentType
import com.example.prakashjobapp.activity.JobDescription.Companion.jobType
import com.example.prakashjobapp.api.KeyClass
import com.example.prakashjobapp.api.RequestParameters
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.*
import com.google.gson.Gson
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class EducationInfo : AppCompatActivity() {
    lateinit var backarrow_edit: ImageView
    lateinit var editbutton: ImageView
    lateinit var qualification_text: EditText
    lateinit var board_university_text: EditText
    lateinit var passing_year_text: EditText
    lateinit var percent_text: EditText
    lateinit var EducationInfo_Layout: LinearLayout
    lateinit var linear_button: LinearLayout
    lateinit var submit_button: FrameLayout
    lateinit var update_button: FrameLayout
    lateinit var submit_textview: TextView
    lateinit var update_textview: TextView
    lateinit var previous_button_edu: AppCompatButton
    lateinit var progressBar: ProgressBar
    lateinit var progress_bar: ProgressBar
//    lateinit var picker_year: NumberPicker
//    private val MAX_YEAR = 2099
    private var year = 0
    private var month = 0
    private var day = 0
//    private lateinit var calendar: Calendar
    protected lateinit var sessionManager: SessionManager
    private lateinit var objectPersonalInfoData: PersonalInfoData
    private lateinit var objectCompanyInfoData: CompanyInfoData
    private lateinit var objectEducationInfoData: EducationInfoData

    companion object {

        lateinit var fName: String
        lateinit var lName: String
        lateinit var email: String
        lateinit var password: String
        lateinit var mobileNo: String
        lateinit var address: String
        lateinit var city: String
        lateinit var state: String
        lateinit var country: String
        lateinit var dateOfBirth: String
        lateinit var gapInedu: String
        lateinit var gender: String
        lateinit var knownLanguage: String
        lateinit var ProfileImage: String
        lateinit var uploadResume: String
        lateinit var companyNameCI: String
        lateinit var currentDesignination: String
        lateinit var jobTypwCI: String
        lateinit var employmentTypeCI: String
        lateinit var totalExp: String
        lateinit var department: String
        lateinit var noticePeriod: String
        lateinit var GapinWorkExp: String
        lateinit var currentCTC: String
        lateinit var expectedCTC: String
        lateinit var qualification: String
        lateinit var board_university: String
        lateinit var passingYear: String
        lateinit var percentage: String
        lateinit var skill1: String
        lateinit var skill2: String
        lateinit var skill3: String
        lateinit var personaldata :String
        lateinit var companydata :String

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education_info)

        val bundle = intent.extras
//        personaldata = bundle!!.getString(KeyClass.PERSONAL_INFO_DATA)!!
//        companydata = bundle!!.getString(KeyClass.COMPANY_INFO_DATA)!!
        fName = String()
        lName = String()
        password = String()
        email = String()
        mobileNo = String()
        address = String()
        city = String()
        state = String()
        country = String()
        dateOfBirth = String()
        gapInedu = String()
        gender = String()
        knownLanguage = String()
        ProfileImage = String()
        uploadResume = String()
        companyNameCI = String()
        currentDesignination = String()
        jobTypwCI = String()
        employmentTypeCI = String()
        totalExp = String()
        department = String()
        noticePeriod = String()
        GapinWorkExp = String()
        currentCTC = String()
        expectedCTC = String()
        qualification = String()
        board_university = String()
        passingYear = String()
        percentage = String()
        skill1 = String()
        skill3 = String()
        skill2 = String()

        update_textview = findViewById(R.id.update_textview)
        progressBar = findViewById(R.id.progressBar1)
        submit_textview = findViewById(R.id.submit_textview)
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
        progress_bar = findViewById(R.id.progress_bar)
//        picker_year = findViewById(R.id.picker_year)

        //NumberPicker
//        passing_year_text.setOnClickListener {
//
////            calendar = Calendar.getInstance()
////            year = calendar.get(Calendar.YEAR)
////            month = calendar.get(Calendar.MONTH)
////            day = calendar.get(Calendar.DAY_OF_MONTH)
////            val year: Int = calendar.get(Calendar.YEAR)
////            picker_year.setMinValue(1900)
////            picker_year.setMaxValue(3500)
////            picker_year.setValue(year)
////            val dialog = DatePickerDialog(this, { _, year, month, day_of_month ->
////                calendar[Calendar.YEAR] = year
////                calendar[Calendar.MONTH] = month
////                calendar[Calendar.DAY_OF_MONTH] = day_of_month
////                val myFormat = "yyy"
////                val sdf = SimpleDateFormat(myFormat, Locale.US)
////                passing_year_text.setText(sdf.format(calendar.time))
////            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
////            dialog.datePicker.maxDate = calendar.timeInMillis
////            dialog.show()
//
////            createDialogWithoutDateField().show()
//            passing_year_text.setShowSoftInputOnFocus(false)
////            picker_year.visibility = View.VISIBLE
//        }
        //Data from Personal info and Company info
//       val bundle = intent.extras
//       if (bundle != null) {
//            fName = bundle.getString(KeyClass.KEY_FIRST_NAMEPI)!!
//           lName = bundle.getString(KeyClass.KEY_LAST_NAMEPI)!!
//           password = bundle.getString(KeyClass.KEY_PASSWORDPI)!!
//           email = bundle.getString(KeyClass.KEY_EMAIL)!!
//           mobileNo = bundle.getString(KeyClass.KEY_MOBILENO_PI)!!
//            address = bundle.getString(KeyClass.KEY_ADDRESS)!!
//           gender = bundle.getString(KeyClass.KEY_GENDER)!!
//           city = bundle.getString(KeyClass.KEY_CITY)!!
//           state = bundle.getString(KeyClass.KEY_STATE)!!
//            country = bundle.getString(KeyClass.KEY_COUNTRY)!!
//           dateOfBirth = bundle.getString(KeyClass.KEY_BIRTH_DATE)!!
//           gapInedu = bundle.getString(KeyClass.KEY_GAP_IN_EDU_PI)!!
//            knownLanguage = bundle.getString(KeyClass.KEY_KNOWN_LANGUAGES)!!
//           ProfileImage = bundle.getString(KeyClass.KEY_PROFILE_IMAGE)!!
//          uploadResume = bundle.getString(KeyClass.KEY_RESUME_UPLOAD)!!
//          companyNameCI = bundle.getString(KeyClass.KEY_COMPANY_NAMECI)!!
//            currentDesignination = bundle.getString(KeyClass.KEY_CURRENT_DESIGNINATION)!!
//           jobTypwCI = bundle.getString(KeyClass.KEY_JOBTYPE_CI)!!
//            employmentTypeCI = bundle.getString(KeyClass.KEY_EMPLOYMENTTYPE_CI)!!
//           totalExp = bundle.getString(KeyClass.KEY_TOTAL_EXPIRENCE_CI)!!
//            department = bundle.getString(KeyClass.KEY_DEPARTMENT)!!
//            noticePeriod = bundle.getString(KeyClass.KEY_NOTICE_PERIOD_CI)!!
//            GapinWorkExp = bundle.getString(KeyClass.KEY_WORK_EXPRIENCE)!!
//           currentCTC = bundle.getString(KeyClass.KEY_CURRENTCTC_CI)!!
//            expectedCTC = bundle.getString(KeyClass.KEY_EXPECTEDCTC_CI)!!
//           objectPersonalInfoData =
//                bundle.getParcelable<PersonalInfoData>(KeyClass.PERSONAL_INFO_DATA)!!
//           objectCompanyInfoData =
//              bundle.getParcelable<CompanyInfoData>(KeyClass.COMPANY_INFO_DATA)!!
//       }

        backarrow_edit.setOnClickListener {
            if (editbutton.isInvisible) {
                onBackPressed()
            } else {
                alertDialog()
            }
        }

        previous_button_edu.setOnClickListener {
            val intent = Intent(this, CompanyInfo::class.java)
            startActivity(intent)
        }
        //SubmitButton
        submit_button.setOnClickListener {
//            progress_bar.visibility = View.VISIBLE
//            submit_textview.visibility = View.GONE
            personaldata = bundle!!.getString(KeyClass.PERSONAL_INFO_DATA)!!
            companydata = bundle!!.getString(KeyClass.COMPANY_INFO_DATA)!!
            objectEducationInfoData = EducationInfoData(Qualification = qualification,
                boardUniversity = board_university,
                passingYear = passingYear,
                percentage = percentage)
          val gson1 = Gson()
          gson1.fromJson(KeyClass.PERSONAL_INFO_DATA, personaldata::class.java)
            objectPersonalInfoData = PersonalInfoData(profilePhoto = ProfileImage,
                firstName = fName,
                lastName = lName,
                email = email,
                password = password,
                mobileNo = mobileNo,
                dateOfBirth = dateOfBirth,
                address = address,
                city = city,
                state = state,
                country = country,
                gapInEducation = gapInedu,
                gender = gender,
                knownlanguage = knownLanguage,
                skills = skill1,
                resume = uploadResume)
           val gson = Gson()
          gson.fromJson(KeyClass.COMPANY_INFO_DATA, companydata::class.java)
            objectCompanyInfoData = CompanyInfoData(companyName = companyNameCI,
                currentDesignation = currentDesignination,
                jobType = jobType,
                employmentType = employmentType,
                totalExp = totalExp,
                department = department,
                noticePeriod = noticePeriod,
                gapInWorkExpirence = GapinWorkExp,
                currentCTC = currentCTC,
                expectedCTC = expectedCTC)
            ValidEducation(true)
        }

        //UpdateButton
        update_button.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            update_textview.visibility = View.GONE
            passingYear = passing_year_text.text.toString()
            qualification = qualification_text.text.toString()
            percentage = percent_text.text.toString()
            board_university = board_university_text.text.toString()

            objectPersonalInfoData = PersonalInfoData(
                profilePhoto = ProfileImage,
                firstName = fName,
                lastName = lName,
                email = email,
                password = password,
                mobileNo = mobileNo,
                dateOfBirth = dateOfBirth,
                address = address,
                city = city,
                state = state,
                country = country,
                gapInEducation = gapInedu,
                gender = gender,
                knownlanguage = knownLanguage,
                skills = skill1,
                resume = uploadResume)
            objectCompanyInfoData = CompanyInfoData(
                companyName = companyNameCI,
                currentDesignation = currentDesignination,
                jobType = jobType,
                employmentType = employmentType,
                totalExp = totalExp,
                department = department,
                noticePeriod = noticePeriod,
                gapInWorkExpirence = GapinWorkExp,
                currentCTC = currentCTC,
                expectedCTC = expectedCTC)
            ValidEducation(false)
        }
    }

    private fun enabledField() {
        EducationInfo_Layout.alpha = 0.5f
        editbutton.setOnClickListener {

            EducationInfo_Layout.alpha = 1.0f
            EducationInfo_Layout.isFocusable = true
            qualification_text.isEnabled = true
            board_university_text.isEnabled = true
            passing_year_text.isEnabled = true
            percent_text.isEnabled = true
        }
    }

    override fun onStart() {
        super.onStart()
        displayUserProfile()
    }

    fun ValidEducation(isInsert : Boolean) {
        if (TextUtils.isEmpty(qualification_text.toString())) {
            Toast.makeText(this,
                resources.getString(R.string.Qualification_should_not_be_emptied),
                Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(board_university_text.toString())) {
            Toast.makeText(this,
                resources.getString(R.string.University_should_not_be_emptied),
                Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(passing_year_text.toString())) {
            Toast.makeText(this,
                resources.getString(R.string.Passing_year_should_not_be_emptied),
                Toast.LENGTH_SHORT).show()
        } else if (TextUtils.isEmpty(percent_text.toString())) {
            Toast.makeText(this,
                resources.getString(R.string.Percentage_should_not_be_emptied),
                Toast.LENGTH_SHORT).show()
        }
        else {
            if(isInsert){
                userInsert()
                progress_bar.visibility = View.VISIBLE
                submit_textview.visibility = View.GONE
            }else {
                updateProfile()
                progressBar.visibility = View.VISIBLE
                update_textview.visibility = View.GONE
            }
        }
    //       if (qualification_text.getText().toString().isEmpty()) {
//            Toast.makeText(
//                this,
//                resources.getString(R.string.Qualification_should_not_be_emptied),
//                Toast.LENGTH_SHORT
//            ).show()
//        } else if (board_university_text.getText().toString().isEmpty()) {
//            Toast.makeText(
//                this,
//                resources.getString(R.string.University_should_not_be_emptied),
//                Toast.LENGTH_SHORT
//            ).show()
//        } else if (passing_year_text.getText().toString().isEmpty()) {
//            Toast.makeText(
//                this,
//                resources.getString(R.string.Passing_year_should_not_be_emptied),
//                Toast.LENGTH_SHORT
//            ).show()
//        } else if (percent_text.getText().toString().isEmpty()) {
//            Toast.makeText(
//                this,
//                resources.getString(R.string.Percentage_should_not_be_emptied),
//                Toast.LENGTH_SHORT
//            ).show()
//        }
    }

    // UserInsert
    fun userInsert() {
        var personalInfoData: PersonalInfoData =
            PersonalInfoData("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
        var companyInfoData: CompanyInfoData =
            CompanyInfoData("", "", "", "", "", "", "", "", "", "")
        var educationInfoData: EducationInfoData = EducationInfoData("", "", "", "")
        personalInfoData = objectPersonalInfoData
        companyInfoData = objectCompanyInfoData
        educationInfoData = objectEducationInfoData
        val id: String? = sessionManager.getString(SessionManager.KEY_ID)
        val id1: Int = id!!.toInt()
        RetrofitBuilder.JsonServices.jsonInstance.profileSubmit(RequestParameters().userProfileSubmit(
            id1,
            personalinfo = personalInfoData,
            companyInfo = companyInfoData,
            educationInfo = educationInfoData
        )).enqueue(object : Callback<DisplayUser?> {
            override fun onResponse(
                call: Call<DisplayUser?>,
                response: Response<DisplayUser?>,
            ) {
                try {
                    val insertUser = response.body()
                    if (insertUser != null && response.code() == 200) {
                        Toast.makeText(this@EducationInfo,
                            "Data submited successfully",
                            Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@EducationInfo, DashboardActivity::class.java))
                        finish()
                    } else if (response.body()?.Status?.equals("201") == true) {
                        Toast.makeText(
                            this@EducationInfo,
                            response.body()?.Message,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@EducationInfo,
                            "Try Again !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {

                Log.d("TAG", " Got Error " + t.localizedMessage)
            }
        })
    }

    fun displayUserProfile() {
        sessionManager = SessionManager(this)
        val id: String? = sessionManager.getString(SessionManager.KEY_ID)
        val id1: Int = id!!.toInt()
        RetrofitBuilder.JsonServices.jsonInstance.displayProfile(id1).enqueue(object :
            Callback<DisplayUser?> {
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
                Log.d("TAG", "Display User " + response.body()!!.Data)

                try {
                    val user1 = response.body()

                    if (user1 != null && response.code() == 200) {
                        qualification_text.setText(user1.Data.Qualification)
                        board_university_text.setText(user1.Data.Board_University)
                        passing_year_text.setText(user1.Data.PassingYear.toString())
                        percent_text.setText(user1.Data.Percentage.toString())
                        if (user1.Data != null && user1.Data.DateOfBirth.isNullOrEmpty()) {
//                            editbutton.visibility = View.VISIBLE
//                            enabledField()
//                            update_button.visibility = View.VISIBLE
                            //for check
                            EducationInfo_Layout.alpha = 1.0f
                            EducationInfo_Layout.isFocusable = true
                            qualification_text.isEnabled = true
                            board_university_text.isEnabled = true
                            passing_year_text.isEnabled = true
                            percent_text.isEnabled = true
                            editbutton.visibility = View.INVISIBLE
                            submit_button.visibility = View.VISIBLE
                            previous_button_edu.visibility = View.VISIBLE
                        } else {
                            editbutton.visibility = View.VISIBLE
                            enabledField()
                            update_button.visibility = View.VISIBLE
//                            EducationInfo_Layout.alpha = 1.0f
//                            EducationInfo_Layout.isFocusable = true
//                            qualification_text.isEnabled = true
//                            board_university_text.isEnabled = true
//                            passing_year_text.isEnabled = true
//                            percent_text.isEnabled = true
//                            editbutton.visibility = View.INVISIBLE
//                            submit_button.visibility = View.VISIBLE
//                            previous_button_edu.visibility = View.VISIBLE
                        }
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()

                }
            }

            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {
                Log.d("TAG", " Got Error " + t.localizedMessage)
            }
        })
    }

    fun updateProfile() {
        var personalInfoData: PersonalInfoData =
            PersonalInfoData("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
        var companyInfoData: CompanyInfoData =
            CompanyInfoData("", "", "", "", "", "", "", "", "", "")
        var educationInfoData: EducationInfoData = EducationInfoData("", "", "", "")
        val qualification = qualification_text.text.toString()
        val board_university = board_university_text.text.toString()
        val passingYear = passing_year_text.text.toString()
        val percentage = percent_text.text.toString()
        educationInfoData =
            EducationInfoData(Qualification = qualification, boardUniversity = board_university,
                passingYear = passingYear, percentage = percentage
            )
        val objectPersonalInfoData = PersonalInfoData(
            profilePhoto = ProfileImage,
            firstName = fName,
            lastName = lName,
            email = email,
            password = password,
            mobileNo = mobileNo,
            gender = gender,
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
        personalInfoData = objectPersonalInfoData
        companyInfoData = objectCompanyInfoData
        sessionManager = SessionManager(this)
        val id: String? = sessionManager.getString(SessionManager.KEY_ID)
        val id1: Int = id!!.toInt()
        Log.d("Update_Image", ProfileImage)

        RetrofitBuilder.JsonServices.jsonInstance.updateProfile(RequestParameters().userProfileUpdate(
            id1,
            personalinfo = personalInfoData,
            companyInfo = companyInfoData,
            eduationInfo = educationInfoData))
            .enqueue(object : Callback<DisplayUser?> {
                override fun onResponse(
                    call: Call<DisplayUser?>,
                    response: Response<DisplayUser?>,
                ) {
                    Log.d("TAG", "Update User " + response.body()!!.Data)

                    try {
                        val userUpdate = response.body()
                        if (userUpdate != null && response.code() == 200) {
                            userUpdate.Data.UserCompanyInfoId
                            userUpdate.Data.UserEducationId
                            startActivity(Intent(this@EducationInfo, DashboardActivity::class.java))
                            Toast.makeText(
                                getApplicationContext(),
                                "Data Updated  successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {

                            Toast.makeText(
                                getApplicationContext(),
                                "Try Again",
                                Toast.LENGTH_SHORT
                            ).show()
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

    private fun alertDialog() {
        val profileAlertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        profileAlertDialog.setTitle("Alert")
        profileAlertDialog.setMessage(R.string.alert_message)
        profileAlertDialog.setPositiveButton(("yes"),
            DialogInterface.OnClickListener { dialog, item ->
                onBackPressed()
            }).setNegativeButton(("no"), DialogInterface.OnClickListener { dialog, item ->
            dialog.dismiss()
        })
        profileAlertDialog.show()
    }

    private fun createDialogWithoutDateField(): DatePickerDialog {
        val dpd = DatePickerDialog(this ,null, 2014, 1, 24)
        dpd.datePicker.spinnersShown =  true
        print("Spinner Mode")

        try {
            val datePickerDialogFields = dpd.javaClass.declaredFields
            for (datePickerDialogField in datePickerDialogFields) {
                if (datePickerDialogField.name == "mDatePicker") {
                    datePickerDialogField.isAccessible = true

                    val datePicker = datePickerDialogField[dpd] as DatePicker
                    val datePickerFields = datePickerDialogField.type.declaredFields
                    for (datePickerField in datePickerFields) {
                        Log.i("test", datePickerField.name)
                        if ("mDaySpinner" == datePickerField.name) {
                            datePickerField.isAccessible = true
                            val dayPicker = datePickerField[datePicker]
                            (dayPicker as View).visibility = View.GONE

                        }
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        catch (e :JSONException){
            e.printStackTrace()
        }
        return dpd
    }
}