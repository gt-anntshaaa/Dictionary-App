package com.application.dictionaryapp.networks

class DictionaryResponse : ArrayList<DictionaryResponse.DictionaryResponseItem>(){
    data class DictionaryResponseItem(
        val meanings: List<Meaning>,
        val origin: String,
        val phonetic: String,
        val phonetics: List<Phonetic>,
        val word: String
    ) {
        data class Meaning(
            val definitions: List<Definition>,
            val partOfSpeech: String
        ) {
            data class Definition(
                val antonyms: List<Any>,
                val definition: String,
                val example: String,
                val synonyms: List<Any>
            )
        }
    
        data class Phonetic(
            val audio: String,
            val text: String
        )
    }
}