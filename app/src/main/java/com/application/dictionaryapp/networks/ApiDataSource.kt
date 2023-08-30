package com.application.dictionaryapp.networks

import javax.inject.Inject

class ApiDataSource @Inject constructor(private val dictionaryService: DictionaryService) {
    suspend fun callMeanings(word: String) = dictionaryService.callMeanings(word)
}