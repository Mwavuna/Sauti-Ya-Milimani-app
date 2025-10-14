package com.example.sautiyamilimani_test1.di

import android.content.Context
import androidx.room.Room
import com.example.sautiyamilimani_test1.data.local.daos.MemberDao
import com.example.sautiyamilimani_test1.data.local.databases.AppDatabase
import com.example.sautiyamilimani_test1.domain.model.Member
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "sauti_ya_milimani_db"
    ).build()

    @Provides
    fun providesMemberDao(db: AppDatabase): MemberDao =db.memberDao()
}