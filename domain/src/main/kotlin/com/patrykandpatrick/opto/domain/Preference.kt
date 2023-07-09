package com.patrykandpatrick.opto.domain

import kotlinx.coroutines.flow.Flow

interface Preference<T> {
    fun get(): Flow<T>

    suspend fun set(value: T)
}
