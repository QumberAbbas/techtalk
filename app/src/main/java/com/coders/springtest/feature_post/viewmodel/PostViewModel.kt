package com.coders.springtest.feature_post.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.coders.springtest.base.viewmodel.BaseViewModel
import com.coders.springtest.db.entity.Post
import com.coders.springtest.extension.add
import com.coders.springtest.feature_post.repository.PostRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


/**
 * View model class, will fetch post from the repo and post live data value which
 * is been observed by Post Activity
 *
 * @property repo , will fetch the data for displaying posts.
 */
class PostViewModel @Inject constructor(private val repo: PostRepository) : BaseViewModel() {

    private val disposable = CompositeDisposable()

    val postLiveData: MutableLiveData<List<Post>> = MutableLiveData()
    fun getPosts() {
        repo.getAllPosts().subscribe(postLiveData::postValue) {
            Log.d("PostData", it.toString() + "Error")
        }.add(disposable)
    }

}
