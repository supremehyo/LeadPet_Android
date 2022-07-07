package com.dev6.feed.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemDailyfeedBinding
import com.dev6.feed.databinding.ItemDailyshelterBinding
import com.dev6.feed.databinding.ItemRecommendFeedBinding

class RecommendFeedAdapter (private val callback : (String) -> Unit)
    : ListAdapter<String, RecommendFeedAdapter.RecommendViewHolder>(RecommendDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = ItemRecommendFeedBinding.
        inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.onBind(currentList[position])
        holder.getLayoutParams()
        holder.itemClickListener(currentList[position] , callback)
    }

    class RecommendViewHolder(private val binding: ItemRecommendFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String) {

        }

        fun getLayoutParams(): ViewGroup.LayoutParams {
            return binding.root.layoutParams
        }

        fun itemClickListener(item: String , callback: (String) -> Unit) {
            binding.recommendItemCl.setOnClickListener {
                callback("")
            }
        }
    }

    private class RecommendDiffUtil : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }

}