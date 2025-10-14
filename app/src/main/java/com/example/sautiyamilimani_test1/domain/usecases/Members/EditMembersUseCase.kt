package com.example.sautiyamilimani_test1.domain.usecases.Members

import com.example.sautiyamilimani_test1.domain.model.Member
import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.repository.MembersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EditMembersUseCase @Inject constructor(
    private val membersRepository: MembersRepository,
) {
    suspend operator fun invoke(member: Member): Flow<Resource<Member>> {
        return membersRepository.editMember(member)
    }
}