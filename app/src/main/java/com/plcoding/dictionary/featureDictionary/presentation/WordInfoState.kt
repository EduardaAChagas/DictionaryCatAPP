package com.plcoding.dictionary.featureDictionary.presentation

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.featureDictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean  = false
)
