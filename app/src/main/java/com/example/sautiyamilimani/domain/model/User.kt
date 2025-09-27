package com.example.sautiyamilimani.domain.model

data class User(
    val uid: String,
    val email:String?=null,
    val displayName:String?=null,
    val profilePic:String?=null
    )