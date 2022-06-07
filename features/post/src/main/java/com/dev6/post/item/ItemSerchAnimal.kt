package com.dev6.post.item

import android.view.View
import com.dev6.post.R
import com.dev6.post.databinding.ItemChoiceAnimalBinding
import com.dev6.post.databinding.ItemChoiceSearchBinding
import com.xwray.groupie.viewbinding.BindableItem
import okhttp3.internal.threadName

class ItemSerchAnimal() : BindableItem<ItemChoiceSearchBinding>() {

    override fun getLayout(): Int = R.layout.item_choice_search

    override fun initializeViewBinding(view: View): ItemChoiceSearchBinding {
        return ItemChoiceSearchBinding.bind(view)
    }

    override fun bind(viewBinding: ItemChoiceSearchBinding, position: Int) {

    }

}