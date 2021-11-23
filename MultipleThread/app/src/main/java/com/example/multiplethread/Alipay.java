package com.example.multiplethread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Alipay {
    private double[] accounts;
    private Lock alipayLock;
    private Condition mCondition;

    public Alipay(int n, double money){
        accounts = new double[n];

        alipayLock = new ReentrantLock();
        mCondition = alipayLock.newCondition();   //获得所的条件对象

        for(int i=0; i<accounts.length; i++){
            accounts[i] = money;
        }
    }

    public void transfer(int from, int to, int amount) throws InterruptedException{
        alipayLock.lock();
        try{
            while(accounts[from]<amount){
                mCondition.await();  //阻塞当前线程，并放弃锁
                /*
                一旦一个线程调用await方法，他就会静茹该条件的等待集并处于堵塞状态，直到另一个线程调用了同一个条件的signalAll()
                 */
            }

            accounts[from] = accounts[from]-amount;
            accounts[to] = accounts[to]+amount;
            mCondition.signalAll();  //激活所有线程
        } finally {
            alipayLock.unlock();
        }
    }

    public synchronized void transfer2(int from, int to, int amount) throws InterruptedException{
        while(accounts[from]<amount){
            mCondition.await();  //阻塞当前线程，并放弃锁
                /*
                一旦一个线程调用await方法，他就会静茹该条件的等待集并处于堵塞状态，直到另一个线程调用了同一个条件的signalAll()
                 */
        }

        accounts[from] = accounts[from]-amount;
        accounts[to] = accounts[to]+amount;
        notifyAll(); //激活所有线程
    }
}
