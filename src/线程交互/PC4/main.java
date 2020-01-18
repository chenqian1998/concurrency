package 线程交互.PC4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class main {
    public static void main(String[] args) {
        // 基于BlockingQueue的产品队列已经
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        PC4 pcdemo = new PC4(blockingQueue);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            pcdemo.produce();
        },"producer").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"消费线程启动");
            pcdemo.consume();
        },"comsumer").start();


        try{ Thread.sleep(5000); }catch (Exception e){ e.printStackTrace(); }

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("生产结束。主线程设置 FLAG= false");
        pcdemo.stop();
    }
}
