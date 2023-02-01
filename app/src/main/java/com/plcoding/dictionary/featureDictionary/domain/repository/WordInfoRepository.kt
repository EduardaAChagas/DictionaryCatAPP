package com.plcoding.dictionary.featureDictionary.domain.repository

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.featureDictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow


interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}