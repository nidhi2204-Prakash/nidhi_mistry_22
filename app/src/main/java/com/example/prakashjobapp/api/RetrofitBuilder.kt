package com.example.prakashjobapp.api

import com.example.prakashjobapp.SessionManager
import com.prakash.prakashjobapp.JobApplication
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitBuilder {

    object JsonServices {

        val jsonInstance: ApiInterface

        init {
            val sessionManager = SessionManager(JobApplication.applicationContext())
            val token = sessionManager.getString(SessionManager.USER_TOKEN)

            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(30, TimeUnit.SECONDS)
            httpClient.readTimeout(30, TimeUnit.SECONDS)
            httpClient.addInterceptor { chain ->
                val request: Request =
                    chain.request().newBuilder().addHeader("Authorization", "Bearer $token")
                        .addHeader("Content-Type", "application/json; charset=UTF-8").build()
                chain.proceed(request)
            }
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()) // Add our Okhttp client
                .build()

            jsonInstance = retrofit.create(ApiInterface::class.java)

        }
    }
}
