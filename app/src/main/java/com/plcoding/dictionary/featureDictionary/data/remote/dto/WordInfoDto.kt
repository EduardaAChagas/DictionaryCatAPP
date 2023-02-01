package com.plcoding.dictionary.featureDictionary.data.remote.dto

import com.plcoding.dictionary.featureDictionary.data.local.entity.WordInfoEntity
import com.plcoding.dictionary.featureDictionary.domain.model.WordInfo

data class WordInfoDto(
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val origin: String,
    val phonetics: List<PhoneticDto>,
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map {it.toMeaning()},
            origin = origin,
            phonetic = phonetic,
            word = word
        )
    }

}