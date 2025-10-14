package com.example.sautiyamilimani_test1.domain.usecases.Members

data class MembersUseCases (
    val addMembersUseCase: AddMembersUseCase,
    val editMembersUseCase: EditMembersUseCase,
    val deleteMembersUseCase: DeleteMembersUseCase,
    val getMembersUseCase: GetMembersUseCase,
    val getMembersByIdUseCase: GetMembersByIdUseCase
)