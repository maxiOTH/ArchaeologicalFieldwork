package org.wit.archaeologicalfieldwork.models

import android.media.Image
import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize
import java.util.*
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.collections.ArrayList


@Parcelize
@Entity
data class SiteModel (@PrimaryKey(autoGenerate = true) var id: Long = 0,
                      var name:String="",
                      var description:String = "",
                      var images : ArrayList<org.wit.archaeologicalfieldwork.models.Image> = ArrayList(),
                      var lat: Double = 0.0,
                      var lng: Double = 0.0,
                      var zoom:Float = 0f,
                      var rating:Int = 0,
                      var favourite : Boolean = false,
                      var additionalNotes:String = "",
                      @Embedded var visited:Visited = Visited(),
                      @Embedded var location: Location = Location()):Parcelable

@Parcelize
data class Image(
    var image: String = "",
    var preview: Boolean = false
) : Parcelable

@Parcelize
data class Location(var lat : Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f):Parcelable

@Parcelize
data class Visited(
    var visited: Boolean = false,
    var date: Date = Date(System.currentTimeMillis())
):Parcelable

