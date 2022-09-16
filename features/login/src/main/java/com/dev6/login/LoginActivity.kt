package com.dev6.login

import android.content.Intent
import android.util.Log
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
            Log.v("asdfsadga", "로그인 성공?")
            val joinIntent = Intent(this, JoinActivity::class.java)
            joinIntent.putExtra("loginMethod", event.loginData.loginMethod)
            joinIntent.putExtra("uuid", event.loginData.uid)
            joinIntent.putExtra("userType", event.loginData.userType)
            joinIntent.putExtra("exist", true)
            startActivity(joinIntent)
            finish()
        }

        is LoginViewModel.Event.ErrorEvent -> {
            val joinIntent = Intent(this, JoinActivity::class.java)
            joinIntent.putExtra("loginMethod", event.loginData.loginMethod)
            joinIntent.putExtra("uuid", event.loginData.uid)
            joinIntent.putExtra("userType", event.loginData.userType)
            joinIntent.putExtra("exist", false)
            startActivity(joinIntent)
        }

        is LoginViewModel.Event.LoginEvent -> {
            //     UserData.userId
            UserData.uid = event.loginData.uid.toString()
            UserData.userId = event.loginData.userId
            UserData.profileImage = event.loginData.profileImage
            UserData.userType = event.loginData.userType.name
            UserData.loginMethod = event.loginData.loginMethod
            UserData.email = event.loginData.email

            val loginIntent = Intent(this, FeedActivity::class.java)
            startActivity(loginIntent)
            finish()
        }
    }
}
