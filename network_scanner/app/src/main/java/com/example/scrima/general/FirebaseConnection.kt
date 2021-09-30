package com.example.scrima.general

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirebaseConnection {
    fun getInstance() : FirebaseFirestore{
        return Firebase.firestore
    }
}