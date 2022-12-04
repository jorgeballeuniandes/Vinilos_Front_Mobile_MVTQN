package com.grupo20.vinilos.modelos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collector (
    val collectorId: Int,
    val name:String,
    val telephone:String,
    val email:String
): Parcelable
