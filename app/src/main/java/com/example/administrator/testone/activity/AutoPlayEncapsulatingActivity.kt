package com.example.administrator.testone.activity

import android.os.Bundle
import com.example.administrator.testone.R
import com.example.administrator.testone.base.BaseActivity
import kotlinx.android.synthetic.main.activity_auto_play_encapsulating.*

/**
 * 展示封装好的轮播图
 */
class AutoPlayEncapsulatingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_play_encapsulating)


    }

    override fun onDestroy() {
        super.onDestroy()
        mAutoPlayView.recycleHandler()
    }
}
