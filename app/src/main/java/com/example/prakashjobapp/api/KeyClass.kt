package com.example.prakashjobapp.api

import android.content.SharedPreferences

class KeyClass {
    lateinit var edior: SharedPreferences.Editor

    companion object{
        const val KEY_ID = "User_id"//USER_ID
        const val KEY_COMPANY_NAME = "companyName"
        const val KEY_JOB_TITLE = "jobTitle"
        const val KEY_JOB_POSTED_DATE = "jobPostedDate"
        const val KEY_REQUIRED_YEAR_EXPIRENCE = "requiredYearExpirence"
        const val KEY_OPENING_DATE = "openingDate"
        const val KEY_CLOSING_DATE ="closingDate"
        const val KEY_JOB_TYPE = "jobType"
        const val KEY_LOCATION = "Location"
        const val KEY_NO_OF_POSITION =" no.ofPosition"
        const val KEY_JOB_DESCRIPTION = "jobDescription"
        const val KEY_EMPLOYMENT_TYPE = "employmentType"
    // Personal info
        const val KEY_FIRST_NAMEPI ="firstNamePI"
        const val KEY_LAST_NAMEPI ="lastNamePI"
        const val KEY_EMAIL ="email"
        const val KEY_PASSWORDPI ="passwordPI"
        const val KEY_MOBILENO_PI ="mobileNo"
        const val KEY_ADDRESS ="address"
        const val KEY_CITY ="city"
        const val KEY_STATE ="state"
        const val KEY_COUNTRY ="country"
        const val KEY_GAP_IN_EDU_PI="gapInEdu"
        const val KEY_RESUME_UPLOAD = "uploadResume"
        const val KEY_KNOWN_LANGUAGES = "knownLanguage"
        const val KEY_BIRTH_DATE = "date_of_birth"
        const val KEY_PROFILE_IMAGE= "profileImage"
        const val KEY_GENDER = "gender"
        const val KEY_SKILL = "skill"
        const val KEY_COMPANY_NAMECI = "companyNameCI"
        const val KEY_CURRENT_DESIGNINATION = "currentDesignination"
        const val KEY_JOBTYPE_CI = "jobTypeCI"
        const val KEY_WORK_EXPRIENCE = "gapinWorkExp"
        const val KEY_CURRENTCTC_CI = "currentCTC"
        const val KEY_EXPECTEDCTC_CI = "expectedCTC"
        const val KEY_TOTAL_EXPIRENCE_CI = "totalExp"
        const val KEY_DEPARTMENT = "department"
        const val KEY_NOTICE_PERIOD_CI = "noticePeriod"
        const val KEY_EMPLOYMENTTYPE_CI = "employmentType"
        const val KEY_COMPANY_INFO_ID = "companyInfoId"
        const val KEY_EDUCATION_INFO_ID = "educationInfoId"
        const val KEY_COMPANY_ID = "CompanyId"
        const val KEY_VACANCY_ID = "vacancyId"
        //For DataClass
        const val PERSONAL_INFO_DATA = "PersonalInfoData"
        const val COMPANY_INFO_DATA = "companyinfodata"
    }
    fun putString(key_name :String , value : String){
        edior.putString(key_name,value)
        edior.apply()
    }
}