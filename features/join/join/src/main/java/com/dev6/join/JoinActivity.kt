package com.dev6.join
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dev6.core.base.BindingActivity
import com.dev6.core.enums.LoginType
import com.dev6.data.model.JoinEntitiy
import com.dev6.join.databinding.ActivityJoinBinding
import com.dev6.join.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JoinActivity : BindingActivity<ActivityJoinBinding>(R.layout.activity_join) {

    private  val joinViewModel : JoinViewModel by viewModels()
    lateinit var loginMethod : LoginType
    lateinit var uuid : String

    override fun initView() {
        super.initView()



       // loginMethod = intent.getSerializableExtra("loginMethod") as LoginType
        //uuid = intent.getStringExtra("uuid")!!

        val dto  = JoinEntitiy("구글","uuuiiiddd","","",
            "","","","",""
        ,"","" , "")

        joinViewModel.setJoinDTOData(dto)
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