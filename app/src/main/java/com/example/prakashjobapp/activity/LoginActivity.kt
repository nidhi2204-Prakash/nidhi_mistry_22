package com.example.prakashjobapp.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.prakashjobapp.R
import com.example.prakashjobapp.SessionManager
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.helper.Constant
import com.example.prakashjobapp.models.Login
import com.example.prakashjobapp.models.ProfileStatusData
import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.material.textfield.TextInputEditText as TextInputEditText1

class LoginActivity : AppCompatActivity() {

    lateinit var login_button: Button
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        google_logo = findViewById(R.id.google_logo)
        email_phone = findViewById(R.id.email_phone) as TextInputEditText1
        password = findViewById(R.id.password) as TextInputEditText1
        login_button = findViewById(R.id.login_button)
        forgot_password = findViewById(R.id.forgot_password)
        Sign_up = findViewById(R.id.Sign_up)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sharedPreferences.edit()
    //   checkSharedPreference()

        login_button.setOnClickListener {
//            val intent = Intent(this, DashboardActivity::class.java)
//            startActivity(intent)
         ValidationRules()
//            userProfileStatus()
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
//            val intent = Intent(this, DashboardActivity::class.java)
//            startActivity(intent)
        }

    }

    fun ValidationRules() {

        val emailAddress = Patterns.EMAIL_ADDRESS.matcher(email_phone.text.toString()).matches()
      //  val patternPassword = Regex("(.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}")
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
           Toast.makeText(getApplicationContext(),resources.getString(R.string.API_call) ,Toast.LENGTH_SHORT).show()
            LoginApi()
        }
    }

        fun LoginApi(){

            val email = email_phone.text.toString()
            val password = password.text.toString()
           // var phone = ""

            RetrofitBuilder.JsonServices.jsonInstance.LoginData(email,password).enqueue(object : Callback<Login?> {
                override fun onResponse(call: Call<Login?>, response: Response<Login?>) {
                  //  val responseBody = response.body()!!
                    try {
                        System.out.println("--------------->>> 115")

                        val jsonParams = response.body()
                      //  val tokenbody = TokenBody()
                        val sessionManager = SessionManager(this@LoginActivity)
                        val constant = Constant()
                        if (response.body()?.Status == 200) {
                            System.out.println("--------------->>> 121");
                            val id = jsonParams?.Data?.Id
                            val email = jsonParams?.Data?.Emailid
                            val firstname = jsonParams?.Data?.Firstname
                            val lastname = jsonParams?.Data?.Lastname
                            val gender = jsonParams?.Data?.Gender
                            val phone = jsonParams?.Data?.Mobileno
                            val password = jsonParams?.Data?.Password
                          //  val token = jsonParams?.Data?.toString()

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

//                            sessionManager.putString(
//                                SessionManager.USER_TOKEN,
//                                java.lang.String.valueOf(token)
//                            )
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
//                        System.out.println("--------------->>> 176")
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
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val sessionManager = SessionManager(this@LoginActivity)
                    val jsonparams = response.body()
                    val jsonString = jsonparams!!.string()
                    val constant = Constant()
                    val jsonObject = JSONObject(jsonString)
                    val token =jsonObject.getString("access_token")
                    sessionManager.putString(SessionManager.USER_TOKEN ,token)
                    sessionManager.putBoolean(SessionManager.KEY_USER_LOGIN, true)
//                    constant.putBoolean(Constant.KEY_FLAG,true)

                    if (jsonparams != null && response.code() == 200 ){
                        var dashActivity  = Intent(this@LoginActivity,DashboardActivity::class.java)
                        dashActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(dashActivity)
                        finish()
                        userProfileStatus()
                    }
                 else{
                        startActivity(Intent(this@LoginActivity,PersonalInfo::class.java))
                        startActivity(intent)
                    }

                   // intent.addFlags(constant.putBoolean(Constant.KEY_FLAG,true))

                }catch (e: JSONException){
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
        RetrofitBuilder.JsonServices.jsonInstance.userProfileStatus(128).enqueue(object : Callback<ProfileStatusData?> {
            override fun onResponse(
                call: Call<ProfileStatusData?>,
                response: Response<ProfileStatusData?>
            ) {
                try {
                    val profileStatus = response.body()
                    if (profileStatus != null && response.code() == 200){
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


//        RetrofitBuilder.JsonServices.jsonInstance.fetchToken(TokenBody().authenticateUser(username = userName,
//            password = password)).enqueue(object : Callback<Token?> {
//            override fun onResponse(call: Call<Token?>, response: Response<Token?>) {
//                try {
//
//                    val jsonParams = response.body()
//                    //  val tokenbody = TokenBody()
//                    val constant = Constant()
//                    val sessionManager = SessionManager(this@LoginActivity)
//
//                    if (jsonParams!=null){
//                        val token = jsonParams?.toString()
//                        Log.d("Nidhi", jsonParams.access_token)
//                        sessionManager.putString(
//                                SessionManager.USER_TOKEN,
//                                 java.lang.String.valueOf(token)
//                           )
//                        sessionManager.putBoolean(SessionManager.KEY_USER_LOGIN, true)
//
//
//
//            }catch (eX :Exception){
//                    eX.printStackTrace()
//
//                }catch (e :JSONException){
//                e.printStackTrace()
//
//                }
//            }
//
//            override fun onFailure(call: Call<Token?>, t: Throwable) {
//
//            }
//        })
        //                    if (jsonparams!= null&& response.code() == 200){
////                        val userName: JSONObject = obj.getJSONObject("user_name")
////                        val password: JSONObject = obj.getJSONObject("password")
//
//                    }





