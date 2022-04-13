package com.example.prakashjobapp.models

data class LoginData(
    val Address: Any,
    val City: Any,
    val Country: Any,
    val DateOfBirth: Any,
    val EducationGap: Any,
    val Emailid: String,
    val Firstname: String,
    val Gender: String,
    val Id: Int,
    val Languages: Any,
    val Lastname: String,
    val Mobileno: String,
    val Password: String,
    val ProfilePhoto: Any,
    val ResumeUpload: Any,
    val Skills: Any,
    val State: Any,
    val UserCompanyInfoes: List<Any>,
    val UserEducationInfoes: List<Any>,
    val tblResetPasswordRequests: List<Any>

//    @SerializedName("Id"                  ) var Id                  : Int?              = null,
//    @SerializedName("ProfilePhoto"        ) var ProfilePhoto        : String?           = null,
//    @SerializedName("Firstname"           ) var Firstname           : String?           = null,
//    @SerializedName("Lastname"            ) var Lastname            : String?           = null,
//    @SerializedName("Emailid"             ) var Emailid             : String?           = null,
//    @SerializedName("Password"            ) var Password            : String?           = null,
//    @SerializedName("Mobileno"            ) var Mobileno            : String?           = null,
//    @SerializedName("DateOfBirth"         ) var DateOfBirth         : String?           = null,
//    @SerializedName("Address"             ) var Address             : String?           = null,
//    @SerializedName("City"                ) var City                : String?           = null,
//    @SerializedName("State"               ) var State               : String?           = null,
//    @SerializedName("Country"             ) var Country             : String?           = null,
//    @SerializedName("EducationGap"        ) var EducationGap        : String?           = null,
//    @SerializedName("Gender"              ) var Gender              : String?           = null,
//    @SerializedName("ResumeUpload"        ) var ResumeUpload        : String?           = null,
//    @SerializedName("Languages"           ) var Languages           : String?           = null,
//    @SerializedName("Skills"              ) var Skills              : String?           = null,
//    @SerializedName("UserEducationInfoes" ) var UserEducationInfoes : ArrayList<String> = arrayListOf(),
//    @SerializedName("UserCompanyInfoes"   ) var UserCompanyInfoes   : ArrayList<String> = arrayListOf(),
//    @SerializedName("JobApplies"          ) var JobApplies          : ArrayList<String> = arrayListOf()
//    /*val Id: Int,
//    val Email: String,
//    val Phone: String,
//    val Password: String,
//    val first_name: String,
//    val last_name: String,
//    val gender: String*/

)