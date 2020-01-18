package 线程交互.PC4;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 生产者消费者问题第四种实现：使用BlockingQueue
 */
public class PC4 {
    private boolean FLAG = true;

    // BlockingQueue,其实就是用来存放产品的产品队列
    private BlockingQueue<String> blockingQueue = null;

    // 只要是个程序员，都要明白构造函数里传入接口是非常好的做法
    public PC4(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
    }

    // 我们可以模拟放入的产品是字符串： 手办1，手办2，这种感觉
    // 加volatile是因为可见性，其他线程都要知道当前的id已经改变了
    private volatile AtomicInteger product_id = new AtomicInteger();

//    周所周知，BlockingQueue + Atomic 这种实现方式是不需要加锁的！！！
//    private Lock lock = new ReentrantLock();
//    private Condition condition = lock.newCondition();

    public void produce() {
        // 这些共有的变量放在外面，可以节省很多new的资源
        String product = null;
        boolean res = false;
        while (FLAG) {
            product = "手办" + product_id.getAndIncrement();
            try {
                res = blockingQueue.offer(product, 2L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (res) {
                System.out.println(Thread.currentThread().getName() + "\t 生产了 ：" + product);
            } else {
                System.out.println(Thread.currentThread().getName() + "队列已满，不能生产！");
            }
            try{ Thread.sleep(1000); }catch (Exception e){ e.printStackTrace(); }

        }
        System.out.println("停止生产！");

    }

    public void consume() {
        String poll_product = null;

        while (FLAG) {
            try {
                poll_product = blockingQueue.poll(2L,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (poll_product == null || poll_product.equals("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 两秒中之内未取到商品，退出系统");
                System.out.println();
                System.out.println();
                // 一定要退出
                return ;
            }else {
                System.out.println(Thread.currentThread().getName()+"消费成功，消费："+poll_product);
                try{ Thread.sleep(1000); }catch (Exception e){ e.printStackTrace(); }
            }

        }

    }

    public void stop(){
        this.FLAG = false;
    }
}
