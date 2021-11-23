package com.example.multiplethread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallable {

    public static class MyTestCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            return "hello world";
        }
    }

    public static void main(String[] arg){
        MyTestCallable myTestCallable = new MyTestCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(myTestCallable);
        try{
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
