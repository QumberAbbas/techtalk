package com.coders.springtest.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.coders.springtest.db.entity.Post
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface PostDao {

    @Query("SELECT * FROM post")
    fun getAllPosts(): Observable<List<Post>>

    @Insert(onConflict = REPLACE)
    fun insertAll(list: List<Post>)

    @Insert(onConflict = REPLACE)
    fun insert(post: Post)
}