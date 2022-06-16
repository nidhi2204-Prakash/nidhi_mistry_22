package com.example.prakashjobapp.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.prakashjobapp.R
import com.example.prakashjobapp.SessionManager
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.Login
import com.example.prakashjobapp.models.ProfileStatusData
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.material.textfield.TextInputEditText as TextInputEditText1

class LoginActivity : AppCompatActivity() {

    lateinit var login_button: FrameLayout
    lateinit var Sign_up: TextView
    lateinit var email_phone: TextInputEditText1
    lateinit var forgot_password :TextView
    lateinit var password: TextInputEditText1
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    lateinit var strEmail :String
    lateinit var strPassword :String
    lateinit var strPhone :String
    lateinit var google_logo: Button
    lateinit var progressBar : ProgressBar
    private lateinit var sessionManager :SessionManager
    lateinit var loginText :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        google_logo = findViewById(R.id.google_logo)
        email_phone = findViewById(R.id.email_phone) as TextInputEditText1
        password = findViewById(R.id.password) as TextInputEditText1
        login_button = findViewById(R.id.login_button)
        forgot_password = findViewById(R.id.forgot_password)
        progressBar = findViewById(R.id.progressBar)
        Sign_up = findViewById(R.id.Sign_up)
        loginText = findViewById(R.id.login_textview)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sharedPreferences.edit()

        login_button.setOnClickListener {
            ValidationRules()
            }
        Sign_up.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        forgot_password.setOnClickListener {
             val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
        }
        google_logo.setOnClickListener {
        }
    }

    fun ValidationRules() {

        val emailAddress = Patterns.EMAIL_ADDRESS.matcher(email_phone.text.toString()).matches()
        val patternPassword = Regex("(.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}")
        val patternPhone =  ( Regex("((\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}"))

        if (!emailAddress) {
            if (patternPhone.containsMatchIn(password.text.toString())) {
                if (patternPassword.containsMatchIn(password.text.toString())) {
                    Toast.makeText(getApplicationContext(),resources.getString(R.string.Welcome), Toast.LENGTH_SHORT).show()
                    LoginApi()
                } else {
                    Toast.makeText(getApplicationContext(),resources.getString(R.string.password_valid) ,Toast.LENGTH_SHORT).show()
                }

            } else {

                Toast.makeText(getApplicationContext(), resources.getString(R.string.Email_or_phone), Toast.LENGTH_SHORT).show()
            }
        }
        else{
            progressBar.visibility = View.VISIBLE
            loginText.visibility = View.GONE
            LoginApi()
        }
    }

        fun LoginApi(){

            val email = email_phone.text.toString()
            val password = password.text.toString()


            RetrofitBuilder.JsonServices.jsonInstance.LoginData(email,password).enqueue(object : Callback<Login?> {
                override fun onResponse(call: Call<Login?>, response: Response<Login?>) {
                  //  val responseBody = response.body()!!
                    try {
                        System.out.println("--------------->>> 115")

                        val jsonParams = response.body()
                        val sessionManager = SessionManager(this@LoginActivity)
                        if (response.body()?.Status == 200) {
                            System.out.println("--------------->>> 121");
                            val id = jsonParams?.Data?.Id
                            val email = jsonParams?.Data?.Emailid
                            val firstname = jsonParams?.Data?.Firstname
                            val lastname = jsonParams?.Data?.Lastname
                            val gender = jsonParams?.Data?.Gender
                            val phone = jsonParams?.Data?.Mobileno
                            val password = jsonParams?.Data?.Password
                            sessionManager.putString(
                                SessionManager.KEY_ID,
                                java.lang.String.valueOf(id)
                            )
                            sessionManager.putString(
                                SessionManager.KEY_EMAIL,
                                java.lang.String.valueOf(email)
                            )
                            sessionManager.putString(
                                SessionManager.KEY_FIRST_NAME,
                                java.lang.String.valueOf(firstname)
                            )
                            sessionManager.putString(
                                SessionManager.KEY_LAST_NAME,
                                java.lang.String.valueOf(lastname)
                            )
                            sessionManager.putString(
                                SessionManager.KEY_GENDER,
                                java.lang.String.valueOf(gender)
                            )
                            sessionManager.putString(
                                SessionManager.KEY_PHONE,
                                java.lang.String.valueOf(phone)
                            )
                            sessionManager.putString(
                                SessionManager.KEY_PASSWORD,
                                java.lang.String.valueOf(password)
                            )
                                CallAuthentication()

                        } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Email and password are incorrect!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } catch (e: JSONException){
                           e.printStackTrace()
                           e.message?.let { Log.e("errorrr", it) }

                    }
                }

                override fun onFailure(call: Call<Login?>, t: Throwable) {
                    System.out.println("--------------->>> 183")
                    Toast.makeText(
                        this@LoginActivity,
                        t.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }

    private fun CallAuthentication() {
        val userName = email_phone.text.toString()
        val password = password.text.toString()

        val user = RetrofitBuilder.JsonServices.jsonInstance.tokenGenerate(userName,password,"password")
        user.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                try {
                    val sessionManager = SessionManager(this@LoginActivity)
                    val authToken = response.body()
                    if (authToken != null && response.code() == 200 ){

                        val jsonString = response.body()!!.string()
                        val jsonObject = JSONObject(jsonString)
                        val token =jsonObject.getString("access_token")
                        sessionManager.putString(SessionManager.USER_TOKEN,token)
                        userProfileStatus()
                    }
                 else{

                        Toast.makeText(this@LoginActivity, "Something Went Wrong", Toast.LENGTH_SHORT).show()

                    }
                }catch (e: JSONException){
                    e.printStackTrace()
                }
                catch (e :NullPointerException){
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(
                    this@LoginActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    fun userProfileStatus(){
        sessionManager = SessionManager(this)
        val id = sessionManager.getString(SessionManager.KEY_ID).toString()
        val id1 = id.toInt()
        RetrofitBuilder.JsonServices.jsonInstance.userProfileStatus(id1).enqueue(object : Callback<ProfileStatusData?> {
            override fun onResponse(
                call: Call<ProfileStatusData?>,
                response: Response<ProfileStatusData?>
            ) {
                try {
                    val profileStatus = response.body()
                    if (profileStatus!!.Status == 200){
                        sessionManager.putBoolean(SessionManager.KEY_USER_LOGIN, true)
                        startActivity(Intent(this@LoginActivity,DashboardActivity::class.java))
                    }
                    else {
                        startActivity(Intent(this@LoginActivity,PersonalInfo::class.java))
                    }
                }catch (e : JSONException){
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ProfileStatusData?>, t: Throwable) {
                Toast.makeText(
                    this@LoginActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
