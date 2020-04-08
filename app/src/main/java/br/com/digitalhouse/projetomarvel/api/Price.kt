package br.com.digitalhouse.projetomarvel.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

class Price() : Parcelable{
    @Expose
    var price: String? = null

    @Expose
    var type: String? = null

    constructor(parcel: Parcel) : this() {
        price = parcel.readString()
        type = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(price)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Price> {
        override fun createFromParcel(parcel: Parcel): Price {
            return Price(parcel)
        }

        override fun newArray(size: Int): Array<Price?> {
            return arrayOfNulls(size)
        }
    }

}