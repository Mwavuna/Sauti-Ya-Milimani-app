package com.example.sautiyamilimani_test1.data.repository

import com.example.sautiyamilimani_test1.data.local.daos.MemberDao
import com.example.sautiyamilimani_test1.data.mapper.toDomain
import com.example.sautiyamilimani_test1.data.mapper.toEntity
import com.example.sautiyamilimani_test1.domain.model.Member
import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.repository.MembersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MembersRepositoryImpl @Inject constructor(
    private val memberDao: MemberDao

) : MembersRepository {
    override suspend fun addMember(member: Member): Flow<Resource<Member>> = flow {
        emit(Resource.Loading)
        try {
            memberDao.insertMember(member.toEntity())
            emit(Resource.Success(member))

        } catch (e: Exception) {
            emit(Resource.Failure(e.localizedMessage, e))
        }
    }


    override suspend fun editMember(member: Member): Flow<Resource<Member>> = flow {
        emit(Resource.Loading)
        try {
            memberDao.updateMember(member.toEntity())
            emit(Resource.Success(member))
        } catch (e: Exception) {
            emit(Resource.Failure(e.localizedMessage, e))
        }
    }

    override suspend fun deleteMember(memberId: Int?): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        try {
            val member = memberDao.getMemberById(memberId)
            if (member != null) {
                memberDao.deleteMember(member)
                emit(Resource.Success(Unit))
            }

        } catch (e: Exception) {
            emit(Resource.Failure(e.localizedMessage, e))
        }
    }

    override suspend fun getMembers(): Flow<Resource<List<Member>>> =flow{
        emit(Resource.Loading)
        try{

            memberDao.getAllMembers().collect {
                entities->
                emit(Resource.Success(entities.map { it.toDomain() }))
            }

        }catch (e: Exception){
            emit(Resource.Failure(e.localizedMessage, e))
        }
    }

    override suspend fun getMemberById( memberId: Int ): Flow<Resource<Member>> =flow {
        emit(Resource.Loading)
        try {
            val member = memberDao.getMemberById(memberId)
            if (member != null) {
                emit(Resource.Success(member.toDomain()))
            }

        } catch (e: Exception) {
            emit(Resource.Failure(e.localizedMessage, e))
        }
    }

}