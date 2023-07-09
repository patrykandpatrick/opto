package com.patrykandpatrick.opto.core

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.patrykandpatrick.opto.domain.Preference
import kotlinx.coroutines.flow.map

class PreferenceImpl<S, C>(
    private val key: Preferences.Key<S>,
    private val defaultValue: C,
    private val serialize: (C) -> S,
    private val deserialize: (S) -> C,
    private val preferencesDataStore: DataStore<Preferences>,
) : Preference<C> {

    private fun S?.deserializedOrDefault() = this?.let { deserialize(it) } ?: defaultValue

    fun getFromPreferences(preferences: Preferences) = preferences[key].deserializedOrDefault()

    override fun get() = preferencesDataStore.data.map(::getFromPreferences)

    override suspend fun set(value: C) {
        preferencesDataStore.edit { it[key] = serialize(value) }
    }

    suspend fun update(function: (C) -> C) {
        preferencesDataStore.edit { it[key] = serialize(function(it[key].deserializedOrDefault())) }
    }
}
