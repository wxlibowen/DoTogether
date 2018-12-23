package com.example.administrator.testone.activity

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import com.example.administrator.testone.R
import com.example.administrator.testone.base.BaseActivity
import kotlinx.android.synthetic.main.activity_hong_fund.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.image
import java.util.*

class HongFundActivity : BaseActivity() {
    private var i = 0
    private val mList = ArrayList<Int>()
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hong_fund)
                //修改状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this@HongFundActivity,R.color.colorGray)
        }



        mList.add(R.drawable.xiaobo)
        mList.add(R.drawable.bowen)
        mList.add(R.drawable.taishanyading)
        mList.add(R.drawable.tangyue)
        mList.add(R.drawable.mingxing)
        mList.add(R.drawable.chenbin)
        mDaLuanBTN.setOnClickListener {
            if (i>0) return@setOnClickListener
            i = 30
            diyizu.backgroundColor=ContextCompat.getColor(this@HongFundActivity,R.color.colorGray)
            dierzu.backgroundColor=ContextCompat.getColor(this@HongFundActivity,R.color.colorGray)
            disanzu.backgroundColor=ContextCompat.getColor(this@HongFundActivity,R.color.colorGray)
            handler.post(runnable)
        }
    }
    private val runnable = object : Runnable {
        override fun run() {
            mList.shuffle()
            image1.image = ContextCompat.getDrawable(this@HongFundActivity, mList[0])
            image2.image = ContextCompat.getDrawable(this@HongFundActivity, mList[1])
            image3.image = ContextCompat.getDrawable(this@HongFundActivity, mList[2])
            image4.image = ContextCompat.getDrawable(this@HongFundActivity, mList[3])
            image5.image = ContextCompat.getDrawable(this@HongFundActivity, mList[4])
            image6.image = ContextCompat.getDrawable(this@HongFundActivity, mList[5])
            i--
            if (i == 0) {
                diyizu.backgroundColor = ContextCompat.getColor(this@HongFundActivity, R.color.colorWhite)
                dierzu.backgroundColor = ContextCompat.getColor(this@HongFundActivity, R.color.colorWhite)
                disanzu.backgroundColor = ContextCompat.getColor(this@HongFundActivity, R.color.colorWhite)
                return
            }
            when {
                i > 20 -> handler.postDelayed(this, 100)
                i in 10..20 -> handler.postDelayed(this, 200)
                i in 5..10 -> handler.postDelayed(this, 300)
                i < 5->handler.postDelayed(this, 1000)
            }
        }
    }
}
