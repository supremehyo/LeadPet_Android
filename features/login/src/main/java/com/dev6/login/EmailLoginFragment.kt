package com.dev6.login

import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.core.enum.LoginType
import com.dev6.login.databinding.FragmentEmailLoginBinding
import com.dev6.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EmailLoginFragment :
    BindingFragment<FragmentEmailLoginBinding>(R.layout.fragment_email_login) {

    private val loginViewModel: LoginViewModel by activityViewModels()
    override fun initListener() {
        super.initListener()

        binding.tvLogin.setOnClickListener {
            loginViewModel.getlogin()

        }

    }

    override fun initView() {
        super.initView()
        with(binding) { viewModel = loginViewModel }
    }
}