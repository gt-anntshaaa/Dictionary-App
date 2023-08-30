package com.application.dictionaryapp.networks

import com.application.dictionaryapp.helper.EndPoint
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryService {
    @GET(EndPoint.GET_DICTIONARY)
    suspend fun callMeanings(@Path("word") word: String) : Response<DictionaryResponse>
}