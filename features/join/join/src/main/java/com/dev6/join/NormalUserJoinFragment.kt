package com.dev6.join

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingFragment
import com.dev6.data.model.JoinEntitiy
import com.dev6.domain.entitiyRepo.JoinEntitiyRepo
import com.dev6.join.databinding.FragmentNormalUserJoinBinding
import com.dev6.join.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException


class NormalUserJoinFragment : BindingFragment<FragmentNormalUserJoinBinding>(R.layout.fragment_normal_user_join) {

    private val PICK_IMAGE_REQUEST = 1
    private  val joinViewModel : JoinViewModel by activityViewModels()
    lateinit var joinDto : JoinEntitiy
    lateinit var userType : String


    override fun initView() {
        super.initView()


        userType = arguments?.get("userType").toString()

        binding.apply {
            profileImageButton.setOnClickListener {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"),
                    PICK_IMAGE_REQUEST
                )
            }
        }


        lifecycleScope.launch {
            joinViewModel.joinDTOData.collectLatest {
                Log.v("testt" , it._loginMethod)
                joinDto = it
            }
        }


    }

    override fun initListener() {
        super.initListener()

        binding.apply {
            profileButton.setOnClickListener {
                if(normalUserNicknameEt.text.toString().isNotEmpty()) {
                    joinViewModel.signUp(makeJoinDto())
                }else if(normalUserNicknameEt.text.toString().isEmpty()){
                    Toast.makeText(context, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
                }else if(normalUserNicknameEt.text.toString().length <2 || normalUserNicknameEt.text.toString().length >16 ){
                    Toast.makeText(context, "닉네임의 길이가 적합하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
             }
        }
    }

    private fun makeJoinDto() : JoinEntitiy{
        var dto : JoinEntitiy = JoinEntitiy(
                joinDto.loginMethod , joinDto.uid,
                "","",
                "",binding.normalUserNicknameEt.text.toString()
                ,"","",
                "",
                "","" , userType)

        return dto
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === PICK_IMAGE_REQUEST && resultCode === Activity.RESULT_OK && data != null && data.data != null) {
            val uri: Uri? = data.data
            try {
                val bitmap: Bitmap =
                    MediaStore.Images.Media.getBitmap(requireActivity()!!.contentResolver, uri)
                val imageView: ImageView = requireActivity()!!.findViewById<View>(R.id.profileImageButton) as ImageView
                Glide.with(this).load(bitmap).circleCrop().into(imageView)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}