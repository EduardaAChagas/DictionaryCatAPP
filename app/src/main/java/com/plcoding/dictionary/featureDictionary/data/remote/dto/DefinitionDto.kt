package com.plcoding.dictionary.featureDictionary.data.remote.dto

import com.plcoding.dictionary.featureDictionary.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val example: String,
    val synonyms: List<String>
) {
    fun toDefinition(): Definition {
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}