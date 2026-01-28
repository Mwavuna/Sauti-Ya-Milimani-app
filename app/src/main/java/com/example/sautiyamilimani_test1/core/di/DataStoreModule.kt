package com.example.sautiyamilimani_test1.core.di

import android.content.Context
import com.example.sautiyamilimani_test1.core.data.datastore.DataStoreRepositoryImpl
import com.example.sautiyamilimani_test1.core.domain.repository.DataStoreRepository
import com.example.sautiyamilimani_test1.domain.usecases.DataStore.DataStoreUseCases
import com.example.sautiyamilimani_test1.domain.usecases.DataStore.GetFirstTimeStatusUseCase
import com.example.sautiyamilimani_test1.domain.usecases.DataStore.SetFirstTimeStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule{
    @Provides
    @Singleton
    fun providesDatastoreRepository(
    @ApplicationContext context: Context
    ): DataStoreRepository= DataStoreRepositoryImpl(context=context)
}

@Module
@InstallIn(SingletonComponent::class)
object DataStoreUseCases{
    @Provides
    @Singleton
    fun providesDataStoreUseCases(dataStoreRepository: com.example.sautiyamilimani_test1.domain.repository.DataStoreRepository): com.example.sautiyamilimani_test1.domain.usecases.DataStore.DataStoreUseCases {
        return DataStoreUseCases(
            getFirstTimeStatus = GetFirstTimeStatusUseCase(dataStoreRepository),
            setFirstTimeStatus = SetFirstTimeStatusUseCase(dataStoreRepository)
        )
    }
}