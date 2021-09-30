package com.example.scrima.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.scrima.R
import com.example.scrima.entities.User
import com.example.scrima.general.FirebaseConnection
import com.example.scrima.general.Validators
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.Serializable


class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_sign_up)
        setTitle("Crear una cuenta")
        // Initialize Firebase Auth
        val auth = FirebaseAuth.getInstance()
        findViewById<Button>(R.id.btn_signup)
            .setOnClickListener {
                // Get user inputs
                val userEmail = findViewById<EditText>(R.id.input_signup_email)
                val userPassword = findViewById<EditText>(R.id.input_signup_password)
                val userPasswordConfirmation = findViewById<EditText>(R.id.input_signup_repeat_password)
                val userName = findViewById<EditText>(R.id.input_singup_name)
                val userLastname = findViewById<EditText>(R.id.input_signup_lastname)

                if(Validators.validateNotBlankInputs(
                        arrayListOf(userEmail, userPassword, userPasswordConfirmation, userName, userLastname)
                    )){
                    if (userPassword.text.toString() == userPasswordConfirmation.text.toString()){
                        auth.createUserWithEmailAndPassword(
                            userEmail.text.toString(),
                            userPasswordConfirmation.text.toString()
                        ).addOnCompleteListener{
                            if(it.isSuccessful){
                                val newUser = hashMapOf<String, Any>(
                                    "username" to userName.text.toString(),
                                    "lastname" to userLastname.text.toString(),
                                    "email" to userEmail.text.toString(),
                                    "totalScans" to 0,
                                    "uid" to auth.currentUser!!.uid,
                                )

                                showSimpleDialog("Inicio de sesión", "Se ha registrado correctamente")
                                FirebaseConnection.getInstance()
                                    .collection("users")
                                    .add(newUser)
                                    .addOnSuccessListener {
                                        openActivity(this, LogInActivity::class.java)
                                    }
                            }else{
                                showSimpleToast("No se ha podido crear este usuario")
                            }
                        }
                    }
                    else{
                        showSimpleToast("Las contraseñas deben ser iguales")
                    }
                }else {
                    showSimpleToast("Ingrese los datos requeridos")
                }
            }
    }

    fun openActivity( context: Context, classRef: Class<*>) {
            val intentExplicito = Intent(
                context,
                classRef
            )
            startActivity(intentExplicito)
    }

    fun showSimpleDialog(title: String, message: String){
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                "Ok",
                null
            )
            .create()
            .show()
    }

    private fun showSimpleToast(message: String){
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}
