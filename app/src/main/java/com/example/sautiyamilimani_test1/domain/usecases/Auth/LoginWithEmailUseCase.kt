package com.example.sautiyamilimani_test1.domain.usecases.Auth

import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.model.User
import com.example.sautiyamilimani_test1.domain.repository.AuthRepository
import javax.inject.Inject

//@Inject constructor tells dagger hilt to inject an implementation of a certain repository it provides that has this return type
class LoginWithEmailUseCase @Inject constructor(
    private val repository: AuthRepository
){
    suspend operator fun invoke( email:String, password:String): Resource<User> {
        return repository.loginWithEmail( email,password)
    }
}