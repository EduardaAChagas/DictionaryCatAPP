package com.plcoding.dictionary.featureDictionary.data.remote.dto

import com.plcoding.dictionary.featureDictionary.domain.model.Meaning

data class MeaningDto(
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
) {
    fun toMeaning(): Meaning {
        return Meaning(
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech
        )
    }
}