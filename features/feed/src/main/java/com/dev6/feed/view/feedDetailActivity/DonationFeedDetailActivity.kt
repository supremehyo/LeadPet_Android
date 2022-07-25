package com.dev6.feed.view.feedDetailActivity

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.Gravity
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.dev6.core.base.BindingActivity
import com.dev6.domain.entitiyRepo.DonationPostFeed
import com.dev6.feed.R
import com.dev6.feed.databinding.ActivityDonationFeedDetailBinding
import com.skydoves.balloon.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DonationFeedDetailActivity :
    BindingActivity<ActivityDonationFeedDetailBinding>(R.layout.activity_donation_feed_detail) {

    lateinit var currentFeed: DonationPostFeed

    override fun initView() {
        super.initView()
        currentFeed = intent.getSerializableExtra("donationPostFeed") as DonationPostFeed
        makeCurrentView()
        makeBalloun()
    }

    override fun initViewModel() {

    }

    override fun initListener() {

    }

    private fun makeCurrentView() {
        binding.apply {
            currentFeed.apply {
                donationFeedUserId.text = userId
                donationFeedTitle.text = title
                donationFeedContent.text = contents
                donationFeedEndDate.text = endDate[0] + "." + endDate[1]+"."+endDate[2]+"일 까지"
                donationTag.text = "물"
                makeImageView("")
            }
        }
    }

    private fun makeBalloun() {

        val text =
            SpannableString("보호소에서 기부를 확인해드려요!")
        text.setSpan(StyleSpan(Typeface.BOLD), 31, 41, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)


        val balloon = createBalloon(applicationContext) {
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setText(text)
            setTextColorResource(R.color.Gray_01)
            setTextSize(12f)
            setTextGravity(Gravity.START)
            setIconDrawableResource(R.drawable.ic_close_thin)
            setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            setArrowSize(10)
            setArrowPosition(0.5f)
            setPadding(10)
            setCornerRadius(8f)
            setIconGravity(IconGravity.END)
            setIconSpace(5)
            setBackgroundColorResource(R.color.Gray_07)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
            build()
        }
        balloon.showAlignBottom(binding.donationConfirm)

        balloon.setOnBalloonClickListener {
            balloon.dismiss()
        }

    }

    private fun makeImageView(uri: String) {
        Glide.with(this).load(uri).error(R.drawable.alarm).into(binding.dailyContentImage)
    }
}