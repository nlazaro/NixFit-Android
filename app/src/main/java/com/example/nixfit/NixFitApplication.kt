@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.nixfit

import android.app.Application
import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class NixFitApplication : Application()