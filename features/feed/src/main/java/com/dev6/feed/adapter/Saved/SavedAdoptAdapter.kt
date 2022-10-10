package com.dev6.feed.adapter.Saved
import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev6.domain.model.save.SavedAdoptionData
import com.dev6.feed.R
import com.dev6.feed.databinding.ItemSavedAdoptBinding


class SavedAdoptAdapter(private val callback: (SavedAdoptionData) -> Unit) :
    PagingDataAdapter<SavedAdoptionData, SavedAdoptAdapter.ImageViewHolder>(
        object : DiffUtil.ItemCallback<SavedAdoptionData>() {
            override fun areItemsTheSame(
                oldItem: SavedAdoptionData,
                newItem: SavedAdoptionData
            ): Boolean {
                return oldItem.adoptionPostId == newItem.adoptionPostId
            }

            override fun areContentsTheSame(
                oldItem: SavedAdoptionData,
                newItem: SavedAdoptionData
            ): Boolean {
                return oldItem.adoptionPostId == newItem.adoptionPostId
            }

        }
    ) {
    override fun onBindViewHolder(holder: SavedAdoptAdapter.ImageViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SavedAdoptAdapter.ImageViewHolder {
        return ImageViewHolder(
            binding = ItemSavedAdoptBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ImageViewHolder(private val binding: ItemSavedAdoptBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("CheckResult")
        fun onBind(item: SavedAdoptionData) {

            binding.savedAdoptionCl.setOnClickListener {
                callback(item)
            }
            //binding.savedAdoptionTitle.text = item.title
           // binding.savedAdoptionUserId.text = item.userId

            if(item.images!=null && item.images.isNotEmpty()){
                Glide.with(binding.root)
                    .load(Uri.parse(item.images?.get(0)))
                    .error(R.mipmap.img_1)
                    .into(binding.savedAdoptionIv)
            }else{
                Glide.with(binding.root)
                    .load(Uri.parse( ""))
                    .error(R.mipmap.img_1)
                    .into(binding.savedAdoptionIv)
            }
        }
    }
}