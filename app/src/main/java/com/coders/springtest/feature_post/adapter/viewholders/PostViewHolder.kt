package com.coders.springtest.feature_post.adapter.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.coders.springtest.db.entity.Post

abstract class PostViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(post: Post)
}