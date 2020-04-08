package br.com.digitalhouse.projetomarvel.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

class Result() : Parcelable{
    @Expose
    var characters: Characters? = null

    @Expose
    var collectedIssues: List<CollectedIssue>? = null

    @Expose
    var collections: List<Collection>? = null

    @Expose
    var creators: Creators? = null

    @Expose
    lateinit var dates: List<Date>

    @Expose
    var description: String? = null

    @Expose
    var diamondCode: String? = null

    @Expose
    var digitalId: String? = null

    @Expose
    var ean: String? = null

    @Expose
    var events: Events? = null

    @Expose
    var format: String? = null

    @Expose
    var id: String? = null

    @Expose
    var images: List<Image>? = null

    @Expose
    var isbn: String? = null

    @Expose
    var issn: String? = null

    @Expose
    var issueNumber: String? = null

    @Expose
    var modified: String? = null

    @Expose
    var pageCount: String? = null

    @Expose
    var prices: List<Price>? = null

    @Expose
    var resourceURI: String? = null

    @Expose
    var series: Series? = null

    @Expose
    var stories: Stories? = null

    @Expose
    var textObjects: List<TextObject>? = null

    @Expose
    lateinit var thumbnail: Thumbnail

    @Expose
    var title: String? = null

    @Expose
    var upc: String? = null

    @Expose
    var urls: List<Url>? = null

    @Expose
    var variantDescription: String? = null

    @Expose
    var variants: List<Variant>? = null

    constructor(parcel: Parcel) : this() {
        dates = parcel.createTypedArrayList(Date.CREATOR)!!
        description = parcel.readString()
        diamondCode = parcel.readString()
        digitalId = parcel.readString()
        ean = parcel.readString()
        format = parcel.readString()
        id = parcel.readString()
        isbn = parcel.readString()
        issn = parcel.readString()
        issueNumber = parcel.readString()
        modified = parcel.readString()
        pageCount = parcel.readString()
        prices = parcel.createTypedArrayList(Price.CREATOR)
        resourceURI = parcel.readString()
        thumbnail = parcel.readParcelable(Thumbnail::class.java.classLoader)!!
        title = parcel.readString()
        upc = parcel.readString()
        variantDescription = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(dates)
        parcel.writeString(description)
        parcel.writeString(diamondCode)
        parcel.writeString(digitalId)
        parcel.writeString(ean)
        parcel.writeString(format)
        parcel.writeString(id)
        parcel.writeString(isbn)
        parcel.writeString(issn)
        parcel.writeString(issueNumber)
        parcel.writeString(modified)
        parcel.writeString(pageCount)
        parcel.writeTypedList(prices)
        parcel.writeString(resourceURI)
        parcel.writeParcelable(thumbnail, flags)
        parcel.writeString(title)
        parcel.writeString(upc)
        parcel.writeString(variantDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }

}