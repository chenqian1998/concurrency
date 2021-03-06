package 线程交互.PC3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Product {
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while(count!=0){
                condition.await();
            }
            count++;
            System.out.println(Thread.currentThread().getName()+"生产！，当前产品数："+count);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try {
            while(count==0){
                condition.await();
            }
            count--;
            System.out.println(Thread.currentThread().getName()+"消费！，当前产品数："+count);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
