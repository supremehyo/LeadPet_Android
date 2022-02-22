package com.dev6.join
import android.os.Bundle
import com.dev6.core.base.BindingActivity
import com.dev6.join.databinding.ActivityJoinBinding


class JoinActivity : BindingActivity<ActivityJoinBinding>(R.layout.activity_join) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)
    }
}