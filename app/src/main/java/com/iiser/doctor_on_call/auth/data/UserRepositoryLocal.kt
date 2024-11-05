package com.iiser.doctor_on_call.auth.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_prefs")

class UserRepositoryLocal @Inject constructor(
    private val context: Context
){
    private val _username = MutableStateFlow<String?>(null)
    val username: StateFlow<String?> = _username

    init {
        runBlocking {
            loadUsername()
        }
    }

    suspend fun setUsername(newUsername: String){
        context.dataStore.edit { prefs ->
            prefs[USER_NAME_KEY] = newUsername
        }
        _username.value = newUsername
    }

    suspend fun clearUsername(){
        context.dataStore.edit { prefs ->
            prefs.remove(USER_NAME_KEY)
        }
        _username.value = null
    }

    private suspend fun loadUsername(){
        val storedUsername: Flow<String?> = context.dataStore.data.map { prefs ->
            prefs[USER_NAME_KEY]
        }
        _username.value = storedUsername.firstOrNull()
    }

    fun getUsername(): String?{
        if (username != null){
            return username.toString()
        } else {
            return null
        }
    }

    companion object {
        private val USER_NAME_KEY = stringPreferencesKey("username")
    }
}
