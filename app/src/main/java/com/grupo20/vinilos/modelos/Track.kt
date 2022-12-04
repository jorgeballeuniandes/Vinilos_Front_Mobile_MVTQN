package com.grupo20.vinilos.modelos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Track(
    val trackId:Int,
    val name:String,
    val duration:String,
): Parcelable
