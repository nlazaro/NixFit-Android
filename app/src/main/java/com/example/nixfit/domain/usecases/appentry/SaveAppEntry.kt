package com.example.nixfit.domain.usecases.appentry

import com.example.nixfit.domain.manager.LocalUserManager

class SaveAppEntry (
    private val localUserManager : LocalUserManager
){
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}