package com.example.sautiyamilimani_test1.di

import android.content.Context
import com.example.sautiyamilimani_test1.core.data.datastore.DataStoreRepositoryImpl
import com.example.sautiyamilimani_test1.data.repository.AuthRepositoryImpl
import com.example.sautiyamilimani_test1.domain.repository.AuthRepository
import com.example.sautiyamilimani_test1.domain.repository.DataStoreRepository
import com.example.sautiyamilimani_test1.domain.usecases.Auth.AuthUseCases
import com.example.sautiyamilimani_test1.domain.usecases.Auth.GetCurrentUserUseCase
import com.example.sautiyamilimani_test1.domain.usecases.Auth.LinkAccountWithEmailUseCase
import com.example.sautiyamilimani_test1.domain.usecases.Auth.LinkWithGoogleUSeCase
import com.example.sautiyamilimani_test1.domain.usecases.Auth.LoginAnonymouslyUseCase
import com.example.sautiyamilimani_test1.domain.usecases.Auth.LoginWithEmailUseCase
import com.example.sautiyamilimani_test1.domain.usecases.Auth.LoginWithGoogleUseCase
import com.example.sautiyamilimani_test1.domain.usecases.Auth.LogoutUseCase
import com.example.sautiyamilimani_test1.domain.usecases.Auth.SignUpWithEmailUseCase
import com.example.sautiyamilimani_test1.domain.usecases.DataStore.DataStoreUseCases
import com.example.sautiyamilimani_test1.domain.usecases.DataStore.GetFirstTimeStatusUseCase
import com.example.sautiyamilimani_test1.domain.usecases.DataStore.SetFirstTimeStatusUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthRepositoryModule{

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth{
        val auth=FirebaseAuth.getInstance()
        return auth
    }

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
            signUpWithEmail = SignUpWithEmailUseCase(authRepository),
            loginWithEmail = LoginWithEmailUseCase(authRepository),
            loginWithGoogle = LoginWithGoogleUseCase(authRepository),
            getCurrentUser = GetCurrentUserUseCase(authRepository),
            logout = LogoutUseCase(authRepository),
            loginAnonymously = LoginAnonymouslyUseCase(authRepository),
            linkAccountWithEmail = LinkAccountWithEmailUseCase(authRepository),
            linkWithGoogle = LinkWithGoogleUSeCase(authRepository),
        )
    }
}


