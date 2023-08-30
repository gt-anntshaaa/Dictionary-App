package com.application.dictionaryapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.dictionaryapp.helper.Resource
import com.application.dictionaryapp.helper.SingleLiveEvent
import com.application.dictionaryapp.networks.DictionaryResponse
import com.application.dictionaryapp.repo.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo : HomeRepository) : ViewModel() {
    private val _result = SingleLiveEvent<Resource<DictionaryResponse>>()
    val result get() = _result


    fun fetchResult(word: String){
        viewModelScope.launch {
            repo.callMeanings(word).collect{
                _result.value = it
            }
        }
    }
}