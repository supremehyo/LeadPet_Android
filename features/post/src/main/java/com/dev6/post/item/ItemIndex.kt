package com.dev6.post.item

import android.view.View
import com.dev6.post.R
import com.dev6.post.databinding.ItemIndexBinding
import com.xwray.groupie.viewbinding.BindableItem

class ItemIndex(val index: String) : BindableItem<ItemIndexBinding>() {

    override fun getLayout(): Int = R.layout.item_index

    override fun initializeViewBinding(view: View): ItemIndexBinding = ItemIndexBinding.bind(view)

    override fun bind(viewBinding: ItemIndexBinding, position: Int) {
        viewBinding.tvIndex.text = index
    }


}