package com.example.sautiyamilimani_test1.domain.usecases.Auth

import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.model.User
import com.example.sautiyamilimani_test1.domain.repository.AuthRepository

class LinkWithGoogleUSeCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(idToken:String): Resource<User> {
        return authRepository.linkWithGoogle(idToken)

    }
}