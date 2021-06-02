package com.xpl.customview;

import org.junit.Test;

public class TestTread {

    public static void main(String[] args) {
        RunnableDemo runnableDemo1 = new RunnableDemo("runnable--1");
        runnableDemo1.start();
        RunnableDemo runnableDemo2 = new RunnableDemo("runnable--1");
        runnableDemo2.start();
    }
}
