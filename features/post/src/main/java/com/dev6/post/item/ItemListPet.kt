package com.dev6.post.item

import android.view.View
import com.dev6.domain.model.Breed
import com.dev6.post.R
import com.dev6.post.databinding.ItemPetListBinding
import com.xwray.groupie.viewbinding.BindableItem

/**
 * 품종 목록 리스트
 *
 */
class ItemListPet(val breed: Breed, val setOnClickListener: (String) -> Unit) : BindableItem<ItemPetListBinding>() {

    override fun bind(binding: ItemPetListBinding, position: Int) {
        binding.tvName.text = breed.breedName
        binding.root.setOnClickListener {
            setOnClickListener(breed.breedName)
        }
    }

    override fun getLayout(): Int = R.layout.item_pet_list

    override fun initializeViewBinding(view: View): ItemPetListBinding =
        ItemPetListBinding.bind(view)
}
