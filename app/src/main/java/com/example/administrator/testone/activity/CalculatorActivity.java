package com.example.administrator.testone.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.testone.util.LL;
import com.example.administrator.testone.R;

/**
 * 主页
 */
public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTv_one;
    private TextView mTv_two;
    private TextView mTv_three;
    private Button mBtn_c;
    private Button mBtn_delete;
    private Button mBtn_fen;
    private Button mBtn_chu;
    private Button mBtn_7;
    private Button mBtn_8;
    private Button mBtn_9;
    private Button mBtn_cheng;
    private Button mBtn_4;
    private Button mBtn_5;
    private Button mBtn_6;
    private Button mBtn_jian;
    private Button mBtn_1;
    private Button mBtn_2;
    private Button mBtn_3;
    private Button mBtn_jia;
    private Button mBtn_tan;
    private Button mBtn_0;
    private Button mBtn_dian;
    private Button mBtn_deng;
    private int type = 3;
    private TextView tv;
    private StringBuilder sbOne = new StringBuilder("0");
    private StringBuilder sbTow = new StringBuilder("0");
    private StringBuilder sb;
    private double DoubleOne;
    private double DoubleTow;
    private boolean flag = true;//是否点击过运算符了
    private String currentFlag;//当前运算符
    private double countDouble;//计算结果
    private boolean countFlag = false;//是否计算过了

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        getSupportActionBar().hide();
        changStatusIconCollor(true);
        mTv_one = findViewById(R.id.tv_one);
        mTv_two = findViewById(R.id.tv_two);
        mTv_three = findViewById(R.id.tv_three);
        mBtn_c = findViewById(R.id.btn_c);
        mBtn_delete = findViewById(R.id.btn_delete);
        mBtn_fen = findViewById(R.id.btn_fen);
        mBtn_chu = findViewById(R.id.btn_chu);
        mBtn_7 = findViewById(R.id.btn_7);
        mBtn_8 = findViewById(R.id.btn_8);
        mBtn_9 = findViewById(R.id.btn_9);
        mBtn_cheng = findViewById(R.id.btn_cheng);
        mBtn_4 = findViewById(R.id.btn_4);
        mBtn_5 = findViewById(R.id.btn_5);
        mBtn_6 = findViewById(R.id.btn_6);
        mBtn_jian = findViewById(R.id.btn_jian);
        mBtn_1 = findViewById(R.id.btn_1);
        mBtn_2 = findViewById(R.id.btn_2);
        mBtn_3 = findViewById(R.id.btn_3);
        mBtn_jia = findViewById(R.id.btn_jia);
        mBtn_tan = findViewById(R.id.btn_tan);
        mBtn_0 = findViewById(R.id.btn_0);
        mBtn_dian = findViewById(R.id.btn_dian);
        mBtn_deng = findViewById(R.id.btn_deng);
        mBtn_0.setOnClickListener(this);
        mBtn_1.setOnClickListener(this);
        mBtn_2.setOnClickListener(this);
        mBtn_3.setOnClickListener(this);
        mBtn_4.setOnClickListener(this);
        mBtn_5.setOnClickListener(this);
        mBtn_6.setOnClickListener(this);
        mBtn_7.setOnClickListener(this);
        mBtn_8.setOnClickListener(this);
        mBtn_9.setOnClickListener(this);
        mBtn_jia.setOnClickListener(this);
        mBtn_jian.setOnClickListener(this);
        mBtn_cheng.setOnClickListener(this);
        mBtn_chu.setOnClickListener(this);
        mBtn_delete.setOnClickListener(this);
        mBtn_deng.setOnClickListener(this);
        mBtn_dian.setOnClickListener(this);
        mBtn_c.setOnClickListener(this);
        mBtn_fen.setOnClickListener(this);
        mBtn_tan.setOnClickListener(this);
        mBtn_c.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                toZero();

                return true;
            }
        });


    }


    private void changStatusIconCollor(boolean setDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (setDark) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }


    @Override
    public void onClick(View v) {
        selectTextView();


        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_dian:
                //如果计算过了则清零
                if (countFlag) {
                    toZero();
                }
                add(v);
                break;
            case R.id.btn_jia:
            case R.id.btn_jian:
            case R.id.btn_cheng:
            case R.id.btn_chu:
            case R.id.btn_fen:
                if (flag) {
                    countMark(v);
                }
                break;
            case R.id.btn_delete:
                if (sb.length() >= 1 && !sb.toString().equals("0")) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                if (sb.length() == 0) {
                    sb.append("0");
                }
                tv.setText(sb.toString());
                break;
            case R.id.btn_deng:
                count();
                countFlag = true;
                break;
            case R.id.btn_c:
                sb.delete(0, sb.length());
                sb.append(0);
                tv.setText("0");
                flag = true;
                break;
            case R.id.btn_tan:

                break;

            default:
                break;


        }
    }



    private void count() {
        DoubleTow = Double.valueOf(sb.toString());



        mTv_one.setText(stringWithZeroAndDaian(String.valueOf(DoubleOne)));
        mTv_two.setText(stringWithZeroAndDaian(String.valueOf(DoubleTow)));


        if (currentFlag.equals("+")) {
            countDouble = DoubleOne + DoubleTow;
        } else if (currentFlag.equals("-")) {
            countDouble = DoubleOne - DoubleTow;
        } else if (currentFlag.equals("*")) {
            countDouble = DoubleOne * DoubleTow;
        } else if (currentFlag.equals("/")) {
            countDouble = DoubleOne / DoubleTow;
        }else if (currentFlag.equals("%")) {
            countDouble = DoubleOne%DoubleTow;
        }
        String countString = String.valueOf(countDouble);
        if (countString.endsWith(".0")) {
            countString = countString.substring(0, countString.length() - 2);
        }

        countString=stringWithZeroAndDaian(countString);



        tv.setText(countString);


    }

    //清零
    private void toZero() {
        type = 3;
        sbOne = new StringBuilder("0");
        sbTow = new StringBuilder("0");
        DoubleOne=0;
        DoubleTow=0;

        selectTextView();
        mTv_one.setText("");
        mTv_two.setText("");
        mTv_three.setText("0");
        countFlag = false;
    }

    //判断结尾是否为.0
    private String stringWithZeroAndDaian(String num) {
        if (num.endsWith(".0")) {
            num = num.substring(0, num.length() - 2);
        }
        return num;
    }


    //运算符
    private void countMark(View v) {
        currentFlag = v.getTag().toString();
        LL.d("当前运算符为:" + currentFlag);

        String one = String.valueOf(sb.charAt(sb.length() - 1));

        //如果第一个算式结尾是.则删掉

        if (one.equals(".")) {
            sb.deleteCharAt(sb.length() - 1);
        }


        mTv_two.setText(currentFlag+sb.toString());
        DoubleOne = Double.parseDouble(sb.toString());

        type=2 ;

        selectTextView();
        String showText=sb.toString();
        showText=stringWithZeroAndDaian(showText);

        tv.setText(showText);

        if (sb.length() >= 1 && !sb.toString().equals("0")) {
            flag = false;
        }

    }

    private void add(View v) {
        LL.d(v.getTag().toString());
        //当第一位是0的时候按0只显示一个0
        if (v.getTag().toString().equals("0") && sb.length() < 1) {
            tv.setText("0");
            return;
        }
        //小数点只可以出现一次
        if (v.getTag().toString().equals(".") && sb.toString().contains(".")) {
            return;
        }


        sb.append(v.getTag());
        //如果第一位是0且第二位不是.的时候则删除0

        String one = String.valueOf(sb.charAt(0));
        String two = String.valueOf(sb.charAt(1));
        if (one.equals("0") && !two.equals(".")) {
            sb.deleteCharAt(0);
        }

        tv.setText(sb.toString());
    }

    private void selectTextView() {
        if (type == 1) {


        } else if (type == 2) {
            sb = sbTow;
        } else if (type == 3) {
            sb = sbOne;
        }
        tv = mTv_three;
    }
}
