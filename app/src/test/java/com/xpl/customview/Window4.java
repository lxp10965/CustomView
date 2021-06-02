package com.xpl.customview;

public class Window4 extends Thread {
    private static int ticket = 100;

    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    // 此时的同步锁是当前的类
    private static synchronized void show(){
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "：卖票，票号为" + ticket);
            ticket--;
        }
    }
}
