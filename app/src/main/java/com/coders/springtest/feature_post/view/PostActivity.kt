package com.coders.springtest.feature_post.view

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.coders.springtest.R
import com.coders.springtest.base.activity.BaseActivity
import com.coders.springtest.databinding.ActivityPostBinding
import com.coders.springtest.db.dao.PostDao
import com.coders.springtest.extension.showSubscriptionDialog
import com.coders.springtest.feature_post.adapter.PostAdapter
import com.coders.springtest.feature_post.viewmodel.PostViewModel
import com.example.flatdialoglibrary.dialog.FlatDialog
import dagger.android.AndroidInjection
import javax.inject.Inject


/**
 * @param PostViewModel
 * @param ActivityPostBinding
 * Show all the topics and post to the user
 */
class PostActivity :
    BaseActivity<PostViewModel, ActivityPostBinding>(PostViewModel::class.java) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var postDao: PostDao

    private val adapter: PostAdapter by lazy {
        PostAdapter {
            when (it.isPaid) {
                true -> showSubscriptionRequiredDialog()
                false -> redirectToPostSource(it.sourceUrl)
            }
        }
    }

    override fun getVMFactory(): ViewModelProvider.Factory {
        return viewModelFactory
    }

    /**
     * To inject the dependencies of the calls
     */
    override fun onInject() {
        AndroidInjection.inject(this)
    }

    /**
     * @param viewModel
     * set the data binding variable (view model) and  life cycle owner to
     * observe life data changes for data binding classes
     * observe the live data for article and video posts
     */
    override fun initViewModel(viewModel: PostViewModel) {
        binding.lifecycleOwner = this

        viewModel.postLiveData
            .observe(this, Observer {
                adapter.updatePosts(it)
            })

        viewModel.getPosts()
    }

    /**
     * return layout resource id
     */
    override fun getLayoutRes(): Int {
        return R.layout.activity_post
    }

    /**
     * Redirect user to the source of article/ video
     */
    private fun redirectToPostSource(sourceUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sourceUrl))
        startActivity(intent)
    }

    /**
     * In case the article or video is paid we will show user dialog to subscribe premium membership
     */
    private fun showSubscriptionRequiredDialog() {
        FlatDialog(this).showSubscriptionDialog()
    }
}
