package com.example.nixfit.domain.manager

import kotlinx.coroutines.flow.Flow

class ReadAppEntry (
    private val localUserManager : LocalUserManager
) {
    operator fun invoke (): Flow<Boolean>{
        return localUserManager.readAppEntry()
    }

}