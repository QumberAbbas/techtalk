package com.coders.springtest.db.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

/**
 * Type converter for [List]
 */
object ListConverter {

    /**
     * Convert the json stored in db to list of type [String]
     */
    @JvmStatic
    @TypeConverter
    fun fromString(value: String?): List<String> {
        if (value.isNullOrEmpty()) {
            return Collections.emptyList();
        }
        val listType: Type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)

    }

    /**
     * Convert the list of type [String] to json format to stored in
     */
    @JvmStatic
    @TypeConverter
    fun fromArrayList(list: List<String>?): String {
        if (list.isNullOrEmpty()) {
            return ""
        }
        return Gson().toJson(list)
    }
}