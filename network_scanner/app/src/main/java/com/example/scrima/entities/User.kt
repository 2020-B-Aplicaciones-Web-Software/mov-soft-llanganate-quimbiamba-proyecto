package com.example.scrima.entities

import android.os.Parcel
import android.os.Parcelable

class User (
    val email : String?,
    val name : String?,
    val lastname: String?,
    val countScans: Int?
        ) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ){
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(name)
        parcel.writeString(lastname)
        if(countScans != null){
            parcel.writeInt(countScans)
        }
    }

    override fun toString(): String {
        return "User(email=$email, nombre=$name $lastname, )"
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }
        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}