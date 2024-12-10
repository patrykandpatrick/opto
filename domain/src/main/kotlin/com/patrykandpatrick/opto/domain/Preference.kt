package com.patrykandpatrick.opto.domain

import kotlinx.coroutines.flow.Flow

public interface Preference<T> {
    public fun get(): Flow<T>

    public suspend fun set(value: T)

    public suspend fun update(function: (T) -> T)
}
