package com.coders.springtest.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.coders.springtest.R
import com.ohoussein.playpause.PlayPauseView


@BindingAdapter(value = ["mediaUrl"])
fun ImageView.setImageUrlWithPlaceHolder(url: String) {
    Glide.with(context.applicationContext).load(url).placeholder(R.drawable.place_holder_article)
        .into(this)
}

@BindingAdapter(value = ["setListener"])
fun PlayPauseView.setListenerForUnPaidPost(isPaid: Boolean) {
    if (!isPaid) {
        setOnClickListener { toggle() }
    }
}

@BindingAdapter(value = ["adapter"])
fun RecyclerView.bindAdapter(bindAdapter: Adapter<RecyclerView.ViewHolder>) {
    adapter = bindAdapter
}