package com.dev6.login

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.dev6.core.base.BindingFragment
import com.dev6.core.enum.LoginType
import com.dev6.domain.util.repeatOnStarted
import com.dev6.join.JoinActivity
import com.dev6.login.databinding.FragmentEmailLoginBinding
import com.dev6.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext


@AndroidEntryPoint
class EmailLoginFragment : BindingFragment<FragmentEmailLoginBinding>(R.layout.fragment_email_login) {

    private  val loginViewModel : LoginViewModel by activityViewModels()
    override fun initListener() {
        super.initListener()

        binding.button.setOnClickListener {
            loginViewModel.getlogin(LoginType.email)

        }

    }

    override fun initView() {
        super.initView()
        with(binding){
        viewModel = loginViewModel

        }


    }







}