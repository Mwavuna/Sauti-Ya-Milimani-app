package com.example.sautiyamilimani_test1.presentation.Mappers

import com.example.sautiyamilimani_test1.domain.model.Role

fun roleToString(role: Role):String{
    return when(role){
        Role.MEMBER -> "Member"
        Role.LEADER -> "Leader"
    }
}


fun stringToRole(role: String):Role{
    return when(role){
        "Leader"->Role.LEADER
        else->Role.MEMBER
    }
}
