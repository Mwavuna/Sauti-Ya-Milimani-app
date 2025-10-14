package com.example.sautiyamilimani_test1.di

import com.example.sautiyamilimani_test1.data.local.daos.MemberDao
import com.example.sautiyamilimani_test1.data.repository.MembersRepositoryImpl
import com.example.sautiyamilimani_test1.domain.repository.MembersRepository
import com.example.sautiyamilimani_test1.domain.usecases.Members.AddMembersUseCase
import com.example.sautiyamilimani_test1.domain.usecases.Members.DeleteMembersUseCase
import com.example.sautiyamilimani_test1.domain.usecases.Members.EditMembersUseCase
import com.example.sautiyamilimani_test1.domain.usecases.Members.GetMembersByIdUseCase
import com.example.sautiyamilimani_test1.domain.usecases.Members.GetMembersUseCase
import com.example.sautiyamilimani_test1.domain.usecases.Members.MembersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MembersModule {
    @Provides
    @Singleton
    fun providesMembersRepository(
        memberDao: MemberDao
    ): MembersRepository {
        return MembersRepositoryImpl(memberDao)
    }

}

@Module
@InstallIn(SingletonComponent::class)
object MembersUseCases{
    @Provides
    @Singleton
    fun providesMembersUseCases(
    membersRepository: MembersRepository
    ): MembersUseCases{
        return MembersUseCases(
            addMembersUseCase = AddMembersUseCase(membersRepository),
            editMembersUseCase = EditMembersUseCase(membersRepository),
            deleteMembersUseCase = DeleteMembersUseCase(membersRepository),
            getMembersUseCase = GetMembersUseCase(membersRepository),
            getMembersByIdUseCase = GetMembersByIdUseCase(membersRepository)
        )
    }
}

