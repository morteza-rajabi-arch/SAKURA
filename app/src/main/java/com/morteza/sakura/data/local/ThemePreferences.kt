
package com.morteza.sakura.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.themeDataStore: DataStore<Preferences> by
preferencesDataStore(name = "sakura_settings")

class ThemePreferences(
    private val context: Context
) {
    private companion object {
        val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
    }

    val isDarkTheme: Flow<Boolean> =
        context.themeDataStore.data.map { preferences ->
            preferences[DARK_THEME_KEY] ?: false
        }

    suspend fun setDarkTheme(enabled: Boolean) {
        context.themeDataStore.edit { preferences ->
            preferences[DARK_THEME_KEY] = enabled
        }
    }
}
