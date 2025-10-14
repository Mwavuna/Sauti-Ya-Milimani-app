package com.example.sautiyamilimani_test1.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setFirstTimeStatus(status:Boolean)
    fun getFirstTimeStatus(): Flow<Boolean>
}