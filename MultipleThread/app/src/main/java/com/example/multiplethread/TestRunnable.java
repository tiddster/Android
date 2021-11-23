package com.example.multiplethread;

public class TestRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("hello,world");
    }

    public static void main(String[] arg){
        TestRunnable testRunnable = new TestRunnable();
        Thread mThread = new Thread(testRunnable);
        mThread.start();
    }
}
