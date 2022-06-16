package com.example.prakashjobapp.api

import android.util.Log
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject

class TokenBody {

    fun authenticateUser(

        username: String?,
        password: String?

    ): RequestBody {

        // Create JSON using JSONObject
        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)
        jsonObject.put("grant_type", "password")
        val jsonObjectString = jsonObject.toString()
        Log.d("rob",jsonObjectString)

        val jSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        val body: RequestBody = RequestBody.create(jSON, jsonObjectString)

        return body
    }


}