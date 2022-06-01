package com.example.prakashjobapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.example.prakashjobapp.R
import com.example.prakashjobapp.SessionManager
import com.example.prakashjobapp.api.KeyClass
import com.example.prakashjobapp.api.RequestParameters
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.DisplayUser
import com.example.prakashjobapp.models.JobApplyData
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class JobApply : AppCompatActivity() {
    lateinit var back_Arrow_2:ImageView
//    lateinit var editbutton_2 :ImageView
    lateinit var FirstName_editText:EditText
    lateinit var Last_Name_2_editText:EditText
    lateinit var YourEmail_text :EditText
    lateinit var contact_no_text:EditText
    lateinit var currentCTC_Edittext:EditText
    lateinit var ExpectedCTC_Edittext:EditText
    lateinit var noticeperiod_text:EditText
    lateinit var timeslot_text :EditText
    lateinit var Joby_Apply_Button :Button
    lateinit var uploadhere_button : Button
    lateinit var job_apply_layout :LinearLayout
    lateinit var chipGroup :ChipGroup
    private lateinit var sessionManager: SessionManager
    private lateinit var objJobApply : JobApplyData

    companion object{
        var vacancyId : Int = 0
        lateinit  var Firstname :String
        lateinit  var lastname :String
        lateinit  var email :String
        lateinit var contactNo :String
        lateinit  var currentCTC :String
        lateinit   var expectedctc : String
        lateinit var noticeperiod : String
        lateinit   var resumeUpload : String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_apply)

        Firstname = String()
        lastname = String()
        email = String()
        contactNo = String()
        currentCTC = String()
        expectedctc = String()
        noticeperiod = String()
        resumeUpload = String()
        sessionManager = SessionManager(this)
        Joby_Apply_Button = findViewById(R.id.Joby_Apply_Button)
        FirstName_editText = findViewById(R.id.FirstName_editText)
        Last_Name_2_editText = findViewById(R.id.Last_Name_2_editText)
        YourEmail_text = findViewById(R.id.YourEmail_text)
        contact_no_text = findViewById(R.id.contact_no_text)
        currentCTC_Edittext = findViewById(R.id.currentCTC_edittext)
        ExpectedCTC_Edittext = findViewById(R.id.ExpectedCTC_edittext)
        noticeperiod_text = findViewById(R.id.noticeperiod_text)
        timeslot_text = findViewById(R.id.timeslot_text)
        job_apply_layout = findViewById(R.id.job_apply_layout)
        back_Arrow_2 = findViewById(R.id.back_Arrow_2)
//        editbutton_2 = findViewById(R.id.editbutton_2)
        uploadhere_button = findViewById(R.id.uploadhere_button)
        chipGroup = findViewById(R.id.chipGroup)
        addChipCode()
        back_Arrow_2.setOnClickListener {
//            val intent = Intent(this, JobDescription::class.java)
//            startActivity(intent)
            onBackPressed()
        }

        uploadhere_button.setOnClickListener {
            val intent = Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT)
                .addCategory(Intent.CATEGORY_OPENABLE)
            val extraMimeTypes = arrayOf("application/docs", "application/doc","application/pdf")
            intent.putExtra(Intent.EXTRA_MIME_TYPES, extraMimeTypes)

            startActivityForResult(intent, 12)
           // startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
        }
        Joby_Apply_Button.setOnClickListener {

//            val intent = Intent(this, DashboardActivity::class.java)
//            startActivity(intent)
            objJobApply = JobApplyData(
                firstname = Firstname,
                lastname = lastname,
                email = email,
                currentCTC = currentCTC,
                expectedCTC = expectedctc,
                noticePeriod = noticeperiod,
                contactno = contactNo,
                resumeUpload = resumeUpload
            )
            validateJobapply()
//            insertUser()
        }
        val bundle : Bundle = intent.extras!!
        vacancyId = bundle.getInt(KeyClass.KEY_VACANCY_ID)

//        job_apply_layout.alpha = 0.5f

//        editbutton_2.setOnClickListener {
//
//            job_apply_layout.alpha = 1.0f
//            job_apply_layout.isFocusable = true
//            FirstName_editText.isEnabled = true
//            Last_Name_2_editText.isEnabled = true
//            YourEmail_text.isEnabled = true
//            contact_no_text.isEnabled = true
//            currentCTC_Edittext.isEnabled = true
//            ExpectedCTC_Edittext.isEnabled = true
//            noticeperiod_text.isEnabled = true
//            timeslot_text.isEnabled = true
//            Joby_Apply_Button.isEnabled = true
//            uploadhere_button.isEnabled = true
//        }
        DisplayUserDetail()
    }
    fun addChipCode(){

        val genres = arrayOf(".net","c++", "C#", "Java","phthon","Android","Flutter","Kotlin","Communication")
        for (genre in genres) {
            val chip = Chip(chipGroup.context)
            chip.text = genre
            chip.isCheckable = true
            chip.isEnabled = false
            chipGroup.addView(chip)
            chipGroup.checkedChipId
        }
    }
    fun validateJobapply() {

        val ContactNo = (Regex("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}"))
        val emailAddress = Patterns.EMAIL_ADDRESS.matcher(YourEmail_text.text.toString()).matches()

        if (FirstName_editText.getText().toString().isEmpty()) {
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.First_Name_should_not_be_emptied),
                    Toast.LENGTH_SHORT
                ).show()
        } else if (Last_Name_2_editText.getText().toString().isEmpty()) {
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.Last_Name_should_not_be_emptied),
                    Toast.LENGTH_SHORT
                ).show()
        }
//       else if (!emailAddress) {
//               Toast.makeText(
//                  getApplicationContext(),
//                   resources.getString(R.string.Please_enter_a_valid_email),
//                  Toast.LENGTH_SHORT
//               ).show()
//       }
        else  if (YourEmail_text.getText().toString().isEmpty()) {
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.there_are_not_characters_entered_with_email_field),
                Toast.LENGTH_SHORT
            ).show()
        }
//        } else  if (!ContactNo.containsMatchIn(contact_no_text.text.toString())) {
//                 Toast.makeText(
//                    getApplicationContext(),
//                   resources.getString(R.string.Contact_number_should_contain_only_numbers),
//                    Toast.LENGTH_SHORT
//                ).show()
//         }
        else if(contact_no_text.getText().toString().isEmpty()) {
                     Toast.makeText(
                         getApplicationContext(),
                         resources.getString(R.string.Contact_number_should_should_not_be_emptied),
                         Toast.LENGTH_SHORT
                     ).show()
             }
       else  if (currentCTC_Edittext.getText().toString().isEmpty()){
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.Current_CTC_should_not_be_emptied),
                    Toast.LENGTH_SHORT
                ).show()
        }
        else if (ExpectedCTC_Edittext.getText().toString().isEmpty()){
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.Expected_CTC_should_not_be_emptied),
                    Toast.LENGTH_SHORT
                ).show()
        }
        else if (noticeperiod_text.getText().toString().isEmpty()){
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.Notice_Period_should_not_be_emptied),
                    Toast.LENGTH_SHORT
                ).show()
        }
        else if (timeslot_text.getText().toString().isEmpty()){
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.Time_slot_should_not_be_emptied),
                    Toast.LENGTH_SHORT
                ).show()
        }
        else {
            insertUser()
        }

}

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 12 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file
            val uri: Uri = data?.data!!
            val uriString = uri.toString()
            val myFile1 = File(uriString)
            val path1 = myFile1.absolutePath
            var displayName: String? = null
            //  val uri1: Uri = attr.data.getData()
            val myFile = File(uri.toString())
            val path: String = myFile.getAbsolutePath()
            Log.d("nidhi",   myFile.getAbsolutePath().toString())
            if (uriString.startsWith("content://")) {
                var myCursor: Cursor? = null
                try {
                    val nameIndex = myCursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)

                    myCursor = applicationContext!!.contentResolver.query(uri, null, null, null, null)
                    if (myCursor != null && myCursor.moveToFirst()) {

                        displayName = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));

                        // pdfName = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                        uploadhere_button.text = displayName
                    }
                } finally {
                    myCursor?.close()
                }
            } else if (uriString.startsWith("file://")) {
                displayName = myFile.getName();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    fun DisplayUserDetail(){

        val id: String? = sessionManager.getString(SessionManager.KEY_ID)
        val id1 : Int = id!!.toInt()

        RetrofitBuilder.JsonServices.jsonInstance.displayProfile(id1).enqueue(object : Callback<DisplayUser?> {
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
                try {
                    val userDetali = response.body()
                    if (userDetali != null && response.code() == 200){
                          FirstName_editText.setText(userDetali.Data.Firstname)
                          Last_Name_2_editText.setText(userDetali.Data.Lastname)
                          YourEmail_text.setText(userDetali.Data.Emailid)
                          contact_no_text.setText(userDetali.Data.Mobileno)
                          currentCTC_Edittext.setText(userDetali.Data.Current_CTC.toString())
                          ExpectedCTC_Edittext.setText(userDetali.Data.Expected_CTC.toString())
                          noticeperiod_text.setText(userDetali.Data.Noticeperiod)
                            uploadhere_button.setText(userDetali.Data.ResumeUpload)
                        for (chip in chipGroup.children) {
                            chip.isEnabled = true
                        }
                         // timeslot_text.setText(userDetali.Data.)
                    }
                } catch (e : JSONException){
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {
                Log.d("TAG", " Got Error " + t.localizedMessage)
            }
        })
    }

   //InsertApi
    fun insertUser(){
       var jobApplyData :JobApplyData = JobApplyData("","","","","","","","")
       jobApplyData = objJobApply
       val id: String? = sessionManager.getString(SessionManager.KEY_ID)
       val id1 : Int = id!!.toInt()
        RetrofitBuilder.JsonServices.jsonInstance.jobApply(RequestParameters().applyJob( user_id = id1 , jobApply =jobApplyData ))
            .enqueue(object : Callback<DisplayUser?> {
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
                try{
                     val user = response.body()
                     if(user != null && response.code() == 200){
                         Toast.makeText(this@JobApply,
                             "Job Applied successfully",
                             Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@JobApply,DashboardActivity::class.java))
                        finish()
                    }else if (response.body()?.Status?.equals("201") == true) {
                         Toast.makeText(
                             this@JobApply,
                             response.body()?.Message,
                             Toast.LENGTH_SHORT
                         ).show()
                     }else
                    {
                        Toast.makeText(this@JobApply, "try again", Toast.LENGTH_SHORT).show()
                    }

                }catch (e :JSONException){
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {

                Log.d("TAG", " Got Error " + t.localizedMessage)
            }
        })
    }
}