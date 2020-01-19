package com.coders.springtest.diffutil
import androidx.recyclerview.widget.DiffUtil
import com.coders.springtest.db.entity.Post

class PostDiffUtilCallback(private val oldItems: List<Post>, private val newItems: List<Post>) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].postId == newItems[newItemPosition].postId
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}