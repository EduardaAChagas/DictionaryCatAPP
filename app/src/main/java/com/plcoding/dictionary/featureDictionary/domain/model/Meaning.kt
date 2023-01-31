package com.plcoding.dictionary.featureDictionary.domain.model

import com.plcoding.dictionary.featureDictionary.data.remote.dto.DefinitionDto

data class Meaning(
    val definitions: List<Definition>,
    val partOfSpeech: String,
)
