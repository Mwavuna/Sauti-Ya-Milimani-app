package com.example.sautiyamilimani.di

import com.example.sautiyamilimani.data.repository.AuthRepositoryImpl
import com.example.sautiyamilimani.domain.repository.AuthRepository
import com.example.sautiyamilimani.domain.usecases.AuthUseCases
import com.example.sautiyamilimani.domain.usecases.GetCurrentUserUseCase
import com.example.sautiyamilimani.domain.usecases.LoginWithGoogleUseCase
import com.example.sautiyamilimani.domain.usecases.LogoutUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth= FirebaseAuth.getInstance()


/*
 This says:
    “Whenever anyone asks for a FirebaseAuth, give them this one single instance.”

    The @Singleton ensures everyone shares the same FirebaseAuth object, no duplicates.


 */



    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository{
        return AuthRepositoryImpl(firebaseAuth)
    }

/*
    This says:

    “Whenever anyone asks for an AuthRepository (the interface), give them the AuthRepositoryImpl (the actual working class).”

    Also, give the implementation the FirebaseAuth instance we already created above.

    Hilt automatically injects the FirebaseAuth we provided — no need to call getInstance() again.
 */

}


@Module
@InstallIn(SingletonComponent::class)
object AuthUseCases{
    @Provides
    @Singleton
    fun providesAuthUseCases(authRepository: AuthRepository): AuthUseCases{
        return AuthUseCases(
            logout = LogoutUseCase(authRepository),
            loginWithGoogle = LoginWithGoogleUseCase(authRepository),
            getCurrentUser = GetCurrentUserUseCase(authRepository)
        )
    }
}