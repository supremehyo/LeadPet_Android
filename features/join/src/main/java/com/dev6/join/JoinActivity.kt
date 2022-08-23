package com.dev6.join
import android.content.Intent
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.dev6.core.UserData
import com.dev6.core.base.BindingActivity
import com.dev6.core.enums.LoginType
import com.dev6.feed.view.FeedActivity
import com.dev6.join.databinding.ActivityJoinBinding
import com.dev6.join.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedimagepicker.util.ToastUtil.context

@AndroidEntryPoint
class JoinActivity : BindingActivity<ActivityJoinBinding>(R.layout.activity_join) {

    private  val joinViewModel : JoinViewModel by viewModels()
    lateinit var loginMethod : LoginType
    lateinit var uuid : String
    var exist : Boolean = false
    lateinit var navController  : NavController
    lateinit var navHostFragment : NavHostFragment
    override fun initView() {
        super.initView()

        loginMethod = intent.getSerializableExtra("loginMethod") as LoginType
        uuid = intent.getStringExtra("uuid")!!
        exist = intent.getBooleanExtra("exist" , false)

        UserData.uid = uuid
        UserData.loginMethod = loginMethod


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.join_nav_host) as NavHostFragment
        navController = navHostFragment.navController


    }

    override fun initViewModel() {
        super.initViewModel()
    }

    override fun afterOnCreate() {
        super.afterOnCreate()
        setNavGraph(exist)
    }

    private fun setNavGraph(isAlreadyLogin: Boolean) {
        val navGraph = navController.navInflater.inflate(R.navigation.join_nav_graph)
        if (isAlreadyLogin) {
            val feedIntent = Intent(context, FeedActivity::class.java)
            feedIntent.putExtra("userType", "")
            startActivity(feedIntent)
        } else navGraph.setStartDestination(R.id.joinTypeFragment)
        navController.setGraph(navGraph, null) //navController에 graph 설정
    }
}