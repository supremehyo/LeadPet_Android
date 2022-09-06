package com.dev6.feed.adapter.comment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev6.domain.model.comment.Comment
import com.dev6.domain.model.comment.CommentPage
import com.dev6.feed.databinding.ItemDailycommentBinding


class DailyCommentAdapter(private val callback: (Comment) -> Unit) :
    PagingDataAdapter<Comment, DailyCommentAdapter.RecommendViewHolder>(RecommendDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = ItemDailycommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
        holder.getLayoutParams()
    }

    inner class RecommendViewHolder(private val binding: ItemDailycommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Comment) {
            binding.dailyCommentContentTv.text = item.content
            binding.apply {
                dailyCommentShelterNameTv.text = item.userName
                dailyCommentShelterNameTv.setOnClickListener {
                    callback(item)
                }
            }
        }

        fun getLayoutParams(): ViewGroup.LayoutParams {
            return binding.root.layoutParams
        }
    }

    private class RecommendDiffUtil : DiffUtil.ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment) =
            oldItem.normalReplyId == newItem.normalReplyId

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment) =
            oldItem.normalReplyId == newItem.normalReplyId
    }

}
