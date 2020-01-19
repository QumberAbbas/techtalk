package com.coders.springtest.feature_post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.coders.springtest.R
import com.coders.springtest.databinding.ItemTopicBinding
import com.coders.springtest.feature_post.adapter.viewholders.TopicViewHolder

class TopicAdapter :
    RecyclerView.Adapter<TopicViewHolder>() {

    private val topics: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val topicItemBinding = DataBindingUtil.inflate<ItemTopicBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_topic, parent, false
        )
        return TopicViewHolder(topicItemBinding)
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {
        holder.bind(topics[position])
    }


    fun updateTopics(newTopics: List<String>) {
        topics.clear()
        topics.addAll(newTopics)
        notifyDataSetChanged()
    }
}
