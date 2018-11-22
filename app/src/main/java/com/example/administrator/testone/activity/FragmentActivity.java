package com.example.administrator.testone.activity;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import com.example.administrator.testone.R;
import com.example.administrator.testone.base.BaseActivity;
import com.example.administrator.testone.fragment.FragmentOne;
import com.example.administrator.testone.fragment.FragmentTwo;

public class FragmentActivity extends BaseActivity  {

    private Handler handler=new Handler();
    private boolean flag =true;
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (flag){
                flag =false;
               changeFragment();
            }else{
                flag=true;
                changeFragment2();
            }
            handler.postDelayed(runnable,3000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
       FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.home_fragment,new FragmentOne());
        transaction.commit();
//        handler.post(runnable);


    }
    public void changeFragment(){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.home_fragment,new FragmentOne());
        transaction.commit();
    }
    public void changeFragment2(){
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.replace(R.id.home_fragment,new FragmentTwo());
        transaction.commit();
    }
}
