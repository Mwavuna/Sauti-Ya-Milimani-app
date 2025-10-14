package com.example.sautiyamilimani_test1.data.repository

import androidx.compose.runtime.snapshots.SnapshotApplyResult
import com.example.sautiyamilimani_test1.data.mapper.FireBaseUserToUser
import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.model.User
import com.example.sautiyamilimani_test1.domain.repository.AuthRepository
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun loginAnonymously(): Resource<User> {
        return try {
            val user = firebaseAuth.signInAnonymously().await().user
            Resource.Success(FireBaseUserToUser.toUSer(user))
        } catch (e: Exception) {
            Resource.Failure(e.message ?: "Unknown Error", e)
        }
    }

    override suspend fun linkAccountWithEmail(
        email: String,
        password: String
    ): Resource<User> {
        return try {
            val credential = EmailAuthProvider.getCredential(email, password)
            val result = firebaseAuth.currentUser?.linkWithCredential(credential)?.await()
            val user = FireBaseUserToUser.toUSer(result?.user)
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Failure(e.message ?: "Unknown Error", e)
        }

    }


    override suspend fun loginWithEmail(
        email: String,
        password: String
    ): Resource<User> {
        return try {
            val user = firebaseAuth.signInWithEmailAndPassword(email, password).await().user
            Resource.Success(FireBaseUserToUser.toUSer(user))
        } catch (e: Exception) {
            Resource.Failure(e.localizedMessage, e)
        }

    }

    override suspend fun signUpWithEmail(
        email: String,
        password: String
    ): Resource<User> {
        return try {
            val firebaseUser =
                firebaseAuth.createUserWithEmailAndPassword(email, password).await().user
            Resource.Success(FireBaseUserToUser.toUSer(firebaseUser))

        } catch (e: Exception) {
            Resource.Failure(e.localizedMessage, e)
        }
    }

    override suspend fun loginWithGoogle(idToken: String): Resource<User> {
        if (idToken.isEmpty()) {
            return Resource.Failure("ID token is null or empty")
        }
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

    override suspend fun linkWithGoogle(idToken: String): Resource<User> {
        return try {


            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val user = firebaseAuth.currentUser?.linkWithCredential(credential)?.await()?.user
            Resource.Success(FireBaseUserToUser.toUSer(user))
        } catch (e: Exception) {
            Resource.Failure(e.localizedMessage ?: "Unknown Error Occurred!", e)
        }
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