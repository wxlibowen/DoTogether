package com.example.administrator.testone.activity

import android.os.Bundle
import android.widget.LinearLayout

import android.os.Handler
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.view.ViewGroup
import android.support.v4.content.ContextCompat
import android.support.v4.view.PagerAdapter
import android.view.View
import com.example.administrator.testone.base.BaseActivity

/**
 * 自定义轮播图
 */
class AutoPlayActivity : BaseActivity() {
    private var mViewPager: ViewPager? = null
    /**
     * 图片列表
     */
    private val mList = ArrayList<ImageView>()
    /**
     * 指示器布局
     */
    private var mIndicator: LinearLayout? = null
    /**
     * 指示器当前位置
     */
    private var currentPosition = 0
    /**
     * 指示器上一个位置
     */
    private var prePosition = 0
    /**
     * 图片总数
     */
    private var imageCount = 0
    /**
     * 轮播计时器Handler
     */
    private val carouselHandler = Handler()
    /**
     * 轮播计时器Runnable
     */
    private val carouseRunnable = object : Runnable {
        override fun run() {
            //当前页数+1
            currentPosition++
            //翻页
            mViewPager!!.setCurrentItem(currentPosition)
            //2000毫秒后自动翻页
            carouselHandler.postDelayed(this, 2000)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.administrator.testone.R.layout.activity_auto_play)
        mViewPager = findViewById<ViewPager>(com.example.administrator.testone.R.id.mViewPager)
        mIndicator = findViewById<LinearLayout>(com.example.administrator.testone.R.id.mIndicator)
        //在array.xml中设置需要轮播的图片
        val imageArray = resources.obtainTypedArray(com.example.administrator.testone.R.array.CarouselImage)
        //获取需要轮播的图片总数
        imageCount = imageArray.length()
        val images = IntArray(imageCount)
        //循环遍历将图片放入ViewPager
        for (i in 0 until imageCount) {
            //将图片ID放入数组
            images[i] = imageArray.getResourceId(i, 0)
            val view = ImageView(this)
            view.setImageDrawable(ContextCompat.getDrawable(this, images[i]))
            mList.add(view)
        }
        //资源回收
        imageArray.recycle()
        //为ViewPager添加适配器
        mViewPager!!.adapter = object : PagerAdapter() {
            override fun getCount(): Int {
                return Integer.MAX_VALUE
            }


            override fun isViewFromObject(view: View, o: Any): Boolean {
                //官方建议
                return view === o
            }


            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                var position = position
                //对Viewpager页号求模获得实际位置
                position %= imageCount

                //如果View已经在之前添加到了一个父组件，则必须先remove，否则会闪退。
                val view = mList[position]
                val viewParent = view.parent
                if (viewParent != null) {
                    val parent = viewParent as ViewGroup
                    parent.removeView(view)
                }
                container.addView(mList[position])
                return mList[position]
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                //不要在这里removeView
            }
        }
        //将ViewPager放在中间
        val middle = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageCount
        mViewPager!!.currentItem = middle
        //将当前位置的值设置为中间值
        currentPosition = middle
        //处理指示器
        for (i in 0 until imageCount) {
            val view = ImageView(this)
            //将自定义的背景色设置给指示器做背景
            view.setBackgroundResource(com.example.administrator.testone.R.drawable.icon_select_point)
            //将第一个点设置为当前要展示的颜色其他为默认颜色
            view.isEnabled = i == 0
            //设置指示器小圆点的间距
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(20, 0, 0, 0)
            view.layoutParams = params
            //将小圆点添加到指示器的布局中
            mIndicator!!.addView(view)
        }

        //监听ViewPager的页面变动
        mViewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {

            }

            override fun onPageSelected(position: Int) {
                //当页面变动的时候改变位置的记录
                currentPosition = position
                //将之前改变的指示器小圆点恢复到默认
                mIndicator!!.getChildAt(prePosition).isEnabled = false
                //将指示器的小圆点切换为当前颜色
                mIndicator!!.getChildAt(position % imageCount).isEnabled = true
                //获取图片上一个位置
                prePosition = position % imageCount
            }

            override fun onPageScrollStateChanged(state: Int) {
                //滑动状态下不滚动否则滚动
                if (ViewPager.SCROLL_STATE_DRAGGING == state) {
                    carouselHandler.removeCallbacks(carouseRunnable)
                } else if (ViewPager.SCROLL_STATE_SETTLING == state) {
                    carouselHandler.removeCallbacks(carouseRunnable)
                    carouselHandler.postDelayed(carouseRunnable, 2000)
                }
            }
        })
        //开启滚动展示
        carouselHandler.postDelayed(carouseRunnable, 2000)



    }

    override fun onDestroy() {
        super.onDestroy()
        //清除所有Handler
        carouselHandler.removeCallbacksAndMessages(null)
    }
}
