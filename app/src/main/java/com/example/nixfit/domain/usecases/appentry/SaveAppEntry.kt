package com.example.nixfit.domain.usecases.appentry

import com.example.nixfit.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val localUserManager : LocalUserManager
){
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}