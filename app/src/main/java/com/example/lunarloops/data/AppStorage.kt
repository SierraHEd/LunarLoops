package com.example.lunarloops.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.lunarloops.ui.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppStorage(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("app_storage")

        private object PreferenceKeys {
            val USERNAME = stringPreferencesKey("username")
            val PASSWORD = stringPreferencesKey("password")
            val CNAME = stringPreferencesKey("cname")
            val World1_SCORE = intPreferencesKey("world2_score")
            val World2_SCORE = intPreferencesKey("world2_score")
        }
    }

    val appPreferencesFlow: Flow<AppPreferences> =
        context.dataStore.data.map {prefs ->
            val email = prefs[PreferenceKeys.USERNAME] ?: ""
            val password = prefs[PreferenceKeys.PASSWORD] ?: ""
            val cname = prefs[PreferenceKeys.CNAME] ?: ""
            val world1Score = prefs[PreferenceKeys.World2_SCORE] ?: 0
            val world2Score = prefs[PreferenceKeys.World2_SCORE] ?: 0

            AppPreferences(email, password, cname, world2Score)
        }

    suspend fun saveUsername(username: String){
        context.dataStore.edit { prefs ->
            prefs[PreferenceKeys.USERNAME] = username
        }
    }

    suspend fun savePassword(password: String){
        context.dataStore.edit { prefs ->
            prefs[PreferenceKeys.PASSWORD] = password
        }
    }

    suspend fun saveChildName(cName: String){
        context.dataStore.edit { prefs ->
            prefs[PreferenceKeys.CNAME] = cName
        }
    }

    suspend fun saveWorld1Score(world1score: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.World2_SCORE] = world1score
        }
    }

    suspend fun saveWorld2Score(world2score: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.World2_SCORE] = world2score
        }
    }
}