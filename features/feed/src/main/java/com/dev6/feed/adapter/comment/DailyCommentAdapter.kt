package com.dev6.feed.adapter.comment

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev6.core.util.extension.fewDay
import com.dev6.domain.model.comment.Comment
import com.dev6.domain.model.comment.CommentPage
import com.dev6.feed.databinding.ItemDailycommentBinding


class DailyCommentAdapter(private val callback: (Comment) -> Unit) :
    PagingDataAdapter<Comment, DailyCommentAdapter.RecommendViewHolder>(RecommendDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = ItemDailycommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
        holder.getLayoutParams()
    }

    inner class RecommendViewHolder(private val binding: ItemDailycommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun onBind(item: Comment) {
            binding.dailyCommentContentTv.text = item.content
            binding.apply {
                dailyCommentShelterNameTv.text = item.userName
                dailyCommentTimeTv.text = fewDay(
                    item.createdDate[0],
                    item.createdDate[1],
                    item.createdDate[2],
                    item.createdDate[3],
                    item.createdDate[4],
                    item.createdDate[5]
                )
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
            oldItem.normalReplyId == newItem.normalReplyId  && oldItem.createdDate == newItem.createdDate

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment) =
            oldItem.normalReplyId == newItem.normalReplyId && oldItem.createdDate == newItem.createdDate
    }

}
