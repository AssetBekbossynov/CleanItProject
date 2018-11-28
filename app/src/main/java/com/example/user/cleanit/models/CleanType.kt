package com.example.user.cleanit.models

import android.os.Parcel
import android.os.Parcelable

class CleanType() : Parcelable {

    var id: Int = 0
    var name: String? = null
    var cost: Double = 0.toDouble()

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeDouble(cost)
    }

    override fun describeContents(): Int {
        return 0
    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        cost = parcel.readDouble()
    }

    companion object CREATOR : Parcelable.Creator<CleanType> {
        override fun createFromParcel(parcel: Parcel): CleanType {
            return CleanType(parcel)
        }

        override fun newArray(size: Int): Array<CleanType?> {
            return arrayOfNulls(size)
        }
    }
}