package com.plcoding.dictionary.featureDictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.plcoding.dictionary.featureDictionary.data.local.Converters
import com.plcoding.dictionary.featureDictionary.data.local.WordInfoDatabase
import com.plcoding.dictionary.featureDictionary.data.remote.DictionaryAPI
import com.plcoding.dictionary.featureDictionary.data.repository.WordInfoRepositoryImpl
import com.plcoding.dictionary.featureDictionary.data.util.GsonParser
import com.plcoding.dictionary.featureDictionary.domain.repository.WordInfoRepository
import com.plcoding.dictionary.featureDictionary.domain.useCase.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo {
        return GetWordInfo(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        db: WordInfoDatabase,
        api: DictionaryAPI
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDataBase(application: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            application, WordInfoDatabase::class.java, "wordDb"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryAPI(): DictionaryAPI {
        return Retrofit.Builder()
            .baseUrl(DictionaryAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryAPI::class.java)
    }
}