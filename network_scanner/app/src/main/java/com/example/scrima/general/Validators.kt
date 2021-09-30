package com.example.scrima.general

import android.widget.EditText

class Validators {
    companion object {
        fun validateNotBlankInputs(inputs: ArrayList<EditText>): Boolean {
            inputs.forEach {
                input ->
                if(input.text.isBlank()){
                    return false
                }
            }
            return true
        }
    }
}