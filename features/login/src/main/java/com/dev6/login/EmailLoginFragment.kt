package com.dev6.login

import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.login.databinding.FragmentEmailLoginBinding
import com.dev6.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EmailLoginFragment : BindingFragment<FragmentEmailLoginBinding>(R.layout.fragment_email_login) {

    private  val loginViewModel : LoginViewModel by activityViewModels()

    override fun initView() {
        super.initView()
        with(binding){
        viewModel = loginViewModel
            loginViewModel.loginDto.value.email = "hoho"

        }
    }



}