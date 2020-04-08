package br.com.digitalhouse.projetomarvel.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

class Date() : Parcelable{
    @Expose
    var date: String? = null

    @Expose
    var type: String? = null

    constructor(parcel: Parcel) : this() {
        date = parcel.readString()
        type = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(date)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Date> {
        override fun createFromParcel(parcel: Parcel): Date {
            return Date(parcel)
        }

        override fun newArray(size: Int): Array<Date?> {
            return arrayOfNulls(size)
        }
    }

}