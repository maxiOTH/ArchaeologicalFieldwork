package org.wit.archaeologicalfieldwork.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SiteModel (var id: Long = 0,
                      var name:String="",
                      var description:String = "",
                      var image: String=""):Parcelable