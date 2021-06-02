package com.xpl.customview;

public class DisplayMessage implements Runnable {

    private String message;

    public DisplayMessage(String message) {
        this.message = message;
    }

//    @Override
    public void run() {
        int i=0;
        while(true) {
            i++;
            System.out.println(message +"  "+i);
        }
    }
}
