package com.example.sautiyamilimani_test1.domain.usecases.Auth

import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend operator fun invoke(): Resource<Unit>{
        return repository.logout()
    }
}