package com.coders.springtest.feature_post.repository

import androidx.annotation.VisibleForTesting
import com.coders.springtest.db.dao.PostDao
import com.coders.springtest.db.entity.Post
import com.coders.springtest.feature_post.service.MockPostService
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.TestOnly
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repo class for retrieving all the posts
 */
@Singleton
class PostRepository @Inject constructor(
    private val postDao: PostDao,
    private val service: MockPostService
) {

    fun getAllPosts(): Observable<List<Post>> {
        return postDao.getAllPosts().flatMapSingle(this::fetchWhenNoneAnd)
    }


    /**
     * In case when no post are in db will fetch from network
     * We can also change this in case network is available we will fetch post every time
     */
    @VisibleForTesting
    fun fetchWhenNoneAnd(posts: List<Post>): Single<List<Post>> {
        return fetchWhenNone(posts).andThen(
            Single.just(
                posts
            )
        )
    }


    /**
     * In case if there are no post in database, will retrieve form the post service
     * and save those post to database
     */
    private fun fetchWhenNone(posts: List<Post>): Completable {
        return if (posts.isEmpty()) {
            fetchPosts()
        } else {
            Completable.complete()
        }
    }

    /**
     * Get all posts from the database
     */
    @VisibleForTesting
    val getPosts: Observable<List<Post>>
        get() = postDao.getAllPosts()


    /**
     * Get post from the service and insert them to the database
     */
    @VisibleForTesting
    fun fetchPosts(): Completable {
        return service.posts
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .doOnSuccess(postDao::insertAll)
            .ignoreElement()
    }
}