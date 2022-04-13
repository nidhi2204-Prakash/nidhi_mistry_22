package com.example.prakashjobapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.prakashjobapp.R
import com.example.prakashjobapp.fragments.AppliedFragment
import com.example.prakashjobapp.fragments.ProfileFragment
import com.example.prakashjobapp.homefragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {
    private var homefragment = homefragment()
    private var AppliedFragment = AppliedFragment()
    private var ProfileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        val bottom_navigation: BottomNavigationView = findViewById(R.id.bottom_navigation)


        bottom_navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(homefragment)
                R.id.applied -> replaceFragment(AppliedFragment)
                R.id.profile -> replaceFragment(ProfileFragment)
            }
            true
        }
        replaceFragment(homefragment())
    }


    fun replaceFragment(fragment: Fragment) {

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_containerr, fragment)
        ft.commit()

    }

}

