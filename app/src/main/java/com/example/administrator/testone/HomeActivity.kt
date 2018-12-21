package com.example.administrator.testone

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.example.administrator.testone.activity.*
import com.example.administrator.testone.base.BaseActivity
import com.example.firstlabrary.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        startActivity<HongFundActivity>()
        jump()
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this@HomeActivity, Manifest.permission.READ_CONTACTS)) {
            ActivityCompat.requestPermissions(this@HomeActivity, arrayOf(Manifest.permission.READ_CONTACTS), 3)
        }
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this@HomeActivity, Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(this@HomeActivity, arrayOf(Manifest.permission.CALL_PHONE), 3)
        }

    }

    private fun jump() {
        //跳转到计算器
        btn_calculator.setOnClickListener { GoActivity(CalculatorActivity::class.java,false) }
        //跳转到测试
        btn_test.setOnClickListener { GoActivity(TestActivity::class.java,false) }
        //电话号码本
        btn_phone.setOnClickListener { startActivity<PhoneNumberActivity>()}
        //闪屏页
        btn_view_page.setOnClickListener { GoActivity(ViewPageActivity::class.java,false) }
        //MultipleStatusView
        btn_multiple.setOnClickListener { GoActivity(MultipleStatusActivity::class.java,false) }
        //fragment
        btn_fragment.setOnClickListener { GoActivity(FragmentActivity::class.java,false) }
        //自定义钟表View
        btn_clock.setOnClickListener { startActivity<DiyView>()}
        //弹窗测试
        btn_dialog.setOnClickListener { startActivity<DialogActivity>()}
        //RecycleView 失败
        btn_recycle_view.setOnClickListener { startActivity<RecycleActivity>()}
        btn_login.setOnClickListener { startActivity<LoginActivity>()}
        btn_my_login.setOnClickListener { startActivity<MyLoginActivity>()}
        //坐标轴
        btn_xy.setOnClickListener { startActivity<XYzhouActivity>()}
        //点赞
        btn_dian.setOnClickListener { startActivity<DianZanActivity>()}





    }

    override fun onNewIntent(intent: Intent) {
        Log.d(TAG, "onNewIntent: 进来了么1")
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: 进来了么2")
        setIntent(intent)
        val aaa = intent.getBooleanExtra("aaa", false)
        Log.d(TAG, "onNewIntent: $aaa")
        toast(""+aaa)


    }





}
