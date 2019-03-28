package com.example.administrator.testone

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.administrator.testone.activity.*
import com.example.administrator.testone.base.BaseActivity
import com.example.administrator.testone.entity.FunctionEntity
import com.example.administrator.testone.widget.DrawSomeThing
import com.example.firstlabrary.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_page_layout.*

import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeActivity : BaseActivity() {
    private var mList=ArrayList<FunctionEntity>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page_layout)
        initList()
        val mAdapter=ListAdapter(mList,this)
        mListView.adapter=mAdapter
//        mListView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mListView.layoutManager=GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this@HomeActivity, Manifest.permission.READ_CONTACTS)) {
            ActivityCompat.requestPermissions(this@HomeActivity, arrayOf(Manifest.permission.READ_CONTACTS), 3)
        }
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this@HomeActivity, Manifest.permission.CALL_PHONE)) {
            ActivityCompat.requestPermissions(this@HomeActivity, arrayOf(Manifest.permission.CALL_PHONE), 3)
        }

    }
    private fun initList(){
        mList.add(FunctionEntity("计算器",CalculatorActivity::class.java))
        mList.add(FunctionEntity("电话号码",PhoneNumberActivity::class.java))
        mList.add(FunctionEntity("闪屏页",ViewPageActivity::class.java))
        mList.add(FunctionEntity("MultipleStatusActivity",MultipleStatusActivity::class.java))
        mList.add(FunctionEntity("碎片",FragmentActivity::class.java))
        mList.add(FunctionEntity("时钟",DiyView::class.java))
        mList.add(FunctionEntity("弹窗",DialogActivity::class.java))
        mList.add(FunctionEntity("登录",LoginActivity::class.java))
        mList.add(FunctionEntity("坐标",XYzhouActivity::class.java))
        mList.add(FunctionEntity("点赞",DianZanActivity::class.java))
        mList.add(FunctionEntity("自动轮播",AutoPlayActivity::class.java))
        mList.add(FunctionEntity("自动轮播封装",AutoPlayEncapsulatingActivity::class.java))
        mList.add(FunctionEntity("下拉菜单",PopupWindowActivity::class.java))
    }


    override fun onNewIntent(intent: Intent) {
        Log.d(TAG, "onNewIntent: 进来了么1")
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: 进来了么2")
        setIntent(intent)
        val aaa = intent.getBooleanExtra("aaa", false)
        Log.d(TAG, "onNewIntent: $aaa")
        toast("" + aaa)
    }

    class ListAdapter(private  var mList:ArrayList<FunctionEntity>,private var mContext:Context) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListAdapter.ViewHolder {
                val mView=LayoutInflater.from(mContext).inflate(R.layout.item_home_list,null)
                return ViewHolder(mView)
        }

        override fun getItemCount(): Int {
             return   mList.size
        }

        override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
                val data=mList[position]
                holder.mJumpBtn.text=data.buttonName
                holder.mJumpBtn.setOnClickListener {
                    val i=Intent(mContext,data.activityName)
                    mContext.startActivity(i)
                }
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                val mJumpBtn= itemView.findViewById<Button>(R.id.mJumpBtn)!!
        }


    }



}
