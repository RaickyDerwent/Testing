package com.derwentinc.wallpaperapp.model

import android.os.Parcel
import android.os.Parcelable

data class Photo(val name: String?, val url: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        url = parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Photo> {
        override fun createFromParcel(parcel: Parcel): Photo {
            return Photo(parcel)
        }

        override fun newArray(size: Int): Array<Photo?> {
            return arrayOfNulls(size)
        }
    }
}