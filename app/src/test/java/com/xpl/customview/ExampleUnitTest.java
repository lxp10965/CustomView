package com.xpl.customview;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * /**
 *  * 测试Thread中常用的方法
 *  * 1. start():启动当前进程，并且调用当前进程的run()方法
 *  * 2. run()：通常需要重写Thread类中的此方法，将创建的线程要执行的操作声明在这个方法中
 *  * 3. currentThread(): 静态方法，返回当前代码的线程
 *  * 4. getName():获取当前线程的名字
 *  * 5. setName()： 设置当前线程的名字
 *  * 6. yield(): 释放当前cpu的执行权
 *  * 7. join(): 在线程A中调用线程B的join()，此时线程A就进入到了阻塞的状态，直到线程B执行完成，线程A才结束阻塞状态
 *  * 8. sleep(): 让当前的线程睡眠指定的时间
 *  *
 *  * @author MD
 *  * @create 2020-07-10 20:34
 *
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testTread(){

        Window4 w1 = new Window4();
        Window4 w2 = new Window4();
        Window4 w3 = new Window4();

        w1.setName("窗口1");
        w1.start();
        w2.setName("窗口2");
        w2.start();
        w3.setName("窗口3");
        w3.start();

        /**
         * 同步问题
         */
//        Window w = new Window();
//
//        new Thread(w, "窗口1").start();
//        new Thread(w, "窗口2").start();
//        new Thread(w, "窗口3").start();

        /**
         * 线程的优先级等级
         */
//        Method method = new Method();
//        method.setName("线程一：");
//        method.setPriority(Thread.MAX_PRIORITY);
//        method.start();
//
//        // 设置主线程的优先级，最低
//        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
//        // 给主线程命名
//        Thread.currentThread().setName("主线程：");
//        for (int i = 0; i < 100; i++) {
//            if (i % 2 == 0)
//                System.out.println(Thread.currentThread().getName() + ":" + i);
//
//            if (i == 10) {
//                try {
//                    method.join(); //等待当前线程执行完毕
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        //        Thread thread3 = new GuessANumber(27);
//        thread3.start();
//        try {
//            thread3.join();//等待该线程终止
//        }catch(InterruptedException e) {
//            System.out.println("Thread interrupted.");
//        }
//        System.out.println("Starting thread4...");
//        Thread thread4 = new GuessANumber(75);
//        thread4.start();
//        System.out.println("main() is ending...");


//        ThreadDemo t1 = new ThreadDemo("thread-1");
//        t1.start();
//        ThreadDemo t2 = new ThreadDemo("thread-2");
//        t2.start();

    }



    @Test
    public void testRunnable(){
//        RunnableDemo r1=new RunnableDemo("run----1");
//        r1.start();
//        RunnableDemo r2=new RunnableDemo("run----2");
//        r2.start();

        Runnable hello = new DisplayMessage("Hello");
        Thread thread1 = new Thread(hello);
        thread1.setDaemon(false);
        thread1.setName("hello"); //改变线程名称
        System.out.println("Starting hello thread...");
        thread1.start();

        Runnable bye = new DisplayMessage("Goodbye");
        Thread thread2 = new Thread(bye);
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread2.setDaemon(false);
        System.out.println("Starting goodbye thread...");
        thread2.start();
    }


}

