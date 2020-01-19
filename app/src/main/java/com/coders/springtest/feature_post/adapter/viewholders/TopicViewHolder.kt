package com.coders.springtest.feature_post.adapter.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.coders.springtest.databinding.ItemTopicBinding

class TopicViewHolder(private val binding: ItemTopicBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(topic: String) {
        binding.topic = topic
        binding.executePendingBindings()
    }

}