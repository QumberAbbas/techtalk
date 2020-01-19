package com.coders.springtest.feature_post.adapter.viewholders

import com.coders.springtest.BR
import com.coders.springtest.databinding.ItemArticlePostBinding
import com.coders.springtest.db.entity.Post
import com.coders.springtest.feature_post.adapter.TopicAdapter

class ArticlePostViewHolder(binding: ItemArticlePostBinding) : PostViewHolder(binding) {

    private val adapter: TopicAdapter by lazy {
        TopicAdapter()
    }

    override fun bind(post: Post) {
        binding.setVariable(BR.post , post)
        binding.setVariable(BR.topicsAdapter , adapter)
        post.topics?.let {
            adapter.updateTopics(it)
        }
        binding.executePendingBindings()
    }

}