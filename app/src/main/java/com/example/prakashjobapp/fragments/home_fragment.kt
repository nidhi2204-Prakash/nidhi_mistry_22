package com.example.prakashjobapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prakashjobapp.activity.JobVacancyPage
import com.example.prakashjobapp.adapters.CompanyListAdapter
import com.example.prakashjobapp.adapters.PopularjobsAdapter
import com.example.prakashjobapp.api.KeyClass
import com.example.prakashjobapp.api.RetrofitBuilder
import com.example.prakashjobapp.models.Company
import com.example.prakashjobapp.models.CompanyData
import com.example.prakashjobapp.models.DisplayUser
import com.example.prakashjobapp.models.RecentJobs
import de.hdodenhof.circleimageview.CircleImageView
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class homefragment : Fragment(R.layout.fragment_homefragment) {

    lateinit var imageview : CircleImageView
    //PopularJobs
    lateinit var popularjobsRV: RecyclerView
    lateinit var popularjobsAdapter: PopularjobsAdapter
    lateinit var Recentjoblist :RecyclerView
    //CompanyList
    lateinit var companyListCL: RecyclerView
    lateinit var adaptercompanylist: CompanyListAdapter
    lateinit var companyList :RecyclerView
    lateinit var cmpListView :TextView
    //sessionMnagerClass
    private lateinit var sessionManager: SessionManager

    lateinit var search :EditText
//    lateinit var buttonsearch :ImageButton
    //ARRAYlIST
    var companyListdata = ArrayList<CompanyData>()
    private var mUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayUser()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

//        val companyList = ArrayList<String>()
//        companyList.add("Prakash Software Solutions pvt.ltd")
//        companyList.add("Prakash Chemicals  pvt.ltd")
//        companyList.add("Prakash Chemicals International pvt.ltd")
//        companyList.add("Prakash Chemicals AGENCIES pvt.ltd")
//        companyList.add("PrakashtELE servicess pvt.ltd")

        // Inflate the layout for this fragment
        //  val searchview =  Regex("(.*?[A-Z])(?=.*?[a-z]).{3,}")
        val view = inflater.inflate(R.layout.fragment_homefragment, container, false)
//        buttonsearch = view.findViewById(R.id.button_search)
        search = view.findViewById(R.id.search)
        cmpListView = view.findViewById(R.id.textview_datanotfound)
//        companyList = view.findViewById(R.id.verticalrecyclerview)
//        search.addTextChangedListener(true)
//       search. addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
//            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
//                adaptercompanylist.filter.filter(charSequence.toString())
//
//            }
//
//            override fun afterTextChanged(editable: Editable) {}
//        })


        //  companyList = ArrayList<CompanyData>()
        companyListCL = view.findViewById(R.id.verticalrecyclerview)
        popularjobsRV = view.findViewById(R.id.recyclerview)
        imageview = view.findViewById(R.id.imageview)

        val args = this.arguments
        val imageUri = args?.get("image")
        val inputData = args?.get("")
        Log.d("bhoot3", args.toString())
        Log.d("bhoot2", args?.get("image").toString())
        imageview.setImageURI(imageUri as Uri?)


//        val imageUri = "https://blobstorageprakashjobs.blob.core.windows.net/blobstorageprakashjobs/ecabc4e1-cd35-46ec-8ab3-cf4c505602fc-sunflower.jpg"
//        Picasso.with(activity).load(imageUri).into(imageview)
        //  adaptercompanylist = CompanyListAdapter()
        // popularjobsAdapter = PopularjobsAdapter()
        //   popularjobsRV.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        //   popularjobsRV.adapter = popularjobsAdapter
//        companyListCL.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        companyListCL.adapter = adaptercompanylist
        companylistdata()
        recentJobList()

//        imageview.setOnClickListener {
//            displayUser()
//        }
//        button_search.setOnClickListener {
//            if (search.length() > 3) {
//                //api
//                //  companylistdata()
//            } else {
//                Toast.makeText(activity,
//                    resources.getString(R.string.minimum_3_characters_are_required_to_search),
//                    Toast.LENGTH_SHORT).show()
//            }
//        }
//        search.setOnClickListener {
//            if (search.length() > 3){
//                Toast.makeText(activity,
//                    resources.getString(R.string.minimum_3_characters_are_required_to_search),
//                    Toast.LENGTH_SHORT).show()
//            }
//
//        }

        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


            override fun afterTextChanged(s: Editable?) {

                // After changes
                filter(s.toString())
                val dataNull = (companyListCL.adapter as CompanyListAdapter).itemCount
                if (dataNull == 0){
                    cmpListView.visibility = View.VISIBLE
                }
               else{
                    cmpListView.visibility = View.GONE
               }
            }
        })
        return view
    }

//   private fun filter(text: String) {
//
//       val filteredList: ArrayList<CompanyData> = ArrayList()
//       for (item in companyList) {
//            if (item.getText1().toLowerCase().contains(text.toLowerCase())) {
//                filteredList.add(item)
//            }
//           }
//        }
//    adaptercompanylist.filterList(cmpName)
//   }

    private fun filter(text :String){
        val filteredNames = ArrayList<CompanyData>()
        companyListdata.filterTo(filteredNames) {
            it.company_name!!.toLowerCase().contains(text.toLowerCase())
        }
        //calling a method of the adapter class and passing the filtered list
        if (filteredNames != null){
            adaptercompanylist.filterList(filteredNames)
    }
}
//    private fun filter1(text: String) {
//        val filteredList: ArrayList<CompanyData> = ArrayList()
//        for (item in companyListdata) {
//            if (item.company_name.toLowerCase().contains(text.toLowerCase())) {
//                filteredList.add(item)
//            }
//        }
//        adaptercompanylist.filterList(filteredList)
//    }
//
//        val cmpName = ArrayList<CompanyData>()
//    companyListdata.filterTo(cmpName) {
//            it.company_name!!.toLowerCase().contains(text.toLowerCase())
//        }
//        //calling a method of the adapter class and passing the filtered list
//        if (cmpName != null){
//            adaptercompanylist.filterList(cmpName)
//        }
//
//    }



//    override fun onItemClick(position: Int) {
//        val intent = Intent(this.context, JobVacancyPage::class.java)
//        startActivity(intent)
//    }

    fun companylistdata(){

        RetrofitBuilder.JsonServices.jsonInstance.companyList()
            .enqueue(object : Callback<Company> {
                override fun onResponse(call: Call<Company>, response: Response<Company>) {

  //                  Log.d("TAG", "Total Companies: " + response.body()!!.Data.size)
                    try {

                        val company1 = response.body()
                        if (company1 != null)
                        {
                          //  adaptercompanylist = CompanyListAdapter(activity,company1.Data)
                            adaptercompanylist = activity?.let { CompanyListAdapter(it, company1.Data as ArrayList<CompanyData>) }!!
                            companyListCL.adapter = adaptercompanylist
                            companyListCL.layoutManager = LinearLayoutManager(activity)
                            companyListdata.addAll(response.body()!!.Data)
                            //If data is null
                            val dataNull = (companyListCL.adapter as CompanyListAdapter).itemCount
                            if (dataNull == 0){
                                cmpListView.visibility = View.VISIBLE
                            }
                            else{
                                cmpListView.visibility = View.GONE
                            }
                            companyListCL.adapter!!.notifyDataSetChanged()
                            adaptercompanylist.setOnItemClickListener(object : CompanyListAdapter.OnItemClickListener {
                                override fun onItemClick(position: Int) {

                                    val intent = Intent(activity, JobVacancyPage::class.java)
                                    intent.putExtra(KeyClass.KEY_COMPANY_NAME,company1.Data[position].company_name)
                                    intent.putExtra(KeyClass.KEY_COMPANY_ID,company1.Data[position].id)
                                    startActivity(intent)
                                }
                            })
                        }

                    }catch (e : JSONException){
                        e.printStackTrace()
                    }
                    catch (e : NullPointerException){
                        e.printStackTrace()
                    }
//                    if (response.body()?.Data!!.size != null) {
//
//                        companyList.removeAll(companyList)
//                        companyList.addAll(response.body()!!.Data)
//                        adaptercompanylist.notifyDataSetChanged()
//                    }
                }

                override fun onFailure(call: Call<Company?>, t: Throwable) {
                    Log.d("TAG", " Got Error " + t.localizedMessage)
                }
            })
    }

    fun recentJobList(){
        RetrofitBuilder.JsonServices.jsonInstance.popolarjobs().enqueue(object : Callback<RecentJobs?> {
            override fun onResponse(call: Call<RecentJobs?>, response: Response<RecentJobs?>) {
 //              Log.d("TAG", "Recent Jobs: " + response.body()!!.Data.size)

                try {

                    val popularjob = response.body()
                    if ( popularjob!= null) {
                        popularjobsAdapter = activity?.let { PopularjobsAdapter(it, popularjob.Data) }!!
                        popularjobsRV.adapter = popularjobsAdapter
                        popularjobsRV.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL, false)
                    }

                    }catch(e : JSONException){
                       e.printStackTrace()
                    }
                    catch (e : NullPointerException){
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<RecentJobs?>, t: Throwable) {

                Log.e(ContentValues.TAG, "Got error : " + t.localizedMessage)

            }
        })
    }

     fun displayUser() {
         sessionManager = SessionManager(requireActivity())
//         val id: String? = sessionManager.getString(KeyClass.KEY_ID)
//         val id1 : Int = id!!.toInt()
         val id = sessionManager.getString(SessionManager.KEY_ID).toString()
         val id3 = id.toInt()
        RetrofitBuilder.JsonServices.jsonInstance.displayProfile(id3).enqueue(object :
            Callback<DisplayUser?> {
            @SuppressLint("StringFormatMatches")
            override fun onResponse(call: Call<DisplayUser?>, response: Response<DisplayUser?>) {
    //            Log.d("TAG", "Display User " + response.body()!!.Data)

                try {

                    val user1 = response.body()

                    if (user1 != null && response.code() == 200) {
                        val profileImage = user1.Data!!.ProfilePhoto
                        //setImage
                        Glide.with(activity!!).load(profileImage)
                            .placeholder(R.drawable.ic_baseline_person_24)
                            .into(imageview)

                        //   Set_Profile.setImageURI(imageUri as Uri?)
//                      Profile_Button.setImageResource(user1.Data.ProfilePhoto)
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()

                }
            }

            override fun onFailure(call: Call<DisplayUser?>, t: Throwable) {

                Log.d("TAG", " Got Error " + t.localizedMessage)

            }
        })
    }
}