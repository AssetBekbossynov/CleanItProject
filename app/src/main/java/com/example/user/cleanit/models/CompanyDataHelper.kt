package com.example.user.cleanit.models

import android.os.Parcel
import android.os.Parcelable

class CompanyDataHelper() : Parcelable {

    var companyId: Int = 0
    var companyName: String? = null
    var description: String? = null
    var cleanTypes: ArrayList<CleanType> = ArrayList()

    constructor(parcel: Parcel) : this() {
        companyId = parcel.readInt()
        companyName = parcel.readString()
        description = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(companyId)
        parcel.writeString(companyName)
        parcel.writeString(description)
        parcel.writeTypedList(cleanTypes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CompanyDataHelper> {
        override fun createFromParcel(parcel: Parcel): CompanyDataHelper {
            return CompanyDataHelper(parcel)
        }

        override fun newArray(size: Int): Array<CompanyDataHelper?> {
            return arrayOfNulls(size)
        }
    }
}