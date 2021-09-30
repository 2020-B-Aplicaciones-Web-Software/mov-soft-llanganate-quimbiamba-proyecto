package com.example.scrima.general
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Settings {
    companion object {
        fun clearInputs(inputs: ArrayList<EditText>) {
            inputs.forEach {
                    input ->
                input.setText("")
            }
        }
        fun showMessage(context: Context,message: String){
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}