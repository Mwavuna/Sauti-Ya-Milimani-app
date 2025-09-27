package com.example.sautiyamilimani.data.mapper

import com.example.sautiyamilimani.domain.model.User
import com.google.firebase.auth.FirebaseUser

object FireBaseUserToUser{
    fun toUSer(fireBaseUSer: FirebaseUser?): User?{
        return if(fireBaseUSer!=null)
        {
            User(
                uid = fireBaseUSer.uid,
                email = fireBaseUSer.email,
                displayName = fireBaseUSer.displayName,
                profilePic = fireBaseUSer.photoUrl.toString()
            )
        }else{
            null
        }
    }
}