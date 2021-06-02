package com.xpl.customview;

public class Window implements Runnable{

    private int ticket = 100;
    @Override
    public void run() {
        while (true){
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            if (ticket > 0){
                System.out.println(Thread.currentThread().getName() +"：卖票，票号为" + ticket);
                ticket--;
            }else {
                break;
            }
        }
    }
}
