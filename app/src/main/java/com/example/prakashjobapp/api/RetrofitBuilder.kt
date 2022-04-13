package com.example.prakashjobapp.api

import com.example.prakashjobapp.SessionManager
import com.prakash.prakashjobapp.JobApplication
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//const val token = "BhVP_N4tbDuWSYiUsLXAWIySkRnrfcnBXRjBGTRCVVQ8K5rmt27XWlzXvSjiGNg_3O3MQZiCGkVLhFs6xbL5suDz7OmtQ0v_Cl8vF8eUvWnPbzc9Qjzea-1dfmlpyJljzNh363BaQ3JOlKtq7YXyNmPRMQmCBZndbrVcnXVwqBRCEtBlSEh1RB6gRg_KeE7OhcNPbfz_uydUsF4OCnBlbrevW1gHrCZmBQwDwQgHE1mjQcTqp3Abf24j13sYi2YXuQ-M7_nSqACpNVHrIJJvnw"
class RetrofitBuilder {

    object JsonServices {
      //  const val TOKEN_URL = "https://prakashjob.azurewebsites.net/token"
        val jsonInstance: ApiInterface

        init {
            val sessionManager = SessionManager(JobApplication.applicationContext())
            val token =sessionManager.getString(SessionManager.USER_TOKEN)

            val httpClient = OkHttpClient.Builder()
            httpClient.connectTimeout(30,TimeUnit.SECONDS)
            httpClient.readTimeout(30,TimeUnit.SECONDS)
            httpClient.addInterceptor { chain ->
                val request: Request =
                    chain.request().newBuilder().addHeader("Authorization", "Bearer $token").addHeader("Content-Type", "application/json; charset=UTF-8").build()
                chain.proceed(request)
            }

            /*httpClient.networkInterceptors().add(Interceptor { chain ->
                val requestBuilder: Request.Builder = chain.request().newBuilder()
                if(token != null){
                    requestBuilder.header("Authorization", "Bearer $token")
                }
                chain.proceed(requestBuilder.build())
            })*/
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

                .client(httpClient.build()) // Add our Okhttp client
                .build()

            jsonInstance = retrofit.create(ApiInterface::class.java)

        }
    }

//    private fun okhttpClient(context: Context): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(AuthInterceptor(context))
//            .build()
//    }
}
//        fun getMyData() {
//
//        val retrofitbuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(ApiLinks().BASE_URL)
//            .build()
//            .create(ApiInterface::class.java)
//
//        val retrofitData = retrofitbuilder.getData()
//        retrofitData.enqueue(object : Callback<List<Login>?> {
//            override fun onResponse(call: Call<List<Login>?>, response: Response<List<Login>?>)
//            {
//                val responseBody = response.body()!!
//
//                val myStringBuilder = StringBuilder()
//                for (myData in responseBody){
//                    myStringBuilder.append(myData.Status)
//                    myStringBuilder.append("\n")
//                }
//
//            }
//
//            override fun onFailure(call: Call<List<Login>?>, t: Throwable) {
//                Log.d("RetrofitBuilder", "onFailure:" +t.message)
//
//            }
//        })
//    }
