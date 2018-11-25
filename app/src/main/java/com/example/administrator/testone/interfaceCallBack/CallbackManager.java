package com.example.administrator.testone.interfaceCallBack;

public class CallbackManager {
    private MyCallback myCallback;

    public CallbackManager(MyCallback myCallback ) {
        this.myCallback = myCallback;
    }
    public void doS(){
        myCallback.doSomthing();
    }
}
