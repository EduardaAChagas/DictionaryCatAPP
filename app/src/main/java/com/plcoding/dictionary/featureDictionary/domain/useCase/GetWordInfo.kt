package com.plcoding.dictionary.featureDictionary.domain.useCase

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.featureDictionary.domain.model.WordInfo
import com.plcoding.dictionary.featureDictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()) {
            return flow {  }
        }
        return repository.getWordInfo(word)
    }
}