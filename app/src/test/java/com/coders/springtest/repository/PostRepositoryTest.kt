package com.coders.springtest.repository

import com.coders.springtest.base.BaseTest
import com.coders.springtest.db.dao.PostDao
import com.coders.springtest.db.entity.Post
import com.coders.springtest.feature_post.repository.PostRepository
import com.coders.springtest.feature_post.service.MockPostService
import io.reactivex.Observable
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*


class PostRepositoryTest : BaseTest() {
    @Mock
    private lateinit var postDao: PostDao

    @Mock
    private lateinit var service: MockPostService

    @Mock
    private lateinit var throwable: Throwable

    @Mock
    private lateinit var post: Post

    private lateinit var repository: PostRepository


    @Before
    fun setUp() {
        repository = PostRepository(postDao, service)
    }

    @Test
    fun verifyGetPostsReturnsDatabaseObservable() {
        val databaseObservable: Observable<List<Post>> = Observable.empty()
        ArrangeBuilder().withObservableFromDatabase(databaseObservable)
        assertThat(repository.getPosts).isEqualTo(databaseObservable)
    }

    @Test
    fun verifyFetchPostsEmitsErrorWhenNetworkServiceErrors() {
        ArrangeBuilder().withErrorInPostsFromService(throwable)
        repository.fetchPosts().test().assertError(throwable)
    }

    @Test
    fun verifyPostsFormServiceAreFetchedIfDbIsEmpty() {
        ArrangeBuilder().withObservableFromDatabase(Observable.just(ArrayList()))
        repository.getAllPosts().test()
        verify(service).posts
    }

    @Test
    fun verifyPostsAreNotFetchedFromServiceIfDbIsNotEmpty() {
        ArrangeBuilder().withObservableFromDatabase(Observable.just(listOf(post)))
        repository.getAllPosts().test()
        verifyNoInteractions(service)
    }

    @Test
    fun verifyPostsAreSavedInDb() {

        ArrangeBuilder().withPostsFromService(listOf(post))
        repository.fetchPosts().subscribe()
        verify(postDao).insertAll(listOf(post))
    }

    private inner class ArrangeBuilder {

        fun withObservableFromDatabase(observable: Observable<List<Post>>): ArrangeBuilder {
            `when`(postDao.getAllPosts()).thenReturn(observable)
            return this
        }

        fun withPostsFromService(posts: List<Post>): ArrangeBuilder {
            `when`(service.posts).thenReturn(Single.just(posts))
            return this
        }

        fun withErrorInPostsFromService(error: Throwable): ArrangeBuilder {
            `when`(service.posts).thenReturn(Single.error(error))
            return this
        }
    }

}