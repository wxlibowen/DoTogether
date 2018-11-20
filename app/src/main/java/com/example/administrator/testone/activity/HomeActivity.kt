package com.example.administrator.testone.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.example.administrator.testone.R
import com.example.administrator.testone.base.BaseActivity
import io.rong.imkit.RongIM
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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
        btn_calculator.setOnClickListener { GoActivity(CalculatorActivity::class.java) }
        //跳转到测试
        btn_test.setOnClickListener { GoActivity(TestActivity::class.java) }
        btn_phone.setOnClickListener { GoActivity(PhoneNumberActivity::class.java) }
        btn_view_page.setOnClickListener { GoActivity(ViewPageActivity::class.java) }





    }
}
