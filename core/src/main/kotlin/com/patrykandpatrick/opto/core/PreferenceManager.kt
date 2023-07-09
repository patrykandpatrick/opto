package com.patrykandpatrick.opto.core

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface PreferenceManager {
    val preferencesDataStore: DataStore<Preferences>

    fun <S> preference(key: Preferences.Key<S>, defaultValue: S) =
        preference(key = key, defaultValue = defaultValue, serialize = { it }, deserialize = { it })

    fun <C, S> preference(key: Preferences.Key<S>, defaultValue: C, serialize: (C) -> S, deserialize: (S) -> C) =
        PreferenceImpl(key, defaultValue, serialize, deserialize, preferencesDataStore)
}
