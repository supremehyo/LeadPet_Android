package com.dev6.feed.adapter.comment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev6.domain.model.comment.CommentPage
import com.dev6.feed.databinding.ItemDailycommentBinding


class DailyCommentAdapter(private val callback: (CommentPage) -> Unit) :
    PagingDataAdapter<CommentPage, DailyCommentAdapter.RecommendViewHolder>
        (RecommendDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding =
            ItemDailycommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
        holder.getLayoutParams()
    }

    class RecommendViewHolder(private val binding: ItemDailycommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: CommentPage) {
            Log.v("asdfsdf" , item.userId)
            binding.dailyCommentContentTv.text = item.content
            binding.dailyCommentShelterNameTv.text = item.userName

        }

        fun getLayoutParams(): ViewGroup.LayoutParams {
            return binding.root.layoutParams
        }
    }

    private class RecommendDiffUtil : DiffUtil.ItemCallback<CommentPage>() {
        override fun areItemsTheSame(oldItem: CommentPage, newItem: CommentPage) =
            oldItem.normalReplyId == newItem.normalReplyId

        override fun areContentsTheSame(oldItem: CommentPage, newItem: CommentPage) =
            oldItem.normalReplyId == newItem.normalReplyId
    }

}
