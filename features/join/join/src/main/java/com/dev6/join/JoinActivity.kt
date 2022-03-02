package com.dev6.join
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dev6.core.base.BindingActivity
import com.dev6.join.databinding.ActivityJoinBinding
import com.dev6.join.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JoinActivity : BindingActivity<ActivityJoinBinding>(R.layout.activity_join) {

    private  val joinViewModel : JoinViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.apply {
            joinViewModel.joinDTOData
        }
    }

    override fun initViewModel() {
        super.initViewModel()
        lifecycleScope.launch {
            joinViewModel.joinDataFlow.collect(){
                Log.v("joinresponse", it)
            }
        }


    }
}