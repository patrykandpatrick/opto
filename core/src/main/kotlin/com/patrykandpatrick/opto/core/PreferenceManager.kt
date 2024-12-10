package com.patrykandpatrick.opto.core

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

public interface PreferenceManager {
    public val dataStore: DataStore<Preferences>

    public fun <C, S> preference(
        key: Preferences.Key<S>,
        defaultValue: C,
        serialize: (C) -> S,
        deserialize: (S) -> C,
    ): PreferenceImpl<C, S> = PreferenceImpl(key, defaultValue, serialize, deserialize, dataStore)

    public fun <S> preference(key: Preferences.Key<S>, defaultValue: S): PreferenceImpl<S, S> =
        preference(key = key, defaultValue = defaultValue, serialize = { it }, deserialize = { it })
}
