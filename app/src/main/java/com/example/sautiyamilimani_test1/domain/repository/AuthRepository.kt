package com.example.sautiyamilimani_test1.domain.repository

import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.model.User

interface AuthRepository {
    suspend fun loginAnonymously():Resource<User>
    suspend fun linkAccountWithEmail(email: String,password:String): Resource<User>
    suspend fun loginWithEmail(email:String,password:String):Resource<User>
    suspend fun signUpWithEmail(email:String,password:String):Resource<User>
    suspend fun loginWithGoogle(idToken:String):Resource<User>
    suspend fun logout(): Resource<Unit>
    fun getCurrentUser():User?
    suspend fun linkWithGoogle(idToken: String):Resource<User>
   }