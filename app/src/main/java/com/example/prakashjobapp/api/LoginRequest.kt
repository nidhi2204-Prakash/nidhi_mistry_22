package com.example.prakashjobapp.api

import android.util.Log
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class LoginRequest {
    fun authenticateLogin(email: String?,password: String?,):RequestBody{
        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("password", password)
        val jsonObjectString = jsonObject.toString()
        Log.d("rob",jsonObjectString)

        val jSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        val body: RequestBody = RequestBody.create(jSON, jsonObjectString)

        return body
    }
}



