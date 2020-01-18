package 线程交互;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    // 信号量
    public static void main(String[] args) {
        // 以前抢占资源，比如说买票，卖掉了我们并不会要求他释放空位，这是一种情况
        // 之前的生产者消费者，其实是一个信号量为1的情况，我们要进行信号量的加减，然后各种通知，加锁，解锁
        // 当信号量的值大于1的时候，就比较麻烦，Java实现了Semaphore（）类，帮助我们
        // 10个厂家去生产东西，但是池子大小只有3
        // 一个生产完，就需要结束
        Semaphore semaphore = new Semaphore(3);
        for(int i=0; i<10;i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"生产");
                    try{ Thread.sleep(1000); }catch (Exception e){ e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName()+"生产结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
