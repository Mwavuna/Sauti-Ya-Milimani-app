package com.example.sautiyamilimani.domain.usecases

import com.example.sautiyamilimani.domain.model.User
import com.example.sautiyamilimani.domain.repository.AuthRepository
import javax.inject.Inject

//@Inject constructor tells dagger hilt to inject an implementation of a certain repository it provides that has this return type

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): User? {
        return repository.getCurrentUser()
    }

    //Another way to implement this would be to create a .execute function
//    suspend fun execute():User?{
//        return repository.GetCurrentUserUseCase()
//    }
    //this is a longer method as to use this we would need to call
    //val result=GetCurrentUser.execute(){}
//    but now we can just use
//    val result=GetCurrentUser(){}
}