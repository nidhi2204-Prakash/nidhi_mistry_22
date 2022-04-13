package com.example.prakashjobapp.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.example.prakashjobapp.R
import com.example.prakashjobapp.activity.EducationInfo.Companion.GapinWorkExp
import com.example.prakashjobapp.activity.EducationInfo.Companion.ProfileImage
import com.example.prakashjobapp.activity.EducationInfo.Companion.address
import com.example.prakashjobapp.activity.EducationInfo.Companion.board_university
import com.example.prakashjobapp.activity.EducationInfo.Companion.city
import com.example.prakashjobapp.activity.EducationInfo.Companion.companyNameCI
import com.example.prakashjobapp.activity.EducationInfo.Companion.country
import com.example.prakashjobapp.activity.EducationInfo.Companion.currentCTC
import com.example.prakashjobapp.activity.EducationInfo.Companion.currentDesignination
import com.example.prakashjobapp.activity.EducationInfo.Companion.dateOfBirth
import com.example.prakashjobapp.activity.EducationInfo.Companion.department
import com.example.prakashjobapp.activity.EducationInfo.Companion.email
import com.example.prakashjobapp.activity.EducationInfo.Companion.employmentTypeCI
import com.example.prakashjobapp.activity.EducationInfo.Companion.expectedCTC
import com.example.prakashjobapp.activity.EducationInfo.Companion.fName
import com.example.prakashjobapp.activity.EducationInfo.Companion.gapInedu
import com.example.prakashjobapp.activity.EducationInfo.Companion.gender
import com.example.prakashjobapp.activity.EducationInfo.Companion.jobTypwCI
import com.example.prakashjobapp.activity.EducationInfo.Companion.knownLanguage
import com.example.prakashjobapp.activity.EducationInfo.Companion.lName
import com.example.prakashjobapp.activity.EducationInfo.Companion.mobileNo
import com.example.prakashjobapp.activity.EducationInfo.Companion.noticePeriod
import com.example.prakashjobapp.activity.EducationInfo.Companion.passingYear
import com.example.prakashjobapp.activity.EducationInfo.Companion.password
import com.example.prakashjobapp.activity.EducationInfo.Companion.percentage
import com.example.prakashjobapp.activity.EducationInfo.Companion.qualification
import com.example.prakashjobapp.activity.EducationInfo.Companion.skill1
import com.example.prakashjobapp.activity.EducationInfo.Companion.skill2
import com.example.prakashjobapp.activity.EducationInfo.Companion.skill3
import com.example.prakashjobapp.activity.EducationInfo.Companion.state
import com.example.prakashjobapp.activity.EducationInfo.Companion.totalExp
import com.example.prakashjobapp.activity.EducationInfo.Companion.uploadResume
import com.example.prakashjobapp.api.KeyClass
import com.example.prakashjobapp.api.RequestParameters
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.fragments.ProfileFragment
import com.example.prakashjobapp.models.DisplayUser
import com.example.prakashjobapp.models.PersonalInfoData
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

class PersonalInfo : AppCompatActivity() {
    lateinit var personal_info_backarrow: ImageView
    lateinit var Update_btn: Button
    lateinit var person_Name :TextView
    lateinit var Profile_Button: CircleImageView
    lateinit var iv_camera: AppCompatImageView
    lateinit var personal_info_layout: LinearLayout
    lateinit var editbutton_edit: ImageView
    lateinit var Date_of_birth_edit: EditText
//    lateinit var DOB_text: TextView
    lateinit var FirstName_Text :EditText
    lateinit var Last_Name_2_Text :EditText
    lateinit var email_id :EditText
    lateinit var password_edit :EditText
    lateinit var mobile_no_text :EditText
    lateinit var address_text :EditText
    lateinit var city_text :EditText
    lateinit var state_text: EditText
    lateinit var country_text :EditText
    lateinit var gap_in_edu_text: EditText
    lateinit var uploadResume_button :Button
    lateinit var known_language_text:EditText
    lateinit var male_1 :RadioButton
    lateinit var female_1 :RadioButton
    lateinit var radioGroup :RadioGroup
    lateinit var next_button :AppCompatButton
    lateinit var chip_1 :Chip
    lateinit var chip_2 :Chip
    lateinit var chip_3 :Chip
    private var year = 0
    private var month = 0
    private var day = 0
    private lateinit var calendar: Calendar
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var imageByte: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)

        male_1 = findViewById(R.id.male_1)
        female_1 = findViewById(R.id.female_1)
        FirstName_Text= findViewById(R.id.FirstName_Text)
        Last_Name_2_Text = findViewById(R.id.Last_Name_2_Text)
        email_id = findViewById(R.id.email_id)
        password_edit = findViewById(R.id.password_edit)
        mobile_no_text = findViewById(R.id.mobile_no_text)
        address_text = findViewById(R.id.address_text)
        city_text = findViewById(R.id.city_text)
        state_text = findViewById(R.id.state_text)
        country_text = findViewById(R.id.country_text)
        gap_in_edu_text = findViewById(R.id.gap_in_edu_text)
        uploadResume_button = findViewById(R.id.uploadResume_button)
        known_language_text = findViewById(R.id.known_language_text)
        editbutton_edit = findViewById(R.id.editbutton_edit)
        personal_info_layout = findViewById(R.id.personal_info_layout)
        Update_btn = findViewById(R.id.Update_btn)
        radioGroup = findViewById(R.id.radioGroup)
        personal_info_backarrow = findViewById(R.id.personal_info_backarrow)
        Date_of_birth_edit = findViewById(R.id.Date_of_birth_edit)
        Profile_Button = findViewById(R.id.Profile_Button)
        iv_camera = findViewById(R.id.iv_camera)
        chip_1 = findViewById(R.id.chip_1)
        chip_2 = findViewById(R.id.chip_2)
        chip_3 = findViewById(R.id.chip_3)
        person_Name = findViewById(R.id.person_Name)
        next_button = findViewById(R.id.next_button)

//        val imageUri = "https://blobstorageprakashjobs.blob.core.windows.net/blobstorageprakashjobs/ecabc4e1-cd35-46ec-8ab3-cf4c505602fc-sunflower.jpg"
//        Picasso.with(this).load(imageUri).into(Profile_Button)


//        personal_info_layout.alpha = 0.5f
        editbutton_edit.setOnClickListener {

//            personal_info_layout.alpha = 1.0f
            personal_info_layout.isFocusable = true
            FirstName_Text.isEnabled = true
            Last_Name_2_Text.isEnabled = true
            email_id.isEnabled = true
            password_edit.isEnabled = true
            mobile_no_text.isEnabled = true
            address_text.isEnabled = true
            city_text.isEnabled = true
            state_text.isEnabled = true
            country_text.isEnabled = true
            gap_in_edu_text.isEnabled = true
            uploadResume_button.isEnabled = true
            known_language_text.isEnabled = true
            Update_btn.isEnabled = true
            Date_of_birth_edit.isEnabled = true
            Profile_Button.isEnabled = true
            chip_1.isEnabled = true
            chip_2.isEnabled = true
            chip_3.isEnabled = true
        }
//        Profile_Button.setOnClickListener {
//           val intent = Intent(this@PersonalInfo,DashboardActivity ::class.java)
//          // val imageShow = Profile_Button.imageAlpha
//            intent.putExtra("showImage",R.id.Profile_Button)
//            startActivity(intent)
////            val mBundle = Bundle()
////            mBundle.putString("image",imageShow.toString())
////            ProfileFragment. = mBundle
////            ProfileFragmentTransaction.add(R.id.frameLayout, mFragment).commit()
//           }
        personal_info_backarrow.setOnClickListener {

          onBackPressed()
        }

//        Update_btn.alpha = 0.5f

        Update_btn.setOnClickListener {
        editbutton_edit.visibility = View.VISIBLE
            fName = FirstName_Text.text.toString()
            lName = Last_Name_2_Text.text.toString()
            email = email_id.text.toString()
            password = password_edit.text.toString()
            mobileNo = mobile_no_text.text.toString()
            address = address_text.text.toString()
            city = city_text.text.toString()
            state = state_text.text.toString()
            country = country_text.text.toString()
            gapInedu = gap_in_edu_text.text.toString()
            uploadResume = uploadResume_button.text.toString()
            dateOfBirth = Date_of_birth_edit.text.toString()
            knownLanguage = known_language_text.text.toString()
            Log.d("imageByte_Update",imageByte.toString())
            ProfileImage = imageByte.toString()
            skill1 = chip_1.text.toString()
            skill2 = chip_2.text.toString()
            skill3 = chip_3.text as String
            val selectedId = radioGroup.checkedRadioButtonId
            var rb:RadioButton
            rb = findViewById<View>(selectedId) as RadioButton
            gender = rb.text.toString()
            companyNameCI = ""
            currentDesignination = ""
            jobTypwCI =""
            employmentTypeCI =""
            totalExp = ""
            GapinWorkExp = " "
            noticePeriod =""
            department =""
            currentCTC =""
            expectedCTC =""
            qualification =""
            board_university =""
            passingYear =""
            percentage =""

//        val firstNamePI  = FirstName_Text.text
//        val lastNamePI = Last_Name_2_Text.text
//        val emailPI = email_id.text
//        val passwordPI = password_edit.text
//        val mobileNo = mobile_no_text.text
//        val addressPI = address_text.text
//        val city =  city_text.text
//        val state = state_text.text
//        val country = country_text.text
//        val gapInEdu = gap_in_edu_text.text
//        val uploadResume = uploadResume_button.text
//        val birthDate = Date_of_birth_edit.text
//        val knowNlanguages = known_language_text.text
//        val profileImage = Profile_Button.imageAlpha
//        val skill1 = chip_1.text
//        val skill2 = chip_2.text
//        val skill3 = chip_3.text


//          val intent = Intent(this,CompanyInfo::class.java)
//            intent.putExtra("FirstNamePI",firstNamePI)
//            intent.putExtra("LastNamePI",lastNamePI)
//            intent.putExtra("Email",emailPI)
//            intent.putExtra("Password",passwordPI)
//            intent.putExtra("Mobile No",mobileNo)
//            intent.putExtra("Address",addressPI)
//            intent.putExtra("City",city)
//            intent.putExtra("State",state)
//            intent.putExtra("Country",country)
//            intent.putExtra("skill1",skill1)
//            intent.putExtra("skill2",skill2)
//            intent.putExtra("skill3",skill3)
//            intent.putExtra("gapInEducation",gapInEdu)
//            intent.putExtra("uploadResumePI",uploadResume)
//            intent.putExtra("BirthDate",birthDate)
//            intent.putExtra("knowonLanguage",knowNlanguages)
//            intent.putExtra("ProfileImage",profileImage)
            ValidPersonalInfo()
            updateProfile()

        }
        next_button.setOnClickListener {

            val fName: String =FirstName_Text .getText().toString().trim()
            val lNAme: String = Last_Name_2_Text.getText().toString().trim()
            val email: String = Last_Name_2_Text.getText().toString().trim()
            val password: String = Last_Name_2_Text.getText().toString().trim()
            val city: String = Last_Name_2_Text.getText().toString().trim()
            val state: String = Last_Name_2_Text.getText().toString().trim()
            val country: String = Last_Name_2_Text.getText().toString().trim()
            val knownLanguages: String = Last_Name_2_Text.getText().toString().trim()
            val uploadresume: String = Last_Name_2_Text.getText().toString().trim()
            val gapinEdu: String = Last_Name_2_Text.getText().toString().trim()
            val birthdate: String = Last_Name_2_Text.getText().toString().trim()
            val address: String = Last_Name_2_Text.getText().toString().trim()
            val mobileNo: String = Last_Name_2_Text.getText().toString().trim()
            val profileimage: String = Last_Name_2_Text.getText().toString().trim()
            val gender : String
            val selectedGenderId = radioGroup.checkedRadioButtonId
            val radioButton: RadioButton = findViewById(selectedGenderId)
            val selectedGender: String = radioButton.text.toString()

            if (!TextUtils.isEmpty(selectedGender) && selectedGender.equals("Male")) {
                gender = "M"
            } else {
                gender = "F"
            }

            val bundle = Bundle()
            bundle.putString(KeyClass.KEY_FIRST_NAMEPI, fName)
            bundle.putString(KeyClass.KEY_LAST_NAMEPI, lNAme)
            bundle.putString(KeyClass.KEY_EMAIL,email)
            bundle.putString(KeyClass.KEY_PASSWORDPI,password)
            bundle.putString(KeyClass.KEY_MOBILENO_PI,mobileNo)
            bundle.putString(KeyClass.KEY_CITY,city)
            bundle.putString(KeyClass.KEY_STATE,state)
            bundle.putString(KeyClass.KEY_COUNTRY,country)
            bundle.putString(KeyClass.KEY_ADDRESS,address)
            bundle.putString(KeyClass.KEY_GAP_IN_EDU_PI,gapinEdu)
            bundle.putString(KeyClass.KEY_BIRTH_DATE,birthdate)
            bundle.putString(KeyClass.KEY_KNOWN_LANGUAGES,knownLanguages)
            bundle.putString(KeyClass.KEY_RESUME_UPLOAD,uploadresume)
            bundle.putString(KeyClass.KEY_PROFILE_IMAGE,profileimage)
            val intent = Intent(this, CompanyInfo::class.java)

            intent.putExtras(bundle)
            startActivity(intent)

//            val firstNamePI  = FirstName_Text.text.toString()
//            val lastNamePI = Last_Name_2_Text.text.toString()
//            val emailPI = email_id.text.toString()
//            val passwordPI = password_edit.text.toString()
//            val mobileNo = mobile_no_text.text.toString()
//            val addressPI = address_text.text.toString()
//            val city =  city_text.text.toString()
//            val state = state_text.text.toString()
//            val country = country_text.text.toString()
//            val gapInEdu = gap_in_edu_text.text.toString()
//            val uploadResume = uploadResume_button.text.toString()
//            val birthDate = Date_of_birth_edit.text.toString()
//            val knowNlanguages = known_language_text.text.toString()
//            val profileImage = Profile_Button.imageAlpha.toString()
//            val skill1 = chip_1.text.toString()
//            val skill2 = chip_2.text.toString()
//            val skill3 = chip_3.text.toString()


       //     val intent = Intent(this,CompanyInfo::class.java)
//            intent.putExtra(Constant.KEY_FIRST_NAMEPI,firstNamePI)
//            intent.putExtra(Constant.KEY_LAST_NAMEPI,lastNamePI)
//            intent.putExtra(Constant.KEY_EMAIL,emailPI)
//            intent.putExtra(Constant.KEY_PASSWORDPI,passwordPI)
//            intent.putExtra(Constant.KEY_MOBILENO_PI,mobileNo)
//            intent.putExtra(Constant.KEY_ADDRESS,addressPI)
//            intent.putExtra(Constant.KEY_ADDRESS,city)
//            intent.putExtra(Constant.KEY_STATE,state)
//            intent.putExtra(Constant.KEY_COUNTRY,country)
//            intent.putExtra("skill",skill1)
//            intent.putExtra("skill2",skill2)
//            intent.putExtra("skill3",skill3)
//            intent.putExtra(Constant.KEY_GAP_IN_EDU_PI,gapInEdu)
//            intent.putExtra(Constant.KEY_RESUME_UPLOAD,uploadResume)
//            intent.putExtra(Constant.KEY_BIRTH_DATE,birthDate)
//            intent.putExtra(Constant.KEY_KNOWN_LANGUAGES,knowNlanguages)
//            intent.putExtra(Constant.KEY_PROFILE_IMAGE,profileImage)
//            startActivity(intent)
            ValidPersonalInfo()
        }

        iv_camera.setOnClickListener {

            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)

         //   Toast.makeText(this, "Clicked ", Toast.LENGTH_SHORT).show()

        }
        uploadResume_button.setOnClickListener {
            val intent = Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT)
                .addCategory(Intent.CATEGORY_OPENABLE)
                val extraMimeTypes = arrayOf("application/docs", "application/doc","application/pdf")
                intent.putExtra(Intent.EXTRA_MIME_TYPES, extraMimeTypes)
                startActivityForResult(intent, 12)

        }

        Date_of_birth_edit.setOnClickListener {
            calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)

            Date_of_birth_edit.setShowSoftInputOnFocus(false)

            val dialog = DatePickerDialog(this, { _, year, month, day_of_month ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = day_of_month
                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                Date_of_birth_edit.setText(sdf.format(calendar.time))
                val input = "2012/01/20 12:05:10.321"
                val output = input.substring(0, 10) // Output : 2012/01/20
            }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH])
            dialog.datePicker.maxDate = calendar.timeInMillis
            dialog.show()
        }
        displayUser()
    }

    fun ValidPersonalInfo() {

        val patternPassword = Regex("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}")
        val patternPhone = (Regex("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}"))
        val emailAddress =  Patterns.EMAIL_ADDRESS.matcher(email_id.text.toString()).matches()

        if (FirstName_Text.getText().toString().isEmpty()) {
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.First_Name_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (Last_Name_2_Text.getText().toString().isEmpty()) {
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Last_Name_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (email_id.getText().toString().isEmpty()) {
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Email_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (emailAddress){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Please_enter_valid_email),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if  (!patternPassword.matches(password_edit.text.toString())) {
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.password_valid),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (mobile_no_text.getText().toString().isEmpty()) {
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Phone_number_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (patternPhone.containsMatchIn(mobile_no_text.text.toString())){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Phone_number_should_contain_only_nimber),
                Toast.LENGTH_SHORT
            ).show()
        }
       else if (Date_of_birth_edit.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Please_Enter_Valid_Date),
                Toast.LENGTH_SHORT
            ).show()
        }
       else  if (address_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Address_Should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (city_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.City_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (state_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.State_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (country_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Country_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (known_language_text.getText().toString().isEmpty()){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Language_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {

          val   filePath = data?.getData();
            val    path_New = filePath?.getPath();
//            Profile_Button.setImageURI(data?.data)
           imageUri = data?.data
//            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver,imageUri)
//            imageUri = da?ta.getData()
//           val path: String = imageUri.toString()
            val uriString = imageUri.toString()
            val myFile1 = File(uriString)
            val path1 = myFile1.absolutePath
            val path2: String = myFile1.getAbsolutePath()
            Log.d("jinal", myFile1.getAbsolutePath().toString())
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val bytes = stream.toByteArray()
            Log.d("byte",bytes.toString())
            val bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            imageByte = bytes.toString()
            Profile_Button.setImageBitmap(bitmap1)
            val bundle = Bundle()
            bundle.putString("image ", imageUri.toString())
            val fragment = ProfileFragment()
            fragment.arguments = bundle

        }

            //For pdff file
            if (requestCode == 12 && resultCode == RESULT_OK) {
                val selectedFile = data?.data //The uri with the location of the file
                val uri: Uri = data?.data!!
                //  val uriString: String = uri.toString()
                // var pdfName: String? = null

                val uriString = uri.toString()
                val myFile1 = File(uriString)
                val path1 = myFile1.absolutePath
                var displayName: String? = null
                //  val uri1: Uri = attr.data.getData()
                val myFile = File(uri.toString())
                val path: String = myFile.getAbsolutePath()
                Log.d("nidhi", myFile.getAbsolutePath().toString())
                if (uriString.startsWith("content://")) {
                    var myCursor: Cursor? = null
                    try {
                        val nameIndex = myCursor?.getColumnIndex(OpenableColumns.DISPLAY_NAME)

                        myCursor =
                            applicationContext!!.contentResolver.query(uri, null, null, null, null)
                        if (myCursor != null && myCursor.moveToFirst()) {

                            displayName =
                                myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));

                            // pdfName = myCursor.getString(myCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                            uploadResume_button.text = displayName
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

    @Throws(IOException::class)
    fun getBytes(inputStream: InputStream): ByteArray? {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 5024
        val buffer = ByteArray(bufferSize)
        var len = 0
        while (inputStream.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        return byteBuffer.toByteArray()
    }
//        }
//        // Create the AlertDialog
//        val alertDialogbox = alertDialog.create()
//        // Set other dialog properties
//        alertDialogbox.setCancelable(false)
//        alertDialog.show()
//    }

//DisplayUserApi
    fun displayUser(){
//     val firstNamePI  = FirstName_Text.text
//    val lastNamePI = Last_Name_2_Text.text
//    val emailPI = email_id.text
//    val passwordPI = password_edit.text
//    val mobileNo = mobile_no_text.text
//    val addressPI = address_text.text
//    val city =  city_text.text
//    val state = state_text.text
//    val country = country_text.text
//    val gapInEdu = gap_in_edu_text.text
//    val uploadResume = uploadResume_button.text
//    val birthDate = Date_of_birth_edit.text
//    val knowNlanguages = known_language_text.text
//    val profileImage = Profile_Button.imageAlpha

        RetrofitBuilder.JsonServices.jsonInstance.displayProfile(128).enqueue(object : Callback<DisplayUser?> {
            @SuppressLint("StringFormatMatches")
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
      //          Log.d("TAG", "Display User " + response.body()!!.Data)

                try {

                    val userDisplay = response.body()
//                   if (userupdate == null) {
//                        editbutton_edit.isVisible = true
//                    } else {
//                        editbutton_edit.isVisible = false
//                    }
                    if (userDisplay != null && response.code() == 200) {
                        val firstName = userDisplay.Data.Firstname
                        val lastName = userDisplay.Data.Lastname
                   //     person_Name.text = getString(R.string.welcome_messages ,firstName + lastName)
                        person_Name.text = firstName + " " + lastName

                        FirstName_Text.setText(userDisplay.Data.Firstname)
                        //setimage
                        Picasso.with(this@PersonalInfo).load(imageUri).into(Profile_Button)
                        Last_Name_2_Text.setText(userDisplay.Data.Lastname)
                        email_id.setText(userDisplay.Data.Emailid)
                        password_edit.setText(userDisplay.Data.Password)
                        mobile_no_text.setText(userDisplay.Data.Mobileno)
                        city_text.setText(userDisplay.Data.City)
                        state_text.setText(userDisplay.Data.State)
                        country_text.setText(userDisplay.Data.Country)
                        known_language_text.setText(userDisplay.Data.Languages)
                        gap_in_edu_text.setText(userDisplay.Data.EducationGap)
                        address_text.setText(userDisplay.Data.Address)
                        Date_of_birth_edit.setText(userDisplay.Data.DateOfBirth)
                        uploadResume_button.setText(userDisplay.Data.ResumeUpload)
//                        Picasso.get(.load("https://blobstorageprakashjobs.blob.core.windows.net\\n\" +\n" +
//                                "                \"/blobstorageprakashjobs/81b7b983-1a3d-493e-bbbb-b31e3e8e5726-sunflower.png").into(Profile_Button);

                        if (userDisplay.Data.Gender.equals("male")){
                            male_1.isChecked
                        }
                        else{
                            female_1.isChecked
                        }

                        if (userDisplay != null){
                            editbutton_edit.visibility = View.INVISIBLE
                            Update_btn.visibility = View.VISIBLE
                            Toast.makeText(applicationContext, "visible", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            editbutton_edit.visibility = View.VISIBLE
                            next_button.visibility = View.VISIBLE
                            Toast.makeText(applicationContext, "Visible", Toast.LENGTH_SHORT).show()
                        }

//                        if (male_1.isChecked || female_1.isChecked){

//                            male_1.setText(user1.Data.Gender)
//                        }
//                        else{
//                            female_1.setText(user1.Data.Gender)
//                        }
//                        Profile_Button.setImageResource(user1.Data.ProfilePhoto)

                }
            }catch (e: JSONException){
                        e.printStackTrace()
                }
            }
            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {
                Toast.makeText(
                    this@PersonalInfo,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
    fun updateProfile(){

        Log.d("Update_Image",ProfileImage)
        RetrofitBuilder.JsonServices.jsonInstance.updateProfile(  RequestParameters().userProfileUpdate(128,personalinfo = PersonalInfoData(fName,
            lName, email, password, mobileNo, city, state, country, address,
            knownLanguage, companyNameCI, currentDesignination, jobTypwCI, employmentTypeCI, department,
            expectedCTC, currentCTC, passingYear, qualification, percentage, skill1, skill2,
            skill3, uploadResume, ProfileImage, gapInedu, board_university, totalExp, gapInedu, percentage))).enqueue(object : Callback<DisplayUser?> {
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
                try {
                    val userupdate = response.body()
                    if (userupdate != null)
                    {
//                        Picasso.with(this@PersonalInfo).load(imageUri).into(Profile_Button)
//                        FirstName_Text.setText(userupdate.Data.Firstname)
//                        Last_Name_2_Text.setText(userupdate.Data.Lastname)
//                        email_id.setText(userupdate.Data.Emailid)
//                        password_edit.setText(userupdate.Data.Password)
//                        mobile_no_text.setText(userupdate.Data.Mobileno)
//                        Date_of_birth_edit.setText(userupdate.Data.DateOfBirth)
//                        address_text.setText(userupdate.Data.Address)
//                        city_text.setText(userupdate.Data.City)
//                        state_text.setText(userupdate.Data.State)
//                        country_text.setText(userupdate.Data.Country)
//                        gap_in_edu_text.setText(userupdate.Data.EducationGap)
//                        known_language_text.setText(userupdate.Data.Languages)
//                        chip_1.setText(userupdate.Data.Skills)
//                        chip_2.setText(userupdate.Data.Skills)
//                        chip_3.setText(userupdate.Data.Skills)
//
//
//                        if (userupdate.Data.Gender.equals("male")){
//                            male_1.isChecked
//                        }
//                        else{
//                            female_1.isChecked
//                        }

                        personal_info_layout.alpha = 0.5f
                        editbutton_edit.visibility = View.VISIBLE
                        personal_info_layout.alpha = 1.0f

                        Toast.makeText(getApplicationContext(),"Data Updated  successfully", Toast.LENGTH_SHORT).show()
                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {
                Toast.makeText(
                    this@PersonalInfo,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}