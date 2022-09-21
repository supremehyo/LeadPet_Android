package com.dev6.login

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.dev6.core.UserData
import com.dev6.core.base.BindingActivity
import com.dev6.core.base.UiState
import com.dev6.core.enums.UserType
import com.dev6.core.util.extension.repeatOnStarted
import com.dev6.domain.model.NormalUserRepo
import com.dev6.domain.model.ProfileUserRepo
import com.dev6.feed.view.FeedActivity
import com.dev6.feed.viewmodel.ProfileViewModel
import com.dev6.join.JoinActivity
import com.dev6.join.viewmodel.JoinViewModel
import com.dev6.login.databinding.ActivityLoginBinding
import com.dev6.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val loginViewModel: LoginViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    override fun initView() {
        super.initView()

        repeatOnStarted {
            loginViewModel.eventFlow.collect { event ->
                handleEvent(event)
            }
        }
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
        repeatOnStarted {
            profileViewModel.eventNormalUserProfileDetail.collect { event ->
                when (event) {
                    is ProfileViewModel.Event.NormalUserProfileUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                var userProfileData = (event.uiState as UiState.Success<NormalUserRepo>).data
                                UserData.intro = userProfileData.intro
                                UserData.userName = userProfileData.userName
                                UserData.email = userProfileData.email
                                UserData.profileImage = userProfileData.profileImage

                                val loginIntent = Intent(this@LoginActivity, FeedActivity::class.java)
                                startActivity(loginIntent)
                                finish()
                            }
                            is UiState.Error -> {
                                Toast.makeText(this@LoginActivity, "실패 했어여", Toast.LENGTH_SHORT).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(this@LoginActivity, "로딩 했어여", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else->{

                    }
                }
            }

        }

        repeatOnStarted {
            profileViewModel.eventProfileDetail.collect { event ->
                when (event) {
                    is ProfileViewModel.Event.ProfileUiEvent -> {
                        when (event.uiState) {
                            is UiState.Success -> {
                                var userProfileData = (event.uiState as UiState.Success<ProfileUserRepo>).data
                                UserData.shelterAccount = userProfileData.shelterAccount
                                UserData.profileImage = userProfileData.profileImage
                                UserData.shelterAddress = userProfileData.shelterAddress
                                UserData.shelterHomepage = userProfileData.shelterHomepage
                                UserData.shelterIntro = userProfileData.shelterIntro
                                UserData.shelterManager = userProfileData.shelterManager
                                UserData.shelterPhoneNumber = userProfileData.shelterPhoneNumber
                                val loginIntent = Intent(this@LoginActivity, FeedActivity::class.java)
                                startActivity(loginIntent)
                                finish()
                            }
                            is UiState.Error -> {
                                Toast.makeText(this@LoginActivity, "실패 했어여", Toast.LENGTH_SHORT).show()
                            }
                            is UiState.Loding -> {
                                Toast.makeText(this@LoginActivity, "로딩 했어여", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }else->{

                    }
                }
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
            UserData.uid = event.loginData.uid.toString()
            UserData.userId = event.loginData.userId
            UserData.profileImage = event.loginData.profileImage
            when(event.loginData.userType){
                UserType.NORMAL->{
                    profileViewModel.getNormalUserProfileDetailData(event.loginData.userId)
                }
                UserType.SHELTER->{
                    profileViewModel.getShelterProfileDetailData(event.loginData.userId)
                }else->{

                }
            }
        }
    }
}
