package com.example.sautiyamilimani.domain.usecases

data class AuthUseCases(
    val logout: LogoutUseCase,
    val loginWithGoogle: LoginWithGoogleUseCase,
    val getCurrentUser: GetCurrentUserUseCase
)
