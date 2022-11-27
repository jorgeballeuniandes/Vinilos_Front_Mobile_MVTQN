package com.grupo20.vinilos.modelos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Album (
    val albumId:Int,
    val name:String,
    val cover:String,
    val releaseDate:Date,
    val description:String,
    val genre:String,
    val recordLabel:String
): Parcelable
