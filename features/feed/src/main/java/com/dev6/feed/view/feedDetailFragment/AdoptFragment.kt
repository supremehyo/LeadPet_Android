package com.dev6.feed.view.feedDetailFragment


import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev6.core.base.BindingFragment
import com.dev6.feed.R
import com.dev6.feed.adapter.AdoptAdapter
import com.dev6.feed.databinding.FragmentAdoptBinding
import com.dev6.feed.viewmodel.FeedViewModel


class AdoptFragment : BindingFragment<FragmentAdoptBinding>(R.layout.fragment_adopt)  {

    private  val feedViewModel : FeedViewModel by activityViewModels()

    override fun initView() {
        binding.adoptRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AdoptAdapter{

            }
        }
    }

    override fun initViewModel() {
        feedViewModel.getAdoptList()
    }

    override fun initListener() {
        binding.adoptSpinner
    }

}