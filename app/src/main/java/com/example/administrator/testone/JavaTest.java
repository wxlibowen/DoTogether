package com.example.administrator.testone;

import android.util.SparseArray;

import java.util.HashMap;

public class JavaTest {
    public static void main(String[] args) {



        HashMap<String,String> map=new HashMap<>(3);
        map.put("1","有趣");
        map.put("3","厉害");
        map.put("什么","非常棒");
        for(Object o:map.keySet()){
            ppr(o+map.get(o));
        }


    }


    private static void ppr(Object msg){
        System.out.println(msg);
    }
}
