package com.example.sautiyamilimani_test1.domain.model

data class RegistrationFormState(
    val email:String="",
    val emailError:String?=null,
    val password:String="",
    val passwordError:String?=null,
    val repeatedPassword:String="",
    val repeatedPasswordError:String?=null,

)
