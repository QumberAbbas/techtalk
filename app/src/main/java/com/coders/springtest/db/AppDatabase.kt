package com.coders.springtest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.coders.springtest.db.convertors.ListConverter
import com.coders.springtest.db.convertors.StatusConverter
import com.coders.springtest.db.dao.PostDao
import com.coders.springtest.db.entity.Post


@Database(entities = [Post::class], version = 1, exportSchema = false)
@TypeConverters(value = [ListConverter::class, StatusConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}