package com.example.prakashjobapp.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.children
import androidx.core.view.isInvisible
import com.bumptech.glide.Glide
import com.example.prakashjobapp.R
import com.example.prakashjobapp.SessionManager
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
import com.example.prakashjobapp.models.*
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.gson.Gson
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
    lateinit var Update_btn: FrameLayout
    lateinit var update_textview: TextView
    lateinit var person_Name: TextView
    lateinit var Profile_Button: CircleImageView
    lateinit var iv_camera: AppCompatImageView
    lateinit var personal_info_layout: LinearLayout
    lateinit var editbutton_edit: ImageView
    lateinit var Date_of_birth_edit: EditText
    lateinit var chipGroup: ChipGroup

    //    lateinit var DOB_text: TextView
    lateinit var FirstName_Text: EditText
    lateinit var Last_Name_2_Text: EditText
    lateinit var email_id: EditText
    lateinit var password_edit: EditText
    lateinit var mobile_no_text: EditText
    lateinit var address_text: EditText
    lateinit var city_text: EditText
    lateinit var state_text: EditText
    lateinit var country_text: EditText
    lateinit var gap_in_edu_text: EditText
    lateinit var uploadResume_button: Button
    lateinit var known_language_text: EditText
    lateinit var male_1: RadioButton
    lateinit var female_1: RadioButton
    lateinit var radioGroup: RadioGroup
    lateinit var next_button: Button
    lateinit var progressBar1: ProgressBar
    private lateinit var objPersonalInfoData: PersonalInfoData
    private lateinit var objCompanyInfoData: CompanyInfoData
    private lateinit var objEducationInfoData: EducationInfoData
    private lateinit var sessionManager: SessionManager
    lateinit var chip_1: Chip
    lateinit var chip_2: Chip
    lateinit var chip_3: Chip
    private var year = 0
    private var month = 0
    private var day = 0
    private lateinit var calendar: Calendar
    private val pickImage = 100
    private var imageUri: Uri? = null
    private var imageByte: String? = null


    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val STORAGE_PERMISSION_CODE = 101
        private val REQUEST_IMAGE_CAPTURE = 1
        private val REQUEST_PICK_IMAGE = 2
    }


    fun updateProfileValidation(){

        val patternPassword = Regex("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}")

        if (FirstName_Text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.First_Name_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (Last_Name_2_Text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Last_Name_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (email_id.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Email_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (!patternPassword.matches(password_edit.text.toString())) {
            Toast.makeText(
                this,
                resources.getString(R.string.password_valid),
                Toast.LENGTH_SHORT
            ).show()
        } else if (mobile_no_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Phone_number_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (Date_of_birth_edit.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Please_Enter_Valid_Date),
                Toast.LENGTH_SHORT
            ).show()
        } else if (address_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Address_Should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (city_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.City_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (state_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.State_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (country_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Country_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }else if (gap_in_edu_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.gap_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (known_language_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.known_language_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (uploadResume_button.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Country_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else{
            progressBar1.visibility = View.VISIBLE
            update_textview.visibility = View.GONE
            updateProfile()
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)

        sessionManager = SessionManager(this)
        chipGroup = findViewById(R.id.chip_group)
        update_textview = findViewById(R.id.update_textview)
        male_1 = findViewById(R.id.male_1)
        female_1 = findViewById(R.id.female_1)
        FirstName_Text = findViewById(R.id.FirstName_Text)
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
        progressBar1 = findViewById(R.id.progressBar1)
        addChipCode()

        personal_info_backarrow.setOnClickListener {
            if (editbutton_edit.isInvisible) {
                onBackPressed()
            } else {
                alertDialog()
            }
        }
    //UpdateButton
        Update_btn.setOnClickListener {

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
            Log.d("imageByte_Update", imageByte.toString())
            ProfileImage = imageByte.toString()
            skill1 = chip_1.text.toString()
            skill2 = chip_2.text.toString()
            skill3 = chip_3.text as String
            val selectedId = radioGroup.checkedRadioButtonId
            var rb: RadioButton
            rb = findViewById<View>(selectedId) as RadioButton
            gender = rb.text.toString()
            companyNameCI = ""
            currentDesignination = ""
            jobTypwCI = ""
            employmentTypeCI = ""
            totalExp = ""
            GapinWorkExp = " "
            noticePeriod = ""
            department = ""
            currentCTC = ""
            expectedCTC = ""
            qualification = ""
            board_university = ""
            passingYear = ""
            percentage = ""
            updateProfileValidation()
            objPersonalInfoData = PersonalInfoData(firstName = fName, lastName = lName, email = email, password = password, mobileNo = mobileNo
            , dateOfBirth = dateOfBirth, city = city, state = state, country = country, gapInEducation = gapInedu, resume = uploadResume, knownlanguage = knownLanguage
            , address = address, gender = gender, profilePhoto = ProfileImage, skills = skill1)
            objCompanyInfoData = CompanyInfoData(companyName = companyNameCI,
                currentDesignation = currentDesignination,
                jobType = jobTypwCI,
                employmentType = JobDescription.employmentType,
                totalExp = totalExp,
                department = department,
                noticePeriod = noticePeriod,
                gapInWorkExpirence = GapinWorkExp,
                currentCTC = currentCTC,
                expectedCTC = expectedCTC)
            objEducationInfoData = EducationInfoData(
                Qualification = qualification,
                boardUniversity = board_university,
                passingYear = passingYear,
                percentage = percentage)

        }
        //NextButton
        next_button.setOnClickListener {

            val fName: String = FirstName_Text.text.toString()
            val lNAme: String = Last_Name_2_Text.text.toString()
            val email: String = email_id.text.toString()
            val password: String = password_edit.text.toString()
            val city: String = city_text.text.toString()
            val state: String = state_text.text.toString()
            val country: String = country_text.text.toString()
            val knownLanguages: String = known_language_text.text.toString()
            val uploadresume: String = uploadResume_button.text.toString()
            val gapinEdu: String = gap_in_edu_text.text.toString()
            val birthdate: String = Date_of_birth_edit.text.toString()
            val address: String = address_text.text.toString()
            val mobileNo: String = mobile_no_text.text.toString()
            val profileimage: String = Profile_Button.toString()
            val gender: String
            val skill = ""
            val selectedGenderId = radioGroup.checkedRadioButtonId
            val radioButton: RadioButton = findViewById(selectedGenderId)
            val selectedGender: String = radioButton.text.toString()

            if (!TextUtils.isEmpty(selectedGender) && selectedGender.equals("Male")) {
                gender = "M"
            } else {
                gender = "F"
            }
            val objPersonalInfoData = PersonalInfoData(
                profilePhoto = profileimage,
                firstName = fName,
                lastName = lNAme,
                email = email,
                password = password,
                mobileNo = mobileNo,
                dateOfBirth = birthdate,
                address = address,
                city = city,
                state = state,
                country = country,
                gapInEducation = gapinEdu,
                gender = gender,
                knownlanguage = knownLanguages,
                skills = skill,
                resume = uploadresume
            )
            val gson = Gson()
            val jsonString = gson.toJson(objPersonalInfoData)
            ValidPersonalInfo(jsonString)
        }

        iv_camera.setOnClickListener {
            selectImage()
        }
        uploadResume_button.setOnClickListener {
            val intent = Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT)
                .addCategory(Intent.CATEGORY_OPENABLE)
            val extraMimeTypes = arrayOf("application/docs", "application/doc", "application/pdf")
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
                val myFormat = "yyy/MM/dd"
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

    fun fieldEnabled() {

        personal_info_layout.alpha = 0.5f
        editbutton_edit.setOnClickListener {
            val unwrappedDrawable =
                AppCompatResources.getDrawable(this, R.drawable.ic_baseline_camera_alt_24)
            val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
            DrawableCompat.setTint(wrappedDrawable, resources.getColor(R.color.orange))
            personal_info_layout.alpha = 1.0f
            personal_info_layout.isFocusable = true
            FirstName_Text.isEnabled = true
            Last_Name_2_Text.isEnabled = true
            password_edit.isEnabled = true
            address_text.isEnabled = true
            city_text.isEnabled = true
            state_text.isEnabled = true
            country_text.isEnabled = true
            gap_in_edu_text.isEnabled = true
            uploadResume_button.isFocusable = true
            known_language_text.isEnabled = true
            Update_btn.isEnabled = true
            Date_of_birth_edit.isEnabled = true
            Profile_Button.isEnabled = true
            chip_1.isEnabled = true
            chip_2.isEnabled = true
            chip_3.isEnabled = true
        }
    }

    fun ValidPersonalInfo(jsonString: String) {

        val patternPassword = Regex("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}")
        val patternPhone = (Regex("^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}"))

        if (FirstName_Text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.First_Name_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (Last_Name_2_Text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Last_Name_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (email_id.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Email_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        }
        else if (!patternPassword.matches(password_edit.text.toString())) {
            Toast.makeText(
                this,
                resources.getString(R.string.password_valid),
                Toast.LENGTH_SHORT
            ).show()
        } else if (mobile_no_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Phone_number_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (patternPhone.containsMatchIn(mobile_no_text.text.toString())){
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Phone_number_should_contain_only_nimber),
                Toast.LENGTH_SHORT
            ).show()
        } else if (Date_of_birth_edit.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Please_Enter_Valid_Date),
                Toast.LENGTH_SHORT
            ).show()
        } else if (address_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Address_Should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (city_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.City_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (state_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.State_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (country_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Country_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (gap_in_edu_text.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.gap_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (uploadResume_button.getText().toString().isEmpty()) {
            Toast.makeText(
                this,
                resources.getString(R.string.Country_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else{
            val intent = Intent(this, CompanyInfo::class.java)
            intent.putExtra(KeyClass.PERSONAL_INFO_DATA, jsonString)
            startActivity(intent)
        }
    }

    @SuppressLint("Range")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1) {
            val filePath = data?.getData();
            val path_New = filePath?.getPath();
            imageUri = data?.data
            val uriString = imageUri.toString()
            val myFile1 = File(uriString)
            val path1 = myFile1.absolutePath
            val path2: String = myFile1.getAbsolutePath()
            Log.d("ImageFile", myFile1.getAbsolutePath().toString())
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val bytes = stream.toByteArray()
            Log.d("byte", bytes.toString())
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
            val uriString = uri.toString()
            val myFile1 = File(uriString)
            val path1 = myFile1.absolutePath
            var displayName: String? = null
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

    //DisplayUserApi
    fun displayUser() {
        val id: String? = sessionManager.getString(SessionManager.KEY_ID)
        val id1: Int = id!!.toInt()
        RetrofitBuilder.JsonServices.jsonInstance.displayProfile(id1)
            .enqueue(object : Callback<DisplayUser?> {
                @SuppressLint("StringFormatMatches")
                override fun onResponse(
                    call: Call<DisplayUser?>, response: Response<DisplayUser?>,
                ) {
                    //          Log.d("TAG", "Display User " + response.body()!!.Data)
                    try {

                        val userDisplay = response.body()

                        if (userDisplay != null && response.code() == 200) {

                            val firstName = userDisplay.Data.Firstname
                            val lastName = userDisplay.Data.Lastname
                            val profileImage = userDisplay.Data.ProfilePhoto
                            val email = userDisplay.Data.Emailid
                            val password = userDisplay.Data.Password
                            val phone = userDisplay.Data.Mobileno
                            val address = userDisplay.Data.Address
                            val city = userDisplay.Data.City
                            val state = userDisplay.Data.State
                            val country = userDisplay.Data.Country
                            val gapInedu = userDisplay.Data.EducationGap
                            val resume = userDisplay.Data.ResumeUpload
                            if (email != null && phone != null) {
                                if ((email.isNotEmpty()) && (phone.isEmpty())) {
                                    email_id.isEnabled = false
                                    mobile_no_text.isEnabled = true
                                } else if ((email.isEmpty()) && (phone.isNotEmpty())) {
                                    email_id.isEnabled = true
                                    mobile_no_text.isEnabled = false
                                } else if ((email.isNotEmpty()) && (phone.isNotEmpty())) {
                                    email_id.isEnabled = false
                                    mobile_no_text.isEnabled = false
                                }
                            }
                            person_Name.text = firstName + " " + lastName

                            FirstName_Text.setText(userDisplay.Data.Firstname)
                            //setimage
                            Glide.with(this@PersonalInfo).load(profileImage)
                                .placeholder(R.drawable.ic_baseline_person_24)
                                .into(Profile_Button)
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

                            if (userDisplay.Data.Gender.equals("male")) {
                                male_1.isChecked
                            } else {
                                female_1.isChecked
                            }

                           if (userDisplay.Data != null && userDisplay.Data.DateOfBirth.isNullOrEmpty()) {

                                //for check
                                editbutton_edit.visibility = View.INVISIBLE
                                next_button.visibility = View.VISIBLE
                                personal_info_layout.isFocusable = true
                                FirstName_Text.isEnabled = true
                                Last_Name_2_Text.isEnabled = true
                                password_edit.isEnabled = true
                                address_text.isEnabled = true
                                city_text.isEnabled = true
                                state_text.isEnabled = true
                                country_text.isEnabled = true
                                gap_in_edu_text.isEnabled = true
                                uploadResume_button.isFocusable = true
                                known_language_text.isEnabled = true
                                Update_btn.isEnabled = true
                                Date_of_birth_edit.isEnabled = true
                                Profile_Button.isEnabled = true
                                chip_1.isEnabled = true
                                chip_2.isEnabled = true
                                chip_3.isEnabled = true
                                chipGroup.isEnabled = true
                                for (chip in chipGroup.children) {
                                    chip.isEnabled = true
                                }
                            } else {
                                editbutton_edit.visibility = View.VISIBLE
                                fieldEnabled()
                                Update_btn.visibility = View.VISIBLE
                            }
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    } catch (e: NullPointerException) {
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
    //Update Api
    fun updateProfile() {
        var personalInfoData: PersonalInfoData =
            PersonalInfoData("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
        var companyInfoData: CompanyInfoData =
            CompanyInfoData("", "", "", "", "", "", "", "", "", "")
        var educationInfoData: EducationInfoData = EducationInfoData("", "", "", "")
        val fName = FirstName_Text.text.toString()
        val lName = Last_Name_2_Text.text.toString()
        val email = email_id.text.toString()
        val password = password_edit.text.toString()
        val mobileNo = mobile_no_text.text.toString()
        val address = address_text.text.toString()
        val city = city_text.text.toString()
        val state = state_text.text.toString()
        val country = country_text.text.toString()
        val gapInedu = gap_in_edu_text.text.toString()
        val uploadResume = uploadResume_button.text.toString()
        val dateOfBirth = Date_of_birth_edit.text.toString()
        val knownLanguage = known_language_text.text.toString()
        Log.d("imageByte_Update", imageByte.toString())
        val ProfileImage = imageByte.toString()
        val skill1 = chip_1.text.toString()
        val skill2 = chip_2.text.toString()
        val skill3 = chip_3.text as String
        val selectedGenderId = radioGroup.checkedRadioButtonId
        val radioButton: RadioButton = findViewById(selectedGenderId)
        val selectedGender: String = radioButton.text.toString()

        val selectedId = radioGroup.checkedRadioButtonId
        var rb: RadioButton
        rb = findViewById<View>(selectedId) as RadioButton
        gender = rb.text.toString()
        companyNameCI = ""
        currentDesignination = ""
        jobTypwCI = ""
        employmentTypeCI = ""
        totalExp = ""
        GapinWorkExp = " "
        noticePeriod = ""
        department = ""
        currentCTC = ""
        expectedCTC = ""
        qualification = ""
        board_university = ""
        passingYear = ""
        percentage = ""

        if (!TextUtils.isEmpty(selectedGender) && selectedGender.equals("Male")) {
            gender = "M"
        } else {
            gender = "F"
        }

        personalInfoData = PersonalInfoData(
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
        val objCompanyInfoData = CompanyInfoData(
            companyName = companyNameCI,
            currentDesignation = currentDesignination,
            jobType = jobTypwCI,
            employmentType = employmentTypeCI,
            totalExp = totalExp,
            department = department,
            noticePeriod = noticePeriod,
            gapInWorkExpirence = GapinWorkExp,
            currentCTC = currentCTC,
            expectedCTC = expectedCTC
        )
        companyInfoData = objCompanyInfoData
        val objEducationInfoData = EducationInfoData(
            Qualification = qualification,
            boardUniversity = board_university,
            passingYear = passingYear,
            percentage = percentage
        )
        educationInfoData = objEducationInfoData
        sessionManager = SessionManager(this)
        val id: String? = sessionManager.getString(SessionManager.KEY_ID)
        val id1: Int = id!!.toInt()
        Log.d("Update_Image", ProfileImage)
        RetrofitBuilder.JsonServices.jsonInstance.updateProfile(RequestParameters().userProfileUpdate(
            id1,
            personalinfo = personalInfoData,
            companyInfo = companyInfoData,
            eduationInfo = educationInfoData,
        )).enqueue(object : Callback<DisplayUser?> {
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
                Log.d("TAG", "Update User " + response.body()!!.Data)

                try {
                    val userUpdate = response.body()
                    if (userUpdate != null && response.code() == 200) {
                        println("Goood  Evening-------------------- Hey There")
                        Toast.makeText(
                            this@PersonalInfo,
                            "Data Updated  successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        userUpdate.Data.UserCompanyInfoId
                        userUpdate.Data.UserEducationId
                        startActivity(Intent(this@PersonalInfo, DashboardActivity::class.java))
                    } else {
                        Toast.makeText(this@PersonalInfo, "Try Again", Toast.LENGTH_SHORT).show()

                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                } catch (e: NullPointerException) {
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

    fun addChipCode() {
        val genres =
            arrayOf(".net", "C#", "c++",
                "Java", "Python", "Android",
                "Flutter", "Kotlin", "Communication")
        for (genre in genres) {
            val chip = Chip(chipGroup.context)
            chip.text = genre
            chip.isCheckable = true
            chip.isEnabled = false
            chipGroup.addView(chip)
            chipGroup.checkedChipId
        }
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

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this,
                permission) == PackageManager.PERMISSION_DENIED
        ) {
            // Requesting the permission
            ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
        } else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun selectImage() {
        val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Photo!")
        builder.setItems(options) { dialog, item ->
            if (options[item] == "Take Photo") {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                val f = File(Environment.getExternalStorageDirectory(), "temp.jpg")
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f))
                checkPermission(android.Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)
                startActivityForResult(intent, 1)
            } else if (options[item] == "Choose from Gallery") {
                val intent = Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 2)
            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        }
        builder.show()
    }
}