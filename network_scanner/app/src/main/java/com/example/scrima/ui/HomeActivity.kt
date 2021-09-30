package com.example.scrima.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.scrima.R
import com.example.scrima.entities.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    // Initialize Firebase Auth
    val auth = Firebase.auth

    // Fragments for bottom menu navegation
    val recordsFragment  = RecordsFragment()
    val scanFragment = ScanFragment()
    val profileFragment = ProfileFragment()
    val idContainerView = R.id.containerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Get Params
        val user = intent.getParcelableExtra<User>("user")
        val  type = intent.getStringExtra("type")

        // According to bottom vavegation
        setFragmentsForNavigation()
    }

    override fun onResume() {
        super.onResume()
        setFragmentsForNavigation()
    }

    override fun onPause() {
        super.onPause()
        setFragmentsForNavigation()

    }

    fun setFragmentsForNavigation(){
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.navigation_history -> {
                        setCurrentFragment(idContainerView, recordsFragment)
                        setTitle("Historial")
                        true
                    }
                    R.id.navigation_scan -> {
                        setCurrentFragment(idContainerView, scanFragment)
                        setTitle("Scanner")
                        true
                    }
                    R.id.navigation_profile -> {
                        setCurrentFragment(idContainerView, profileFragment)
                        setTitle("Perfil")
                        true
                    }
                    else -> false
                }
            }
    }

    fun setCurrentFragment(idContainerView: Int, fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(idContainerView, fragment)
            commit()
        }
    }
}