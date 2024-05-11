package com.example.nixfit.domain.usecases.appentry

import com.example.nixfit.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (
    private val localUserManager : LocalUserManager
) {
    operator fun invoke (): Flow<Boolean>{
        return localUserManager.readAppEntry()
    }

}