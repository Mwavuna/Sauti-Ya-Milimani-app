package com.example.sautiyamilimani_test1.domain.usecases.Auth

data class AuthUseCases(
    val logout: LogoutUseCase,
    val loginWithGoogle: LoginWithGoogleUseCase,
    val loginWithEmail: LoginWithEmailUseCase,
    val signUpWithEmail: SignUpWithEmailUseCase,
    val getCurrentUser: GetCurrentUserUseCase,
    val loginAnonymously: LoginAnonymouslyUseCase,
    val linkAccountWithEmail: LinkAccountWithEmailUseCase,
    val linkWithGoogle: LinkWithGoogleUSeCase,
)
