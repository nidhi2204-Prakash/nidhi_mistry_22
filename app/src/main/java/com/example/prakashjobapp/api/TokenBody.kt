package com.example.prakashjobapp.api

import android.util.Log
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class TokenBody {

    fun authenticateUser(

        username: String?,
        password: String?
//        Phone: String?,
//        Psw: String?,
//        gender: String
    ): RequestBody {

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)
        jsonObject.put("grant_type", "password")
      //  jsonObject.put("Phone", Phone)
   //     jsonObject.put("Psw", Psw)
      //  jsonObject.put("gender", gender)

        // Convert JSONObject to String
        val jsonObjectString = jsonObject.toString()
        Log.d("rob",jsonObjectString)

        val jSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        val body: RequestBody = RequestBody.create(jSON, jsonObjectString)

        return body
    }


}