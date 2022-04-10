package com.dev6.feed.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemDailyshelterBinding

class DailyshelterRecyclerAdapter(private val callback : (String) -> Unit)
    : ListAdapter<String, DailyshelterRecyclerAdapter.RecommendViewHolder>(RecommendDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = ItemDailyshelterBinding.
        inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.onBind(currentList[position])
        holder.getLayoutParams()
        holder.itemClickListener(currentList[position] , callback)
    }

    class RecommendViewHolder(private val binding: ItemDailyshelterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String) {
            Glide.with(binding.root)
                .load(Uri.parse("http://newsimg.hankookilbo.com/2017/11/06/201711060912547465_1.jpg"))
                .circleCrop()
                .error(R.drawable.dailay_image1)
                .into(binding.shelterIv)
        }

        fun getLayoutParams(): ViewGroup.LayoutParams {
            return binding.root.layoutParams
        }

        fun itemClickListener(item: String , callback: (String) -> Unit) {
            binding.shelterIv.setOnClickListener {
                callback.invoke(item)
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