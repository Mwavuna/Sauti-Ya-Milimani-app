package com.example.sautiyamilimani.data.repository

import com.example.sautiyamilimani.data.mapper.FireBaseUserToUser
import com.example.sautiyamilimani.domain.model.Resource
import com.example.sautiyamilimani.domain.model.User
import com.example.sautiyamilimani.domain.repository.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun loginWithGoogle(idToken: String): Resource<User> {
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val result = firebaseAuth.signInWithCredential(credential).await()
            Resource.Success(FireBaseUserToUser.toUSer(result.user))
        } catch (e: Exception) {
            Resource.Failure(e.localizedMessage, e)
        }

    }


    override suspend fun logout(): Resource<Unit> {
        return try {
            firebaseAuth.signOut()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Failure(e.localizedMessage ?: "Unknown error", e)
        }

    }

    override fun getCurrentUser(): User? {
        return FireBaseUserToUser.toUSer(fireBaseUSer = firebaseAuth.currentUser)
    }
}

/*
    Google Login
    Step-by-step:

    Get Google ID Token: From GoogleIdTokenCredential on the client.

    Create Firebase credential: GoogleAuthProvider.getCredential(idToken, null)

    Sign in with Firebase: firebaseAuth.signInWithCredential(credential)

    Map FirebaseUser → User

    Return Resource:

    Success → user object

    Failure → exception and optional message


Flow in app

User taps “Sign in with Google” on UI.

Google Sign-In client shows account picker → user selects an account.

Google generates an ID token for that account.

Your repository gets the ID token and passes it to Firebase:
 */