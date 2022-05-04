package com.dev6.post.item

import android.view.View
import com.dev6.post.R
import com.dev6.post.databinding.ItemChoiceAnimalBinding
import com.xwray.groupie.viewbinding.BindableItem
import okhttp3.internal.threadName

class ItemChoiceAnimal(private val nameAnimal: String) : BindableItem<ItemChoiceAnimalBinding>() {

    override fun bind(viewBinding: ItemChoiceAnimalBinding, position: Int) {
        viewBinding.tvNameAnimal.text = nameAnimal
    }

    override fun getLayout(): Int = R.layout.item_choice_animal

    override fun initializeViewBinding(view: View): ItemChoiceAnimalBinding {
        return ItemChoiceAnimalBinding.bind(view)
    }

}