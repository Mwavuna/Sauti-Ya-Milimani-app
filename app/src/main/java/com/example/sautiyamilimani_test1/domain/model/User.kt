package com.example.sautiyamilimani_test1.domain.model

data class User(
    val uid: String,
    val email:String?=null,
    val displayName:String?=null,
    val profilePic:String?=null,
    val isAnonymous:Boolean=false,
    val providers: List<String> = emptyList()
)