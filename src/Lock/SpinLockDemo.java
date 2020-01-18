package Lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
    // 写一个自旋锁
    // 企业级案例：线程A先进来调用myLock()方法持有锁5秒，B随后进来发现当前有线程持有锁，不是null
    // 所以只能通过自旋等待，直到A释放了锁，随后B抢到
    public void CAS(){
        AtomicInteger atomicInteger = new AtomicInteger(10);
        // AtomicInteger 底部就是通过自旋锁实现的
        // 所谓的自旋就是用循环的方式去获取值，如果是期望的值就执行，
        // 这样就不会造成阻塞（也就是用lock的时候，这段代码在执行完之前，只能有一个线程拥有它）
        // 长时间获得不到锁的时候，导致不断的循环判断，导致运行变慢
        atomicInteger.compareAndSet(10,20);
        atomicInteger.getAndAdd(1);
    }

    // 这个是判断的主要条件
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+" come in");
        while(!atomicReference.compareAndSet(null,thread)){

        }

    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+"  out");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            // 线程运行一会
            try{ Thread.sleep(5000); }catch (Exception e){ e.printStackTrace(); }
            // 只有线程A释放了锁，B才能抢到
            spinLockDemo.unLock();
        },"AA").start();

        try{ Thread.sleep(1000); }catch (Exception e){ e.printStackTrace(); }

        new Thread(() -> {
            spinLockDemo.myLock();
            try{ Thread.sleep(5000); }catch (Exception e){ e.printStackTrace(); }
            spinLockDemo.unLock();
        },"BB").start();


    }
}
