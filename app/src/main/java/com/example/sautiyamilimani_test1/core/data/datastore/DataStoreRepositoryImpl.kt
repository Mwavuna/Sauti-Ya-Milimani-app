package com.example.sautiyamilimani_test1.core.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.sautiyamilimani_test1.core.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


val Context.datastore by preferencesDataStore(name = "settings")




class DataStoreRepositoryImpl @Inject constructor(private val context: Context):DataStoreRepository {
    val datastore=context.datastore
   

    override suspend fun firstTimeStatus(status: Boolean)  {
        datastore.edit{
                preferences -> preferences[UserPrefKeys.FIRST_TIME]=status
        }
    }


    override fun getFirstTimeStatus(): Flow<Boolean> =
        datastore.data.map { preferences -> preferences[UserPrefKeys.FIRST_TIME] ?: true }
}