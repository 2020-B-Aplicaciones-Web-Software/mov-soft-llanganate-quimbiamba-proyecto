package com.example.scrima.entities

import android.os.Parcel
import android.os.Parcelable

class ScanRecord (
    val ipGateway : String?,
    val addressMAC: String?,
    val date : String?,
    val time: String?
    ) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ){
        }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ipGateway)
        parcel.writeString(addressMAC)
        parcel.writeString(date)
        parcel.writeString(time)
    }

    override fun toString(): String {
        return "ScanRecord(gateway=$ipGateway, empresa=$addressMAC, fecha=$date, hora=$time)"
    }

    companion object CREATOR : Parcelable.Creator<ScanRecord> {
        override fun createFromParcel(parcel: Parcel): ScanRecord {
            return ScanRecord(parcel)
        }
        override fun newArray(size: Int): Array<ScanRecord?> {
            return arrayOfNulls(size)
        }
    }
}