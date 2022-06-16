package com.example.prakashjobapp.api

import com.example.prakashjobapp.models.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


const val BASE_URL = "http://prakashjob.azurewebsites.net/"

interface ApiInterface {

    @GET("api/login/signin")
    fun LoginData(
        @Query("email") email: String,
        @Query("password") password: String,
    ): Call<Login>

    //VacancyList
    @GET("api/vacancy/searchvacancy")
    fun vacancyData(@Query("companyid") companyId: Int): Call<Vacancy>

    //SignUp
    @POST("api/login/signup")
    fun signUp(@Body registration: RequestBody): Call<Registration>

    //CompanyList
     @GET("api/company/companylist")
     fun companyList() : Call<Company>

    //ApplyJobs
    @GET("api/useraccount/appliedjobs?")
    fun appliedjob(@Query("user_id") user_id:Int) :  Call<AppliedJobs>

    //RecentJOBS
    @GET("api/vacancy/RecentVacancyList")
    fun popolarjobs() :Call<RecentJobs>

    //token Api
    @POST("token")
    @FormUrlEncoded
    fun tokenGenerate(@Field("username") username : String, @Field("password")password : String,@Field("grant_type")grant_type : String) : Call<ResponseBody>

    //JobApply
    @POST("api/useraccount/JobApply")
    fun jobApply(@Body requestBody: RequestBody?)
    :Call<DisplayUser>

    //insertApi
  @POST("api/userprofile/insertuserprofile")
    fun profileSubmit(
        @Body requestBody: RequestBody?,
    ):Call<DisplayUser>

    //updateuserprofile
    @POST("api/userprofile/UpdateUserProfile")
    fun updateProfile(@Body requestBody: RequestBody?) : Call<DisplayUser>

  //DisplayUserData
    @GET("api/userprofile/DisplayUserProfile?")
    fun displayProfile(@Query("userid") userid: Int) : Call<DisplayUser>

   //UserDataFillStatus
    @GET("api/userprofile/UserProfileStatus?")
    fun userProfileStatus(@Query("userid") userid  :Int)  : Call<ProfileStatusData>

}