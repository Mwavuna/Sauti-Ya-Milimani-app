package com.example.sautiyamilimani_test1.presentation.Mappers

import com.example.sautiyamilimani_test1.domain.model.Status

fun statusToString(status: Status):String=when(status) {
    Status.ACTIVE -> "Active"
    Status.INACTIVE -> "Inactive"
    Status.PENDING -> "Pending"

}

fun stringToStatus(status:String):Status=when(status){
    "Active"->Status.ACTIVE
    "Inactive"-> Status.INACTIVE
    else-> Status.PENDING

}