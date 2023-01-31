package com.plcoding.dictionary.featureDictionary.data.util

import java.lang.reflect.Type

interface JSONParser {
    fun <T> fromJson(json: String, type: Type): T?
    fun <T> toJson(obj:T, type: Type): String?

}