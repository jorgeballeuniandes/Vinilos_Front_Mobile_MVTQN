package com.grupo20.vinilos.modelos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date


@Parcelize
data class Artist(
    val artistId: Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate: Date
): Parcelable
