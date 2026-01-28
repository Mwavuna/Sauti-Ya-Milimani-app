package com.example.sautiyamilimani_test1.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun firstTimeStatus(status: Boolean)
    fun getFirstTimeStatus(): Flow<Boolean>
}