package com.example.sautiyamilimani_test1.domain.usecases.DataStore


import com.example.sautiyamilimani_test1.domain.repository.DataStoreRepository

class SetFirstTimeStatusUseCase(
    private val dataStoreRepo: DataStoreRepository
) {
    operator suspend fun invoke(status:Boolean) =
        dataStoreRepo.setFirstTimeStatus(status)
}
