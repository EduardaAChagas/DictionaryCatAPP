package com.plcoding.dictionary.featureDictionary.data.repository

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.featureDictionary.data.local.WordInfoDao
import com.plcoding.dictionary.featureDictionary.data.remote.DictionaryAPI
import com.plcoding.dictionary.featureDictionary.domain.model.WordInfo
import com.plcoding.dictionary.featureDictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryAPI,
    private val dao: WordInfoDao
): WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow{
        emit(Resource.Loading())

        //the cache wordinfos
        val wordInfos = dao.getWordInfos(word).map {it.toWordInfo()}
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map {it.word})
            dao.insertWordInfos(remoteWordInfos.map {it.toWordInfoEntity()})
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Something went wrong.",
                data = wordInfos
                    ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map {it.toWordInfo()}
        emit(Resource.Success(newWordInfos))
    }
}