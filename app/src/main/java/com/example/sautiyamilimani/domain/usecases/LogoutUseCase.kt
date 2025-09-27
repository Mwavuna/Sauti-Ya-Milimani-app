package com.example.sautiyamilimani.domain.usecases

import com.example.sautiyamilimani.domain.model.Resource
import com.example.sautiyamilimani.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend operator fun invoke(): Resource<Unit>{
        return repository.logout()
    }
}