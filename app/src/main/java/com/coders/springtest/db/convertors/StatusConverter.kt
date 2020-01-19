package com.coders.springtest.db.convertors

import androidx.room.TypeConverter
import com.coders.springtest.enums.PostType


/**
 * Type converter for [PostType] enum
 */
object StatusConverter {

    /**
     * Convert the status int stored in db to [PostType] enum
     */
    @JvmStatic
    @TypeConverter
    fun toStatus(status: Int): PostType {
        return when (status) {
            PostType.ARTICLE.value -> {
                PostType.ARTICLE
            }
            PostType.VIDEO.value -> {
                PostType.VIDEO
            }
            else -> {
                throw IllegalArgumentException("Could not recognize status")
            }
        }
    }

    /**
     * Convert the  [PostType] to Int for storing in db
     */
    @JvmStatic
    @TypeConverter
    fun toInteger(status: PostType): Int {
        return status.value
    }
}