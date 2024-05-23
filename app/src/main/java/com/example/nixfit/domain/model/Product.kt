package com.example.nixfit.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Product(
    @PrimaryKey val productName: String,
    val servingSize: String,
    val servingQuantity: String,
    val nutriments: Nutriments,
) : Parcelable