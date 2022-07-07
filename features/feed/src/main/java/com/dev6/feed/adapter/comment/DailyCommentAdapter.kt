package com.dev6.feed.adapter.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dev6.feed.databinding.ItemDailycommentBinding


class DailyCommentAdapter(private val callback: (String) -> Unit) :
    ListAdapter<String, DailyCommentAdapter.RecommendViewHolder>(RecommendDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding =
            ItemDailycommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.onBind(currentList[position])
        holder.getLayoutParams()
        holder.itemClickListener(currentList[position], callback)
    }

    class RecommendViewHolder(private val binding: ItemDailycommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String) {
            binding.dailyCommentContentTv.text = item
        }

        fun getLayoutParams(): ViewGroup.LayoutParams {
            return binding.root.layoutParams
        }

        fun itemClickListener(item: String, callback: (String) -> Unit) {

        }
    }

    private class RecommendDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }

}
