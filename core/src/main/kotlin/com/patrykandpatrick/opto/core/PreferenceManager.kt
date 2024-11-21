package com.patrykandpatrick.opto.core

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.patrykandpatrick.opto.domain.Preference

public interface PreferenceManager {
    public val dataStore: DataStore<Preferences>

    public fun <C, S> preference(
        key: Preferences.Key<S>,
        defaultValue: C,
        serialize: (C) -> S,
        deserialize: (S) -> C,
    ): Preference<C> = PreferenceImpl(key, defaultValue, serialize, deserialize, dataStore)

    public fun <S> preference(key: Preferences.Key<S>, defaultValue: S): Preference<S> =
        preference(key = key, defaultValue = defaultValue, serialize = { it }, deserialize = { it })
}
