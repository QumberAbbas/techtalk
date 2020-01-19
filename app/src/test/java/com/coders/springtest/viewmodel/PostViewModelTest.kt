package com.coders.springtest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.coders.springtest.base.BaseTest
import com.coders.springtest.db.entity.Post
import com.coders.springtest.feature_post.repository.PostRepository
import com.coders.springtest.feature_post.viewmodel.PostViewModel
import io.reactivex.subjects.BehaviorSubject
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class PostViewModelTest : BaseTest() {

    @Rule
    @JvmField
    var archComponentsRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var post: Post

    @Mock
    private lateinit var repository: PostRepository

    private lateinit var viewModel: PostViewModel

    private lateinit var arrangeBuilder: ArrangeBuilder

    @Rule
    @JvmField
    var otherRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        arrangeBuilder = ArrangeBuilder()
        viewModel = PostViewModel(repository)
    }

    @Test
    fun displayableItemsGoIntoLiveDataWhenPostsAreEmitted() {
        val posts: List<Post> = listOf(post)
        viewModel.postLiveData.observeForever { }
        viewModel.getPosts()
        arrangeBuilder.repositoryEmitsPostItems(posts)
        assertThat(viewModel.postLiveData.value).isEqualTo(posts)
    }

    private inner class ArrangeBuilder {
        var observable: BehaviorSubject<List<Post>> = BehaviorSubject.create()

        init {
            Mockito.`when`(repository.getAllPosts())
                .thenReturn(observable)
        }

        fun repositoryEmitsPostItems(posts: List<Post>): ArrangeBuilder {
            observable.onNext(posts)
            return this
        }
    }
}