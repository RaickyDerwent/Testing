package com.derwentinc.wallpaperapp.model

import android.os.Parcel
import android.os.Parcelable

data class Photo(val name: String?, val url: String) : Parcelable {
    constructor(source: Parcel) : this(source.readString(), url = source.readString())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(url)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Photo> = object : Parcelable.Creator<Photo> {
            override fun createFromParcel(source: Parcel): Photo {
                return Photo(source)
            }

            override fun newArray(size: Int): Array<Photo?> {
                return arrayOfNulls(size)
            }
        }
    }
}