package com.example.sautiyamilimani.domain.repository

import com.example.sautiyamilimani.domain.model.Resource
import com.example.sautiyamilimani.domain.model.User

interface AuthRepository {
    suspend fun loginWithGoogle(idToken:String):Resource<User>
    suspend fun logout(): Resource<Unit>
    fun getCurrentUser():User?
}