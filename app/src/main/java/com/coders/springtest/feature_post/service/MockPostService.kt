package com.coders.springtest.feature_post.service

import android.content.Context
import com.coders.springtest.R
import com.coders.springtest.db.entity.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import javax.inject.Inject

/**
 * Post service class, this will read the posts from the post.json fie and return list of those posts
 */
class MockPostService @Inject constructor(private val gson: Gson, private val context: Context) {
    val posts: Single<List<Post>>
        get() {
            return try {
                val listType = object :
                    TypeToken<ArrayList<Post>>() {}.type
                Single.just(
                    gson.fromJson(
                        InputStreamReader(
                            context.resources.openRawResource(R.raw.posts),
                            "UTF-8"
                        ),
                        listType
                    )
                )
            } catch (e: UnsupportedEncodingException) {
                throw IllegalArgumentException("$TAG: Error parsing from file posts.json")
            }
        }


    companion object {
        private val TAG = MockPostService::class.java.simpleName
    }

}
