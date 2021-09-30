package com.example.scrima.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.scrima.R
import com.example.scrima.general.FirebaseConnection
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val auth = FirebaseAuth.getInstance()

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Fill text views
        val name = view.findViewById<TextView>(R.id.tv_userprofile_name)
        val email = view.findViewById<TextView>(R.id.tv_userprofile_email)
        val totalScans = view.findViewById<TextView>(R.id.tv_userprofile_scan)

        FirebaseConnection.getInstance()
            .collection("users")
            .whereEqualTo("uid", auth.currentUser!!.uid)
            .get()
            .addOnSuccessListener { result ->
                for(document in result){
                    name.setText("${document.get("username")} ${document.get("lastname")}")
                    email.setText("${document.get("email")}")
                    totalScans.setText("${document.get("totalScans")}")
                }
            }

             // Sign Out
            view.findViewById<Button>(R.id.btn_signout).setOnClickListener {
                auth.signOut()
                if (container != null) {
                    openActivity(container.context, LogInActivity::class.java)
                }
            }

            // Edit Users information
            view.findViewById<Button>(R.id.btn_edit_user_profile).setOnClickListener {
                openActivity(container!!.context, EditUserProfileActivity::class.java)
            }

        return view
    }

    override fun onResume() {
        super.onResume()
        requireFragmentManager()!!.beginTransaction().detach(this).attach(this).commit();

    }

    fun openActivity(context: Context, classRef: Class<*>) {
        val intentExplicito = Intent(
            context,
            classRef
        )
        startActivity(intentExplicito)
    }
}