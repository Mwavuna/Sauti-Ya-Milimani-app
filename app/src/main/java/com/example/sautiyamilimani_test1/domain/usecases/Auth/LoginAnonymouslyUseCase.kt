package com.example.sautiyamilimani_test1.domain.usecases.Auth

import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.model.User
import com.example.sautiyamilimani_test1.domain.repository.AuthRepository

class LoginAnonymouslyUseCase (private val authRepository: AuthRepository){
    suspend  operator fun invoke(): Resource<User>{
        return authRepository.loginAnonymously()
    }

}