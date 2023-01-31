package com.plcoding.dictionary.featureDictionary.data.remote
import com.plcoding.dictionary.featureDictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryAPI {
    @GET("/api/v2/entries/pt/{word}")
    suspend fun getWordInfo(
        @Path("word") word: String
    ): List<WordInfoDto>
}