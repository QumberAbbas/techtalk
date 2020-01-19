package com.coders.springtest.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.coders.springtest.enums.PostType

@Entity(primaryKeys = ["postId"])
data class Post(

    @ColumnInfo(name = "postId")
    val postId: Long,

    @ColumnInfo(name = "media_url")
    val mediaUrl: String,

    @ColumnInfo(name = "headline")
    val headline: String,

    @ColumnInfo(name = "type")
    val type: PostType,

    @ColumnInfo(name = "source_url")
    val sourceUrl: String,

    @ColumnInfo(name = "isPaid")
    val isPaid: Boolean,

    @ColumnInfo(name = "duration")
    val duration: Long = 0,

    @ColumnInfo(name = "topics")
    val topics: List<String>? = null,

    @ColumnInfo(name = "description")
    val description: String? = null
)