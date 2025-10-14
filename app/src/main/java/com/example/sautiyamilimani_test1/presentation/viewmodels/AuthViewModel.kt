package com.example.sautiyamilimani_test1.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.model.User
import com.example.sautiyamilimani_test1.domain.usecases.Auth.AuthUseCases
import com.example.sautiyamilimani_test1.presentation.navigation.Screen
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.contains

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    private val _signUpState = MutableStateFlow<Resource<User>>(Resource.Idle)
    val signUpState: StateFlow<Resource<User>> = _signUpState

    private val _loginState = MutableStateFlow<Resource<User>>(Resource.Idle)
    val loginState: StateFlow<Resource<User>> = _loginState


    private val _logoutState = MutableStateFlow<Resource<Unit>>(Resource.Idle)
    val logOutState: StateFlow<Resource<Unit>> = _logoutState


    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    private val _isAdmin = MutableStateFlow<Boolean>(false)
    val isAdmin: StateFlow<Boolean> = _isAdmin

    private val _isAnonymous = MutableStateFlow<Boolean>(currentUser.value?.isAnonymous ?: true)
    val isAnonymous: StateFlow<Boolean> = _isAnonymous


    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent

    private val _admins =
        listOf("gregsonmwavuna3@gmail.com", "grahammwangata@gmail.com", "elishamumba@gmail.com")

    init {
        _isLoading.value = true
        viewModelScope.launch {
            _currentUser.value = getCurrentUser()
            if (currentUser.value == null) {
                loginAnonymously()
            }
        }
    }

    fun loginAnonymously() {
        viewModelScope.launch {
            when (val result = authUseCases.loginAnonymously()) {
                is Resource.Failure -> {
                    _loginState.value = result
                    _uiEvent.emit(
                        UiEvent.SnackBarEvent(
                            result.message ?: "Unknown Error Occurred!"
                        )
                    )
                }

                is Resource.Success<*> -> {
                    val user = getCurrentUser()
                    _currentUser.value = user
                }

                else -> Unit
            }
        }
    }

fun loginWithGoogle(idToken: String) = viewModelScope.launch {
    _isLoading.value = true
    _loginState.value = Resource.Loading

    try {

        if (isAnonymous()) {
            try {
                // Try linking anonymous account to Google
                authUseCases.linkWithGoogle(idToken)
                _uiEvent.emit(UiEvent.SnackBarEvent("Account linked successfully!"))
            } catch (e: FirebaseAuthUserCollisionException) {
                // The Google account already exists – sign in instead
                _uiEvent.emit(UiEvent.SnackBarEvent("Account already exists, signing you in..."))
                authUseCases.loginWithGoogle(idToken)
            }
        } else {
            // Normal login (non-anonymous user)
            authUseCases.loginWithGoogle(idToken)
        }
        _currentUser.value = getCurrentUser()
        _loginState.value = Resource.Success(_currentUser.value)

    } catch (e: Exception) {
        _uiEvent.emit(UiEvent.SnackBarEvent(e.localizedMessage ?: "Unknown Error Occurred!"))
    } finally {
        resetStates()
        _isLoading.value = false
    }
}







    //the user is already anonymous when we reach here
    fun signUpWithEmail(email: String, password: String) = viewModelScope.launch {
        _isLoading.value = true
        _signUpState.value = Resource.Loading
        _uiEvent.emit(UiEvent.SnackBarEvent("Checking Email..."))

        //try linking anonymous account with the supplied email and password
        //if email was already used,tell user to sign in or use a different account
        //but just for safety
        try {
            if (isAnonymous()) {
                //We try linking the anonymous account with the details given
                _uiEvent.emit(UiEvent.SnackBarEvent("Signing you in..."))
                authUseCases.linkAccountWithEmail(email, password)
            } else {
                //if the user is not anonymous by an chance:we try to create a new account with the given details
                //we then automatically sign the use in
                _uiEvent.emit(UiEvent.SnackBarEvent("Creating Account..."))
                authUseCases.signUpWithEmail(email, password)
            }
            _currentUser.value = getCurrentUser()
            _uiEvent.emit(UiEvent.SnackBarEvent("Welcome aboard! 🎉"))
        } catch (e: FirebaseAuthUserCollisionException) {
            //if the user details already exist,inform user
            _uiEvent.emit(UiEvent.SnackBarEvent("Email already in use,please sign in or use another email..."))
        } catch (e: Exception) {
            //show any other exception
            _uiEvent.emit(UiEvent.SnackBarEvent(e.message ?: "Unknown Error Occurred!"))
        } finally {
            resetStates()
            _isLoading.value = false
        }

    }

    fun loginWithEmail(email: String, password: String) = viewModelScope.launch {
        _isLoading.value = true
        _loginState.value = Resource.Loading
        //also when we reach here the the user is anonymous
        try {
            if (isAnonymous()) {
                //try linking with email and password
                authUseCases.linkAccountWithEmail(email,password)
            } else {
                //try signing user in
                authUseCases.loginWithEmail(email,password)
            }
            _currentUser.value=getCurrentUser()
            _loginState.value= Resource.Success(currentUser.value)
        }catch (e: FirebaseAuthUserCollisionException){

            // Email already used → log in directly
            try {
                authUseCases.loginWithEmail(email, password)
                _currentUser.value = getCurrentUser()
                _loginState.value = Resource.Success(_currentUser.value)
            } catch (inner: Exception) {
                _uiEvent.emit(UiEvent.SnackBarEvent(inner.localizedMessage ?: "Login failed."))
            }
        } catch (e: Exception) {
            UiEvent.SnackBarEvent(
                e.localizedMessage ?: "Unknown Error Occurred!"
            )
        } finally {
            resetStates()
            _isLoading.value = false
        }
    }


    suspend fun getCurrentUser(): User? {
        _isLoading.value = true
        val result = authUseCases.getCurrentUser()
        _currentUser.value = result
        if (_admins.contains(result?.email)) {
            _isAdmin.value = true
        }
        _isLoading.value = false
        return result
    }

    fun logout() =
        viewModelScope.launch {
            _isLoading.value = true
            _logoutState.value = Resource.Loading
            when (val result = authUseCases.logout()) {
                is Resource.Failure -> {
                    _logoutState.value = result
                    _uiEvent.emit(
                        UiEvent.SnackBarEvent(
                            result.message ?: "Unknown Error Occurred!"
                        )
                    )
                }

                is Resource.Success<*> -> {
                    _logoutState.value = result
                    _currentUser.value=null
                }

                else -> Unit
            }
            resetStates()
            _isAdmin.value = false
            _isLoading.value = false
        }

    fun resetStates() {
        _loginState.value = Resource.Idle
        _signUpState.value = Resource.Idle
        _logoutState.value = Resource.Idle
    }

    //helper functions
    fun isAnonymous(): Boolean = currentUser.value?.isAnonymous ?: true

    fun hasLinkedAccount(): Boolean {
        val user = currentUser.value ?: return false
        return user.providers.any { it != "firebase" } //if another provider exits apart for firebase,it was linked
    }

    fun shouldBeLinked(): Boolean {
        return isAnonymous() && !hasLinkedAccount()
    }


}


sealed class UiEvent {
    data class SnackBarEvent(val message: String) : UiEvent()
    data class NavigateEvent(val route: String) : UiEvent()
}





