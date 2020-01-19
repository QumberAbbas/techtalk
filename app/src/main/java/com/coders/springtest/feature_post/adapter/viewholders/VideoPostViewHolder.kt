package com.coders.springtest.feature_post.adapter.viewholders

import com.coders.springtest.BR
import com.coders.springtest.databinding.ItemVideoPostBinding
import com.coders.springtest.db.entity.Post

class VideoPostViewHolder(binding: ItemVideoPostBinding) : PostViewHolder(binding) {

    override fun bind(post: Post) {
        binding.setVariable(BR.post , post)
        binding.executePendingBindings()
    }
}