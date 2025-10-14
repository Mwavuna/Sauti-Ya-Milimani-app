package com.example.sautiyamilimani_test1.domain.usecases.Auth

import androidx.credentials.Credential
import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.model.User
import com.example.sautiyamilimani_test1.domain.repository.AuthRepository

class LinkAccountWithEmailUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email:String,password:String):Resource<User>{
        return authRepository.linkAccountWithEmail(email,password)
    }
}