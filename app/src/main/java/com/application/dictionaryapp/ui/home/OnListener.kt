package com.application.dictionaryapp.ui.home

import com.application.dictionaryapp.networks.DictionaryResponse

abstract class OnListener {
    // for sound clicked
    abstract fun onSound(phonetic: DictionaryResponse.DictionaryResponseItem.Phonetic)

}