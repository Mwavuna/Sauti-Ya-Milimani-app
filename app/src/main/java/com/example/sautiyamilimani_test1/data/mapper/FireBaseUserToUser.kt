package com.example.sautiyamilimani_test1.data.mapper

import com.example.sautiyamilimani_test1.domain.model.User
import com.google.firebase.auth.FirebaseUser

object FireBaseUserToUser{
    fun toUSer(fireBaseUSer: FirebaseUser?): User?{
        return if(fireBaseUSer!=null)
        {
            User(
                uid = fireBaseUSer.uid,
                email = fireBaseUSer.email,
                displayName = fireBaseUSer.displayName,
                profilePic = fireBaseUSer.photoUrl.toString(),
                isAnonymous = fireBaseUSer.isAnonymous,
                providers = fireBaseUSer.providerData.map{it.providerId}
            )
        }else{
            null
        }
    }
}