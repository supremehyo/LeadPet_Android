package com.dev6.login

import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingActivity
import com.dev6.login.databinding.ActivityLoginBinding
import com.dev6.login.viewmodel.LoginViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private  val loginViewModel : LoginViewModel by viewModels()

    override fun initView() {
        super.initView()
        with(binding){

        }
    }


}