package com.example.nixfit.data.manager

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.nixfit.domain.manager.LocalUserManager
import com.example.nixfit.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserManagerImpl @Inject constructor(
    private val application : Application
) : LocalUserManager {
    override suspend fun saveAppEntry() {
        application.dataStore.edit { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return application.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = Constants.USER_SETTINGS)
private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY )
}