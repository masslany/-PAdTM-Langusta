package com.example.mobilne_projekt.data.db

import androidx.room.TypeConverter
import com.example.mobilne_projekt.data.db.entity.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {

    @TypeConverter
    fun fromWordList(value: List<Word>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Word>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toWordList(value: String): List<Word> {
        val gson = Gson()
        val type = object : TypeToken<List<Word>>() {}.type
        return gson.fromJson(value, type)
    }
}