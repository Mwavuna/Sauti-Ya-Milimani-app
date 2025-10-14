package com.example.sautiyamilimani_test1.data.repository

import android.content.Context
import com.example.sautiyamilimani_test1.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

import androidx.datastore.preferences.core.booleanPreferencesKey

class DataStoreRepositoryImpl(private val context: Context): DataStoreRepository {

    val DATASTORE=context.datastore
    override suspend fun setFirstTimeStatus(status: Boolean) {
        DATASTORE.edit{
            prefs->prefs[UserPrefKeys.FIRST_TIME]=value
        }
    }

    override fun getFirstTimeStatus(): Flow<Boolean> {
       return DATASTORE.map{
           prefs->prefs[UserPrefKeys.FIRST_TIME]?:true
       }
    }

}


object UserPrefKeys {
    val FIRST_TIME = booleanPreferencesKey("is_first_time")
}
