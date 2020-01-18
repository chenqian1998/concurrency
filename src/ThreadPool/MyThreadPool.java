package ThreadPool;

import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPool {
    public static void main(String[] args) {
        // 这里是讲线程池API，利用Java提供的方法创建一个线程池
        // java中的线程池主要通过Executor()这个框架实现的
        // ExecutorService是一个接口，他的父接口是Executor(就是执行提交的Runnable接口），
        // 他的一个实现是：ThreadPoolExecutor(),这其实就是我们最常用的类，就是所说的线程池

        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        // 创建一个只有一个线程的线程池
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        // 创建有N个线程的线程池，这个N的大小会随着业务访问的频率来自己改变
        ExecutorService threadPool3 = Executors.newCachedThreadPool();

        try{
            for(int i=1; i<=100;i++){
                threadPool3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+":办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 线程池一定要关闭
            threadPool3.shutdown();
        }
    }
}
