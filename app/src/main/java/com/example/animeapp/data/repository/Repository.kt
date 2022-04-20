package com.example.animeapp.data.repository

import com.example.animeapp.domain.repository.DataStoreOperations
import com.example.animeapp.domain.repository.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local: LocalDataSource,
//    private val remote: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }

    suspend fun saveOnLoginState(completed: Boolean) {
        dataStore.saveLoginState(completed = completed)
    }

    fun readOnLoginState(): Flow<Boolean> {
        return dataStore.readOnLoginState()
    }
}