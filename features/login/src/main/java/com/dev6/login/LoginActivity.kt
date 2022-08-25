package com.dev6.login

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.dev6.core.UserData
import com.dev6.core.base.BindingActivity
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.feed.view.FeedActivity
import com.dev6.join.JoinActivity
import com.dev6.login.databinding.ActivityLoginBinding
import com.dev6.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun initView() {
        super.initView()

        repeatOnStarted {
            loginViewModel.eventFlow.collect { event ->
                handleEvent(event)
            }
        }
    }

    fun handleEvent(event: LoginViewModel.Event) = when (event) {
        is LoginViewModel.Event.JoinEvent -> {
            Log.v("asdfsadga" ,"로그인 성공?")
            val joinIntent = Intent(this, JoinActivity::class.java)
            joinIntent.putExtra("loginMethod", event.loginDto.loginMethod)
            joinIntent.putExtra("uuid", event.loginDto.uid)
            joinIntent.putExtra("exist", true)
            startActivity(joinIntent)
            finish()
        }

        is LoginViewModel.Event.ErrorEvent -> {
            val joinIntent = Intent(this, JoinActivity::class.java)
            joinIntent.putExtra("loginMethod", event.loginDto.loginMethod)
            joinIntent.putExtra("uuid", event.loginDto.uid)
            joinIntent.putExtra("exist", false)
            startActivity(joinIntent)
        }

        is LoginViewModel.Event.LoginEvent -> {
            UserData.uid = event.loginDto.uid.toString()
            UserData.loginMethod = event.loginDto.loginMethod
            UserData.password = event.loginDto.password
            UserData.email = event.loginDto.email

            val loginIntent = Intent(this, FeedActivity::class.java)
            startActivity(loginIntent)
        }
    }
}