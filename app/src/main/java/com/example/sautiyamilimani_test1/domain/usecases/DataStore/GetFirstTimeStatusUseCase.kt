package com.example.sautiyamilimani_test1.domain.usecases.DataStore

import android.content.Context
import com.example.sautiyamilimani_test1.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class GetFirstTimeStatusUseCase(private val dataStoreRepo: DataStoreRepository) {
    operator fun invoke(): Flow<Boolean> =
    dataStoreRepo.getFirstTimeStatus()
}