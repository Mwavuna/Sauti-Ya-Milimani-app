package com.example.sautiyamilimani.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sautiyamilimani.domain.model.Resource
import com.example.sautiyamilimani.domain.model.User
import com.example.sautiyamilimani.domain.usecases.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel(){

    private val _loginState= MutableStateFlow<Resource<User>>(Resource.Loading)
    val loginState: StateFlow<Resource<User>> = _loginState


    private val _logoutState=MutableStateFlow<Resource<Unit>>(Resource.Loading)
    val logOutState:StateFlow<Resource<Unit>> = _logoutState

    private val _currentUser=MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun loginWithGoogle(idToken:String)={
        viewModelScope.launch {
            _loginState.value=Resource.Loading
            val result=authUseCases.loginWithGoogle(idToken)
            _loginState.value=result
        }
    }

    fun getCurrentUser(){
        _currentUser.value=authUseCases.getCurrentUser()
    }

    fun logout(){
            viewModelScope.launch {
            _logoutState.value= Resource.Loading
                val result = authUseCases.logout()
                _logoutState.value=result
            }
        }
}