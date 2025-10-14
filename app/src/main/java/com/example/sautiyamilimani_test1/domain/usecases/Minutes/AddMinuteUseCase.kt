package com.example.sautiyamilimani_test1.domain.usecases.Minutes

import com.example.sautiyamilimani_test1.domain.model.Minute
import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.repository.MinutesRepository
import jakarta.inject.Inject

class AddMinuteUseCase @Inject constructor(
    private val minutesRepository: MinutesRepository
) {
    suspend operator fun invoke(): Resource<Minute> {
        return minutesRepository.AddMinute()
    }
}