package com.example.prakashjobapp

import android.content.Context
import android.content.SharedPreferences

class SessionManager {

    var pref: SharedPreferences
    var edior: SharedPreferences.Editor
    var PREF_FILE = "PrakashJobApp"
    var context: Context
    var PRIVATE_MODE: Int = 0

    constructor(context: Context) {

        this.context = context
        pref = context.getSharedPreferences(PREF_FILE, PRIVATE_MODE)
        edior = pref.edit()
    }

    companion object {
        const val KEY_USERID = "Key_userData"
        const val KEY_USER_LOGIN = "isLoggedIn"
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"
        const val KEY_ID = "USER_ID"
        const val KEY_FIRST_NAME = "firstname"
        const val KEY_LAST_NAME = "lastname"
        const val KEY_GENDER = "gender"
        const val KEY_PHONE = "phone"
        const val USER_TOKEN = "user_token"
        const val KEY_FLAG ="Isloggedin"

}
    fun putString(Key_name: String, value: String) {

        edior.putString(Key_name, value)
        edior.putString(Key_name, value)
        edior.apply()
    }
    fun putBoolean(Key_name: String, isLoggedIn: Boolean) {
        edior.putBoolean(Key_name, isLoggedIn)
        edior.apply()
    }
    fun isLoggedIn(): Boolean {
        return pref.getBoolean(KEY_USER_LOGIN,false)
         return pref.getBoolean(KEY_FLAG,false)

    }
    fun fetchAuthToken(): String? {
        return pref.getString(USER_TOKEN, null)
    }
    fun getString(key_name: String):String?{
        return pref.getString(key_name,null)
    }
}




//    companion object {
//        const val KEY_USERID = "Key_userData"
//        const val KEY_USER_LOGIN = "isLoggedIn"
//        const val KEY_EMAIL = "email"
//        const val KEY_PASSWORD = "password"
//        const val KEY_ID = "USER_ID"
//        const val KEY_FIRST_NAME = "firstname"
//        const val KEY_LAST_NAME = "lastname"
//        const val KEY_GENDER = "gender"
//        const val KEY_PHONE = "phone"
//
//    }
//
//    var privateMode = 0
//    var PREF_FILE = "PrakashJobApp"
//    var pref = context.getSharedPreferences(PREF_FILE, privateMode)
//    var prefEditor = pref.edit()
//
//    fun putString(Key_name: String, value: String) {
//        prefEditor.putString(Key_name, value)
//        prefEditor.apply()
//    }
//
//    fun putBoolean(Key_name: String, isLoggedIn: Boolean) {
//        prefEditor.putBoolean(Key_name, isLoggedIn)
//        prefEditor.apply()
//
//    }
//
//    fun isLoggedIn(): Boolean {
//        return pref.getBoolean(KEY_USER_LOGIN,false)
//    }
