package com.dev6.feed.adapter.Saved
import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.domain.model.save.SavedDailyData
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemSavedDailyBinding

class SavedDailyAdapter(private val callback: (SavedDailyData) -> Unit) :
    PagingDataAdapter<SavedDailyData, SavedDailyAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<SavedDailyData>() {
            override fun areItemsTheSame(
                oldItem: SavedDailyData,
                newItem: SavedDailyData
            ): Boolean {
                return oldItem.normalPostId == newItem.normalPostId
            }

            override fun areContentsTheSame(
                oldItem: SavedDailyData,
                newItem: SavedDailyData
            ): Boolean {
                return oldItem.normalPostId == newItem.normalPostId
            }

        }
    ) {
    override fun onBindViewHolder(holder: SavedDailyAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavedDailyAdapter.ImageViewHolder {
        return ImageViewHolder(
            binding = ItemSavedDailyBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ImageViewHolder(private val binding: ItemSavedDailyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun onBind(item: SavedDailyData) {

            binding.savedDailyCl.setOnClickListener {
                callback(item)
            }
            //binding.savedDailyTitle.text = item.title
            //binding.savedDailyUserId.text = item.userId

            if(item.images!=null && item.images.isNotEmpty()){
                Glide.with(binding.root)
                    .load(Uri.parse(item.images?.get(0)))
                    .error(R.mipmap.img_1)
                    .into(binding.savedDailyIv)
            }else{
                Glide.with(binding.root)
                    .load(Uri.parse( ""))
                    .error(R.mipmap.img_1)
                    .into(binding.savedDailyIv)
            }
        }
    }
}