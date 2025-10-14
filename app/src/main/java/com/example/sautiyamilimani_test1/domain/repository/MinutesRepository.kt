package com.example.sautiyamilimani_test1.domain.repository

import com.example.sautiyamilimani_test1.domain.model.Minute
import com.example.sautiyamilimani_test1.domain.model.Resource
import jakarta.inject.Inject

interface MinutesRepository{

    suspend fun AddMinute(): Resource<Minute>


}