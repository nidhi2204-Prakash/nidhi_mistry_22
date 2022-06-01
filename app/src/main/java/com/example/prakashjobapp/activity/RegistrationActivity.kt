package com.example.prakashjobapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.prakashjobapp.R
import com.example.prakashjobapp.api.RequestParameters
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.Registration
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    lateinit var signup: FrameLayout
    lateinit var Login_text: TextView
    lateinit var email_phone_layout: TextInputEditText
    lateinit var password_Layout: TextInputEditText
    lateinit var firstname: TextInputEditText
    lateinit var lastname: TextInputEditText
    private lateinit var genderRadioGroup: RadioGroup
    lateinit var progressBar1 :ProgressBar
     lateinit var sign_up_textview :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        genderRadioGroup = findViewById(R.id.RadioGroup)
        firstname = findViewById(R.id.firstname) as TextInputEditText
        lastname = findViewById(R.id.lastname) as TextInputEditText
        email_phone_layout = findViewById(R.id.email_phone_layout) as TextInputEditText
        password_Layout = findViewById(R.id.password_Layout) as TextInputEditText
        Login_text = findViewById(R.id.Login_text)
        progressBar1= findViewById(R.id.progressBar)
        sign_up_textview = findViewById(R.id.sign_up_textview)
        signup = findViewById(R.id.signup)
        signup.setOnClickListener {

//            progressBar1.visibility = View.VISIBLE
//            sign_up_textview.visibility = View.GONE
            ValidationRules()
        }
        Login_text.setOnClickListener {
            val  intent = Intent(this,LoginActivity ::class.java)
            startActivity(intent)
        }
    }

    var emailAddress: Boolean = false
    val patternPhone: Boolean = true

    fun ValidationRules() {
        emailAddress = Patterns.EMAIL_ADDRESS.matcher(email_phone_layout.text.toString()).matches()
        //  val  patternPhone =(Regex("[789]\\d{9}"))
        val patternPhone = (Regex("(\\+[0-9]+[\\- \\.]*)?" +
                "(\\([0-9]+\\)[\\- \\.]*)?" +
                "([0-9][0-9\\- \\.]+[0-9])" +
                ".{7,}"))

        val patternPassword = Regex("(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}")

        if (firstname.getText().toString().isEmpty()) {
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.First_Name_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (lastname.getText().toString().isEmpty()) {
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.Last_Name_should_not_be_emptied),
                Toast.LENGTH_SHORT
            ).show()
        } else if (!emailAddress) {

            if (patternPhone.containsMatchIn(password_Layout.text.toString())) {
                if (patternPassword.containsMatchIn(password_Layout.text.toString())) {
                    // Toast.makeText(getApplicationContext(), resources.getString(R.string.Welcome), Toast.LENGTH_SHORT).show()
                    signUpData()
                } else {
                    Toast.makeText(
                        getApplicationContext(),
                        resources.getString(R.string.Invalid_Password),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    getApplicationContext(),
                    resources.getString(R.string.Email_or_phone),
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else if (!patternPassword.matches(password_Layout.text.toString())) {
            Toast.makeText(
                getApplicationContext(),
                resources.getString(R.string.password_valid),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            progressBar1.visibility = View.VISIBLE
            sign_up_textview.visibility = View.GONE
            // Toast.makeText(getApplicationContext(), resources.getString(R.string.API_call), Toast.LENGTH_SHORT).show()
            signUpData()
        }
    }

    @SuppressLint("LongLogTag")
    fun signUpData() {
        val firstName = firstname.text.toString()
        val lastName = lastname.text.toString()
        var email = ""
        var phone = ""
        val paswword = password_Layout.text.toString()
        // val gender = genderRadioGroup.checkedRadioButtonId
        val gender: String
        val selectedGenderId = genderRadioGroup.checkedRadioButtonId
        val radioButton: RadioButton = findViewById(selectedGenderId)
        val selectedGender: String = radioButton.text.toString()

        if (!TextUtils.isEmpty(selectedGender) && selectedGender.equals("Male")) {
            gender = "M"
        } else {
            gender = "F"
        }

        if (emailAddress) {
            email = email_phone_layout.text.toString()
            phone = ""
        } else if (patternPhone) {
            email = ""
            phone = email_phone_layout.text.toString()
        }

        RetrofitBuilder.JsonServices.jsonInstance.signUp(RequestParameters().createUser(
            Firstname = firstName,
            Lastname = lastName,
            Emailid = email,
            Mobileno = phone,
            Password = paswword,
            Gender = gender,
        )).enqueue(object : Callback<Registration?> {
            override fun onResponse(call: Call<Registration?>, response: Response<Registration?>) {

                try {
                    if (response.body()?.Status.equals("200")) {
                        startActivity(Intent(this@RegistrationActivity, LoginActivity::class.java))
                        finish()
                    } else if (response.body()?.Status.equals("201")) {
                        Toast.makeText(
                            this@RegistrationActivity,
                            response.body()?.Message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }else {
                        Toast.makeText(
                            this@RegistrationActivity,
                            "Try Again !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<Registration?>, t: Throwable) {
                Toast.makeText(
                    this@RegistrationActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
