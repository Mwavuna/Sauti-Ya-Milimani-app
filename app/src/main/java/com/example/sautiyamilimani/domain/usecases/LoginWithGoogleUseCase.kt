package com.example.sautiyamilimani.domain.usecases

import com.example.sautiyamilimani.domain.model.Resource
import com.example.sautiyamilimani.domain.model.User
import com.example.sautiyamilimani.domain.repository.AuthRepository
import javax.inject.Inject

//@Inject constructor tells dagger hilt to inject an implementation of a certain repository it provides that has this return type
class LoginWithGoogleUseCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend operator fun invoke(idToken: String): Resource<User> {
        return repository.loginWithGoogle(idToken)
    }
}