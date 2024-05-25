package com.example.nixfit.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nutriments(
    val carbohydrates: String,
    val protein: String,
    val fat: String,
    val energy: String,
    val sodium: String,
    val sugar: String
) : Parcelable