package com.example.nixfit.domain.manager

class SaveAppEntry (
    private val localUserManager : LocalUserManager
){
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}