package com.example.nixfit.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.nixfit.R

data class Page (
    val title : String,
    val description : String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "NixFit",
        description = "Thank you for downloading NixFit! We hope you enjoy it!",
        image = R.drawable.ic_splash
    ),
    // Here we can add more pages, perhaps a permissions request
    // Pages()
)