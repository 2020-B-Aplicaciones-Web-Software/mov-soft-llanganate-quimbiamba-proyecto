package com.example.scrima.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.scrima.R
import com.example.scrima.general.FirebaseConnection
import com.example.scrima.general.Settings
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class EditUserProfileActivity : AppCompatActivity() {

    var totalScans : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Actualizar perfil")
        setContentView(R.layout.activity_edit_user_profile)
        val password = findViewById<TextInputEditText>(R.id.update_user_profile_password)
        completeFields()
        findViewById<Button>(R.id.btn_update_user_profile).setOnClickListener {
            updateUser()
        }
    }

    fun completeFields(){
        val name = findViewById<TextInputEditText>(R.id.update_user_profile_name)
        val lastname = findViewById<TextInputEditText>(R.id.update_user_profile_lastname)
        val email = findViewById<TextInputEditText>(R.id.update_user_profile_email)

        // Firebase Auth
        val auth = FirebaseAuth.getInstance()

        // Firestore reference
        FirebaseConnection.getInstance()
            .collection("users")
            .whereEqualTo("uid", auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener { result ->
                for(document in result){
                    name.setText("${document.get("username")}")
                    lastname.setText("${document.get("lastname")}")
                    email.setText("${document.get("email")}")
                    totalScans = "${document.get("totalScans")}".toInt()
                }
            }
    }

    fun updateUser(){
        val name = findViewById<TextInputEditText>(R.id.update_user_profile_name)
        val lastname = findViewById<TextInputEditText>(R.id.update_user_profile_lastname)
        val email = findViewById<TextInputEditText>(R.id.update_user_profile_email)

        // Firebase Auth
        val auth = FirebaseAuth.getInstance()

        // Firestore reference
        FirebaseConnection.getInstance()
            .collection("users")
            .whereEqualTo("uid", auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener { result ->
                for(document in result){
                    FirebaseConnection.getInstance()
                        .collection("users")
                        .document(document.id)
                        .update(mapOf(
                            "username" to name.text.toString(),
                            "lastname" to lastname.text.toString(),
                            "email" to email.text.toString()
                        )).addOnSuccessListener {
                            Settings.showMessage(this, "Se ha actualizado correctamente su información")
                            openActivity(this, HomeActivity::class.java)
                        }
                }
            }
    }

    fun openActivity(context: Context, classRef: Class<*>) {
        val intentExplicito = Intent(
            context,
            classRef
        )
        startActivity(intentExplicito)
    }
}