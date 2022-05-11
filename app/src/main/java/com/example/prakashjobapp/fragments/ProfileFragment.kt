package com.example.prakashjobapp.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.prakashjobapp.R
import com.example.prakashjobapp.SessionManager
import com.example.prakashjobapp.activity.CompanyInfo
import com.example.prakashjobapp.activity.EducationInfo
import com.example.prakashjobapp.activity.LoginActivity
import com.example.prakashjobapp.activity.PersonalInfo
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.DisplayUser
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment(R.layout.fragment_profile) {
    //    var callbacks: OnFragmentCallbacks? = null
    lateinit var signout_button: ImageView
    lateinit var PersonalIcon: ImageView
    lateinit var CompanyIcon: ImageView
    lateinit var EducationIcon: ImageView
    lateinit var personal_InfoCard: CardView
    lateinit var company_infoCard: CardView
    lateinit var education_infoCard: CardView
    lateinit var Set_Profile: CircleImageView
    lateinit var people_Name :TextView
    private var mUri: Uri? = null
    private lateinit var sessionManager :SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,

    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        signout_button = view.findViewById(R.id.Signout_button)
        PersonalIcon = view.findViewById(R.id.Personal_Icon)
        CompanyIcon = view.findViewById(R.id.Company_Icon)
        EducationIcon = view.findViewById(R.id.education_Icon)
        personal_InfoCard = view.findViewById(R.id.personal_infoCard)
        company_infoCard = view.findViewById(R.id.company_infoCard)
        education_infoCard = view.findViewById(R.id.education_infoCard)
        Set_Profile = view.findViewById(R.id.Set_Profile)
        people_Name = view.findViewById(R.id.people_name)
        var sessionManager = SessionManager(requireActivity())

//        val mUri = requireArguments().getString("image", mUri.toString())
////       Picasso.with(activity).load(mUri).into(Set_Profile)
////       Set_Profile.setImageURI(Uri.fromFile( File(mUri.toString())))

        //One more exmaple
        val args = this.arguments
        val imageUri = args?.get("image")
        val inputData = args?.get("")
        Log.d("bhoot3",args.toString())
        Log.d("bhoot2", args?.get("bhoot_image").toString())

        Set_Profile.setImageURI(imageUri as Uri?)



        personal_InfoCard.setOnClickListener {
            val intent = Intent(this.context, PersonalInfo::class.java)
            startActivity(intent)
        }
        company_infoCard.setOnClickListener {
            val intent = Intent(this.context, CompanyInfo::class.java)
            startActivity(intent)
        }
        education_infoCard.setOnClickListener {
            val intent = Intent(this.context, EducationInfo::class.java)
            startActivity(intent)
        }
        signout_button.setOnClickListener {
            showAlertDialog()
        }
        Set_Profile.setOnClickListener {

        }
//        val showImage  = activity?.intent?.getStringExtra("showImage")
//        Set_Profile.setImageResource(showImage)
        displayApi()
        return view
    }
//    private fun showShareIntent() {
//        // Step 1: Create Share itent
//
//        val intent = Intent(Intent.ACTION_SEND).setType("showImage")
//      //  val path = MediaStore.Images.Media.insertImage(requireContext().contentResolver,bit ,"tempimage", null)
//
//       // val uri = Uri.parse(path)
//
//    }
        private fun showAlertDialog() {
            val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this.context)
            alertDialog.setTitle("SignOut")
            alertDialog.setMessage("Are you sure ?")

            alertDialog.setPositiveButton(
                "yes") { _, _ ->
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
//                startActivity(Intent(this@, DashboardActivity::class.java))
//                finish()
                Toast.makeText(activity, "Alert dialog closed.", Toast.LENGTH_LONG).show()
            }
            alertDialog.setNegativeButton(
                "No"
            ) { _, _ -> }
            val alert: AlertDialog = alertDialog.create()
            alert.setCanceledOnTouchOutside(false)
            alert.show()
        }

    fun displayApi(){
//        val id = sessionManager.getString(KeyClass.KEY_ID)
//        val id1 = id.toString()
        sessionManager = SessionManager(requireActivity())
        val id: String? = sessionManager.getString(SessionManager.KEY_ID)

        // Int Converted
        val id1 : Int = id!!.toInt()
        RetrofitBuilder.JsonServices.jsonInstance.displayProfile(id1).enqueue(object :
            Callback<DisplayUser?> {
            @SuppressLint("StringFormatMatches")
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
             Log.d("TAG", "Display User " + response.body()!!.Data)

                try {

                    val user1 = response.body()
                    if (user1 !=null&& response.code() == 200){
                        val profileImage = user1.Data!!.ProfilePhoto
                        val firstName =user1.Data.Firstname
                        val lastName = user1.Data.Lastname

                        people_Name.text = firstName + " " + lastName

                        Glide.with(activity!!).load(profileImage)
                            .into(Set_Profile)
//                       if (profileImage == null ){
//                           val myOptions = RequestOptions()
//                               .override(5, 5)
//
//                           Glide.with(activity!!).load(R.drawable.ic_baseline_person_pin_24)
//                               .apply { myOptions }
//                               .into(Set_Profile);
//                        }
//                        else {

//                       }
                     //   Set_Profile.setImageURI(imageUri as Uri?)
//                      Profile_Button.setImageResource(user1.Data.ProfilePhoto)
                    }

                }catch (e: JSONException){
                    e.printStackTrace()
                }

                catch (e :NullPointerException){
                    e.printStackTrace()
                }
            }
            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {

                Log.d("TAG", " Got Error " + t.localizedMessage)

            }
        })
    }
}
