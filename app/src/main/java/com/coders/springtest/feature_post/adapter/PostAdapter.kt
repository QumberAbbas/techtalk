package com.coders.springtest.feature_post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.coders.springtest.R
import com.coders.springtest.extension.add
import com.coders.springtest.databinding.ItemArticlePostBinding
import com.coders.springtest.databinding.ItemVideoPostBinding
import com.coders.springtest.db.entity.Post
import com.coders.springtest.diffutil.PostDiffUtilCallback
import com.coders.springtest.enums.PostType
import com.coders.springtest.feature_post.adapter.viewholders.ArticlePostViewHolder
import com.coders.springtest.feature_post.adapter.viewholders.PostViewHolder
import com.coders.springtest.feature_post.adapter.viewholders.VideoPostViewHolder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PostAdapter(private val onItemClick: ((post: Post) -> Unit)?) :
    RecyclerView.Adapter<PostViewHolder>() {

    private val posts: ArrayList<Post> = ArrayList()
    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return when (viewType) {
            PostType.ARTICLE.value -> {
                val articleItemBinding = DataBindingUtil.inflate<ItemArticlePostBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_article_post, parent, false
                )
                ArticlePostViewHolder(articleItemBinding)
            }
            else -> {
                val videoItemBinding = DataBindingUtil.inflate<ItemVideoPostBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_video_post, parent, false
                )
                VideoPostViewHolder(videoItemBinding)
            }
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
        onItemClick?.apply {
            holder.binding.root.setOnClickListener { invoke(posts[position]) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return posts[position].type.value
    }

    fun updatePosts(post: List<Post>) {
        if (post.isEmpty()) {
            updateAllPosts(post)
        } else {
            updateDiffPostsOnly(post)
        }
    }

    private fun updateAllPosts(posts: List<Post>) {
        Single.just<List<Post>>(posts)
            .doOnSuccess { items: List<Post> ->
                updateItemsInModel(
                    items
                )
            }
            .subscribe { _ -> notifyDataSetChanged() }
            .add(compositeDisposable)
    }

    private fun updateDiffPostsOnly(items: List<Post>) {
        Single.fromCallable<DiffUtil.DiffResult> { calculateDiff(items) }
            .subscribeOn(Schedulers.computation())
            .doOnSuccess { updateItemsInModel(items) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result: DiffUtil.DiffResult ->
                updateAdapterWithDiffResult(result)
            }.add(compositeDisposable)
    }

    private fun calculateDiff(newPosts: List<Post>): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(PostDiffUtilCallback(posts, newPosts))
    }

    private fun updateItemsInModel(items: List<Post>) {
        posts.clear()
        posts.addAll(items)
    }

    private fun updateAdapterWithDiffResult(result: DiffUtil.DiffResult) {
        result.dispatchUpdatesTo(this)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        compositeDisposable.clear()
        super.onDetachedFromRecyclerView(recyclerView)

    }
}
