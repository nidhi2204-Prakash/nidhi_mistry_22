package com.example.prakashjobapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalInfoData (
    val profilePhoto :String?,
    val firstName :String? ,
    val lastName :String?,
    val email :String?,
    val password :String?,
    val mobileNo:String?,
    val dateOfBirth :String?,
    val address :String,
    val city:String?,
    val state :String?,
    val country :String,
    val gender :String?,
    val skills :String?,
    val gapInEducation :String?,
    val knownlanguage :String?,
    val resume :String?
)  : Parcelable

@Parcelize
data class CompanyInfoData(
    val companyName :String?,
    val currentDesignation :String?,
    val jobType:String?,
    val employmentType :String?,
    val totalExp :String?,
    val department:String?,
    val noticePeriod :String?,
    val gapInWorkExpirence :String?,
    val expectedCTC :String?,
    val currentCTC :String?
) : Parcelable

data class EducationInfoData(
    val Qualification :String?,
    val boardUniversity :String?,
    val passingYear :String?,
    val percentage :String?
)
data class JobApplyData(
    val firstname :String?,
    val lastname : String?,
    val email :String?,
    val contactno :String??,
    val currentCTC :String?,
    val expectedCTC :String?,
    val noticePeriod :String?,
    val resumeUpload :String?
)

