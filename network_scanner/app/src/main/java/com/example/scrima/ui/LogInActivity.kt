package com.example.scrima.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.scrima.R
import com.example.scrima.entities.User
import com.example.scrima.general.Validators
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

enum class ProviderType {
    BASIC
}

class LogInActivity : AppCompatActivity() {

    private val GOOGLE_SIGN_IN = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_log_in)
        onClickToOpenActivity(R.id.btn_main_signup, this, SignUpActivity::class.java)
        setTitle("")

        // User input
        val userEmail = findViewById<EditText>(R.id.input_login_email)
        val userPassword = findViewById<EditText>(R.id.input_login_password)

        // Login with email and password
        findViewById<Button>(R.id.btn_main_login)
            .setOnClickListener {
                if(Validators.validateNotBlankInputs(arrayListOf(userEmail, userPassword))){
                    signInWithEmailPasswordAuth(userEmail.text.toString(), userPassword.text.toString())
                }
                else {
                    showSimpleToast("Ingrese los datos requeridos")
                }
            }
    }

    fun signInWithEmailPasswordAuth(userEmail: String, userPassword: String) {
        FirebaseApp.initializeApp(this)
        FirebaseAuth.getInstance().signInWithEmailAndPassword(
            userEmail,
            userPassword
        ).addOnCompleteListener {
            if (it.isSuccessful) {
                openActivityWithParams(
                    this, HomeActivity::class.java, arrayListOf(
                        Pair("user", User(userEmail, userPassword, null, 0)),
                        Pair("type", "ProviderType.BASIC")
                    )
                )
            } else {
                showSimpleToast("No se ha podido iniciar sesión correctamente")
            }
        }
    }

    private fun showSimpleToast(message: String){
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun onClickToOpenActivity(idButton: Int, context: Context, classRef: Class<*>) {
        findViewById<Button>(idButton).setOnClickListener {
            val intentExplicito = Intent(
                context,
                classRef
            )
            startActivity(intentExplicito)
        }
    }

    fun openActivityWithParams(context: Context, classReference: Class<*>, params:  ArrayList<Pair<String, Any>>) {
        val intent = Intent(
            context,
            classReference
        )
        params.forEach {
                param ->
            var (key, value) = param

            if(value is User){
                intent.putExtra(key, value)
            }else if(value is String){
                intent.putExtra(key, value)
            }
        }
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if(account != null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener{
                            if(it.isSuccessful){
                                openActivityWithParams(
                                    this, HomeActivity::class.java, arrayListOf(
                                        Pair("user", User(account.email, null, null, null)),
                                        Pair("type", "ProviderType.GOOGLE")
                                    )
                                )
                            }else{
                                showSimpleToast("No ha podido iniciar sesión con Google")
                            }
                        }
                }
            }catch (e: ApiException){
                showSimpleToast("No ha podido iniciar sesión con Google")
            }
        }
    }

}
