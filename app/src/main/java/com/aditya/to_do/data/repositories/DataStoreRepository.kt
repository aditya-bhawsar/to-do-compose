package com.aditya.to_do.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.aditya.to_do.data.models.Priority
import com.aditya.to_do.util.Constants.PREFERENCE_KEY
import com.aditya.to_do.util.Constants.PREFERENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ViewModelScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val ctx: Context
) {

    val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

    private object PreferenceKey {
        val sortKey = stringPreferencesKey(name = PREFERENCE_KEY)
    }

    private val dataStore = ctx.datastore

    suspend fun persistSortState(priority: Priority) {
        dataStore.edit { preference ->
            preference[PreferenceKey.sortKey] = priority.name
        }
    }

    val readSortState: Flow<String> = dataStore.data.catch { exception ->
        if (exception is IOException)
            emptyPreferences()
        else
            throw exception
    }.map { prefrence ->
        val sortState = prefrence[PreferenceKey.sortKey] ?: Priority.NONE.name
        sortState
    }
}
