package com.example.nixfit.domain.usecases.appentry

import com.example.nixfit.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAppEntry @Inject constructor(
    private val localUserManager : LocalUserManager
) {
    operator fun invoke (): Flow<Boolean>{
        return localUserManager.readAppEntry()
    }

}