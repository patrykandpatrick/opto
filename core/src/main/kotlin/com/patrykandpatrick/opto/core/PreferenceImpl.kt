package com.patrykandpatrick.opto.core

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.patrykandpatrick.opto.domain.Preference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class PreferenceImpl<C, S>(
    private val key: Preferences.Key<S>,
    private val defaultValue: C,
    private val serialize: (C) -> S,
    private val deserialize: (S) -> C,
    private val dataStore: DataStore<Preferences>,
) : Preference<C> {
    private fun S?.deserializedOrDefault() = if (this != null) deserialize(this) else defaultValue

    override fun get(): Flow<C> = dataStore.data.map { it[key].deserializedOrDefault() }

    override suspend fun set(value: C) {
        dataStore.edit { it[key] = serialize(value) }
    }

    override suspend fun update(function: (C) -> C) {
        dataStore.edit { it[key] = serialize(function(it[key].deserializedOrDefault())) }
    }
}
