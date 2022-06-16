package com.example.prakashjobapp.api
import android.text.TextUtils
import android.util.Log
import com.example.prakashjobapp.models.CompanyInfoData
import com.example.prakashjobapp.models.EducationInfoData
import com.example.prakashjobapp.models.JobApplyData
import com.example.prakashjobapp.models.PersonalInfoData
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.File


class RequestParameters {
    fun createUser(
        Firstname: String?,
        Lastname: String?,
        Emailid: String,
        Mobileno: String?,
        Password: String?,
        Gender: String
    ): RequestBody {

        // Create JSON using JSONObject
        val jsonObject = JSONObject()

        jsonObject.put("Emailid", Emailid)
        jsonObject.put("Mobileno", Mobileno)
        jsonObject.put("Password", Password)
        jsonObject.put("Firstname", Firstname)
        jsonObject.put("Lastname", Lastname)
        jsonObject.put("Gender", Gender)
        val jsonObjectString = jsonObject.toString()
        Log.d("rob", jsonObjectString)

        val jSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        val body: RequestBody = RequestBody.create(jSON, jsonObjectString)

        return body
    }
    fun UploadProfileImage(imgFile: File): RequestBody? {
        var body: RequestBody? = null
        try {
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)

            builder.addFormDataPart("PROFILE_IMAGE", imgFile.name, RequestBody.create(MultipartBody.FORM, imgFile))
            body = builder.build()
            Log.d("TAG", "reqUploadProfileImage: $body")
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return body
    }

        fun userProfileSubmit(user_id :Int?,personalinfo :PersonalInfoData,companyInfo :CompanyInfoData,educationInfo :EducationInfoData): RequestBody? {
            var body: RequestBody? = null
            try {
                val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
                builder.addFormDataPart("user_id",user_id.toString())
                if(TextUtils.isEmpty(personalinfo.profilePhoto)) {
                    val imageFile = File(personalinfo.profilePhoto!!)
                    if (imageFile.exists()){
                        builder.addFormDataPart("profile_upload",imageFile.name, RequestBody.create(MultipartBody.FORM,imageFile)
                        )
                    }
                }

                builder.addFormDataPart("firstName",personalinfo.firstName!!)
                builder.addFormDataPart("lastName",personalinfo.lastName!!)
                builder.addFormDataPart("email",personalinfo.email!!)
                builder.addFormDataPart("password",personalinfo.password!!)
                builder.addFormDataPart("phone",personalinfo.mobileNo!!)
                builder.addFormDataPart("DOB",personalinfo.dateOfBirth!!)
                builder.addFormDataPart("address",personalinfo.address!!)
                builder.addFormDataPart("city",personalinfo.city!!)
                builder.addFormDataPart("state",personalinfo.state!!)
                builder.addFormDataPart("country",personalinfo.country!!)
                builder.addFormDataPart("gapInEducation",personalinfo.gapInEducation!!)
                builder.addFormDataPart("gender",personalinfo.gender!!)
                if (TextUtils.isEmpty(personalinfo.resume)){
                    val docFile = File(personalinfo.resume!!)
                    if (docFile.exists())
                    {
                        builder.addFormDataPart("resume",docFile.name, RequestBody.create(MultipartBody.FORM,docFile))
                    }
                }

                builder.addFormDataPart("skills",personalinfo.skills!!)
                builder.addFormDataPart("knownLanguahes",personalinfo.knownlanguage!!)
                builder.addFormDataPart("company_name",companyInfo.companyName!!)
                builder.addFormDataPart("current_designation",companyInfo.currentDesignation!!)
                builder.addFormDataPart("job_type",companyInfo.jobType!!)
                builder.addFormDataPart("employment_type",companyInfo.employmentType!!)
                builder.addFormDataPart("total_expirence",companyInfo.totalExp!!)
                builder.addFormDataPart("department",companyInfo.department!!)
                builder.addFormDataPart("notice_period",companyInfo.noticePeriod!!)
                builder.addFormDataPart("gap_in_work_expirence",companyInfo.gapInWorkExpirence!!)
                builder.addFormDataPart("current_CTC",companyInfo.currentCTC!!)
                builder.addFormDataPart("expected_CTC",companyInfo.expectedCTC!!)
                builder.addFormDataPart("Qualification",educationInfo.Qualification!!)
                builder.addFormDataPart("board_university",educationInfo.boardUniversity!!)
                builder.addFormDataPart("passing_year",educationInfo.passingYear!!)
                builder.addFormDataPart("percentage",educationInfo.percentage!!)
                body = builder.build()
                Log.d("TAG", "reqUploadProfileImage: $body")

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return body
        }


    //userUpdate
    fun userProfileUpdate(
        user_id: Int,
        personalinfo:PersonalInfoData,
        companyInfo:CompanyInfoData,
        eduationInfo:EducationInfoData
    ): RequestBody? {
        var body: RequestBody? = null
        try {
            val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
            builder.addFormDataPart("user_id",user_id.toString())
            if(TextUtils.isEmpty(personalinfo.profilePhoto)) {
                val imageFile = File(personalinfo.profilePhoto!!)
                if (imageFile.exists()){
                    builder.addFormDataPart("profile_upload",imageFile.name, RequestBody.create(MultipartBody.FORM,imageFile)
                    )
                }
            }

            builder.addFormDataPart("firstName",personalinfo.firstName!!)
            builder.addFormDataPart("lastName",personalinfo.lastName!!)
            builder.addFormDataPart("email",personalinfo.email!!)
            builder.addFormDataPart("password",personalinfo.password!!)
            builder.addFormDataPart("phone",personalinfo.mobileNo!!)
            builder.addFormDataPart("DOB",personalinfo.dateOfBirth!!)
            builder.addFormDataPart("address",personalinfo.address!!)
            builder.addFormDataPart("city",personalinfo.city!!)
            builder.addFormDataPart("state",personalinfo.state!!)
            builder.addFormDataPart("country",personalinfo.country!!)
            builder.addFormDataPart("gapInEducation",personalinfo.gapInEducation!!)
            builder.addFormDataPart("gender",personalinfo.gender!!)
            if (TextUtils.isEmpty(personalinfo.resume)){
                val docFile = File(personalinfo.resume!!)
                if (docFile.exists())
                {
                    builder.addFormDataPart("resume",docFile.name, RequestBody.create(MultipartBody.FORM,docFile))
                }
            }

            builder.addFormDataPart("skills",personalinfo.skills!!)
            builder.addFormDataPart("knownLanguahes",personalinfo.knownlanguage!!)
            builder.addFormDataPart("company_name",companyInfo.companyName!!)
            builder.addFormDataPart("current_designation",companyInfo.currentDesignation!!)
            builder.addFormDataPart("job_type",companyInfo.jobType!!)
            builder.addFormDataPart("employment_type",companyInfo.employmentType!!)
            builder.addFormDataPart("total_expirence",companyInfo.totalExp!!)
            builder.addFormDataPart("department",companyInfo.department!!)
            builder.addFormDataPart("notice_period",companyInfo.noticePeriod!!)
            builder.addFormDataPart("gap_in_work_expirence",companyInfo.gapInWorkExpirence!!)
            builder.addFormDataPart("current_CTC",companyInfo.currentCTC!!)
            builder.addFormDataPart("expected_CTC",companyInfo.expectedCTC!!)
            builder.addFormDataPart("Qualification",eduationInfo.Qualification!!)
            builder.addFormDataPart("board_university",eduationInfo.boardUniversity!!)
            builder.addFormDataPart("passing_year",eduationInfo.passingYear!!)
            builder.addFormDataPart("percentage",eduationInfo.percentage!!)
            body = builder.build()
            Log.d("TAG", "reqUploadProfileImage: $body")

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return body
    }

   fun applyJob(user_id: Int?, jobApply : JobApplyData)  : RequestBody?    {
        var body: RequestBody? = null
        try {
          val builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        builder.addFormDataPart("user_id", user_id.toString())
        builder.addFormDataPart("firstname",jobApply.firstname !!)
        builder.addFormDataPart("lastName",jobApply.lastname!!)
        builder.addFormDataPart("email_id",jobApply.email!!)
        builder.addFormDataPart("contactNo",jobApply.contactno!!)
        builder.addFormDataPart("currentCTC",jobApply.currentCTC!!)
        builder.addFormDataPart("expectedCTC",jobApply.expectedCTC!!)

           if (TextUtils.isEmpty(jobApply.resumeUpload)){
              val docFile = File(jobApply.resumeUpload!!)
                if (docFile.exists())
               {
                    builder.addFormDataPart("resume",docFile.name, RequestBody.create(MultipartBody.FORM,docFile))
                }
            }
            body = builder.build()
            Log.d("TAG", "resume uploaded : $body")
        }catch (e : Exception){
            e.printStackTrace()
        }
        return body
   }
    fun jobApply(Firstname: String?,
                 Lastname: String?,
                 Email: String?,
                 contactNo: String,
                 currenctCTC:String?,
                 expectedCTC:String?,
                 noticePeriod:String?,
                 resume:String?,
                 user_id: Int??,
                   ) :RequestBody{
        val jsonObject = JSONObject()

        jsonObject.put("fname",Firstname)
        jsonObject.put("lastname",Lastname)
        jsonObject.put("Email",Email)
        jsonObject.put("user_id",user_id)
        jsonObject.put("ContactNo",contactNo)
        jsonObject.put("currentCTC",currenctCTC)
        jsonObject.put("expectedCTC",expectedCTC)
        jsonObject.put("noticePeriod",noticePeriod)
//        jsonObject.put("resume",resume)

        val jsonObjectString = jsonObject.toString()
        Log.d("rob", jsonObjectString)
        val jSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        val body: RequestBody = RequestBody.create(jSON, jsonObjectString)

        return body
    }
    fun updateData(user_id :Int?,
                   Firstname: String?,
                   Lastname: String?,
                   Email: String,
                   Password: String?,
                   mobileNo: String?,
                   DOB: String?,
                   address: String?,
                   city: String?,
                   state: String?,
                   country: String?,
                   GapinEdu: String?,
                   Gender: String?,
                   skills: String?,
                   knownLanguages: String?,
//        resumeUpload :String?,
        //CompanyInfo
                   companyName: String?,
                   currentDesignation: String?,
                   jobType: String?,
                   employmentType: String?,
                   totalExpirence: String?,
                   department: String?,
                   noticePeriod: String?,
                   gapinWorkExp: String?,
                   currentCTC: String?,
                   expectedCTC: String?,
        //  EducationInfo
                   qualification: String?,
                   boardUniversity: String?,
                   passingYear: String?,
                   percentage: String?,)  :RequestBody {

        val jsonObject = JSONObject()
        jsonObject.put("user_id",user_id)
        jsonObject.put("firstNAme", Firstname)
        jsonObject.put("lastName", Lastname)
        jsonObject.put("email", Email)
        jsonObject.put("password", Password)
        jsonObject.put("mobileNo", mobileNo)
        jsonObject.put("DOB", DOB)
        jsonObject.put("address", address)
        jsonObject.put("city", city)
        jsonObject.put("state", state)
        jsonObject.put("country", country)
        jsonObject.put("gapinEdu", GapinEdu)
        jsonObject.put("knownLanguages", knownLanguages)
        jsonObject.put("skills", skills)
        jsonObject.put("companyName", companyName)
        jsonObject.put("currentDesignation", currentDesignation)
        jsonObject.put("jobType", jobType)
        jsonObject.put("employmentType", employmentType)
        jsonObject.put("TotalExp", totalExpirence)
        jsonObject.put("currentCTC", currentCTC)
        jsonObject.put("expectedCTC", expectedCTC)
        jsonObject.put("department", department)
        jsonObject.put("noticePeriod", noticePeriod)
        jsonObject.put("gapinWorkExp", gapinWorkExp)
        jsonObject.put("gapinWorkExp", Gender)
        jsonObject.put("qualification", qualification)
        jsonObject.put("boardUniversity", boardUniversity)
        jsonObject.put("passingYear", passingYear)
        jsonObject.put("percentage", percentage)

        val jsonObjectString = jsonObject.toString()
        Log.d("rob", jsonObjectString)

        val jSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        val body: RequestBody = RequestBody.create(jSON, jsonObjectString)

        return body
    }
}

