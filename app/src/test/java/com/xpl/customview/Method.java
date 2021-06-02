package com.xpl.customview;

public class Method extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                System.out.println(Thread.currentThread().getName() + ":" + i);

            if (i % 4 == 0)
                yield();  //释放当前cpu的执行权

        }
    }
}
