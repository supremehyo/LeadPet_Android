package com.dev6.feed.adapter

import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.core.util.extension.fewDay
import com.dev6.domain.model.daily.DailyPost
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemDailyfeedBinding


class DailyPagingAdapter(private val callback: (DailyPost) -> Unit) :
    PagingDataAdapter<DailyPost, DailyPagingAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<DailyPost>() {
            override fun areItemsTheSame(oldItem: DailyPost, newItem: DailyPost): Boolean {
                return oldItem.normalPostId == newItem.normalPostId
            }

            override fun areContentsTheSame(
                oldItem: DailyPost,
                newItem: DailyPost
            ): Boolean {
                return oldItem.normalPostId == newItem.normalPostId
            }

        }
    ) {
    override fun onBindViewHolder(holder: DailyPagingAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyPagingAdapter.ImageViewHolder {
        return ImageViewHolder(
            binding = ItemDailyfeedBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ImageViewHolder(private val binding: ItemDailyfeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: DailyPost) {
            binding.apply {
                dailyFeedContentTv.text = item.contents
                dailyFeedTitleTv.text = item.title
                dailyFeedProfileTv.text = item.userName
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    dailyFeedWriteTimeTv.text = fewDay(
                        item.createdDate[0],
                        item.createdDate[1],
                        item.createdDate[2],
                        item.createdDate[3],
                        item.createdDate[4],
                        item.createdDate[5]
                    )
                }
                dailyCommentCount.text = item.commentCount.toString()
                dailyLikeCount.text = item.likedCount.toString()
                makeLikedHeart(item.liked)
                if(item.images.isEmpty()){
                    Glide.with(binding.root)
                        .load( "")
                        .error(R.mipmap.img_2)
                        .circleCrop()
                        .into(dailyFeedIv)
                }else{
                    Glide.with(binding.root)
                        .load( item.images[0])
                        .error(R.mipmap.img_2)
                        .circleCrop()
                        .into(dailyFeedIv)
                }


                Glide.with(binding.root)
                    .load(item.profileImage)
                    .error(R.mipmap.img_1)
                    .circleCrop()
                    .into(dailyFeedProfileIv)

                constraintLayout2.setOnClickListener {
                    callback(item)
                }

            }
        }

        private fun makeLikedHeart(boolean: Boolean){
            if (boolean) {
                binding.dailyLikeImage.setColorFilter(Color.parseColor("#FF675E"))
            } else {
                binding.dailyLikeImage.setColorFilter(Color.parseColor("#C4C4C4"))
            }
        }
    }
}
