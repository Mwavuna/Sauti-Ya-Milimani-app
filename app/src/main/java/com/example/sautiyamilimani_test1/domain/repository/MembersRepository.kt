package com.example.sautiyamilimani_test1.domain.repository

import com.example.sautiyamilimani_test1.domain.model.Member
import com.example.sautiyamilimani_test1.domain.model.Resource
import kotlinx.coroutines.flow.Flow


interface MembersRepository {
    suspend fun addMember(member: Member): Flow<Resource<Member>>
    suspend fun editMember(member: Member): Flow<Resource<Member>>
    suspend fun deleteMember(memberId:Int?): Flow<Resource<Unit>>
    suspend fun getMembers():Flow<Resource<List<Member>>>
    suspend fun getMemberById(memberId: Int): Flow<Resource<Member>>
}