package com.application.dictionaryapp.repo

import com.application.dictionaryapp.helper.Resource
import com.application.dictionaryapp.networks.ApiDataSource
import com.application.dictionaryapp.networks.BaseDataSource
import com.application.dictionaryapp.networks.DictionaryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: ApiDataSource) : BaseDataSource() {
    suspend fun callMeanings(word: String) : Flow<Resource<DictionaryResponse>>{
        return flow {
            emit(safeApiCall { api.callMeanings(word) })
        }.flowOn(Dispatchers.IO)
    }
}