package com.example.jia.duanshipin;


import android.os.Handler;

public class CountDownTimer implements Runnable{
    private static ICountDownHander CountDownHander ;
    private int time;
    private int countdowntime;
    private final Handler handler;
    private boolean isrun;
    //实时回调时间  倒计时几秒;观察者设计模式

    //支持动态传入时间

    //每秒减一

    //倒计时为零时 回调状态

    public CountDownTimer(int time,ICountDownHander CountDownHander){
            handler = new Handler() ;
        this.time = time;
        this.countdowntime = time;
        this.CountDownHander = CountDownHander;
    }

    @Override
    public void run() {
        if(isrun){
            if(CountDownHander!=null){
                CountDownHander.onTicker(countdowntime);
            }
            if(countdowntime == 0){
                cancel();
                if(CountDownHander!=null){
                    CountDownHander.OnFinish();
                }
            }
            else {
                countdowntime=time--;
                handler.postDelayed(this,1000);
            }
        }
    }


    public void start(){
        isrun = true;
        handler.post(this);
    }


    public void cancel(){
        isrun = false;
        handler.removeCallbacks(this);
    }
}
