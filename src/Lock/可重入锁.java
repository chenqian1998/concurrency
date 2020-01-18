package Lock;

import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Order{
    public synchronized void pushOrder(){
        System.out.println(Thread.currentThread().getName()+":下订单");
    }

    public synchronized void searchLib(){
        System.out.println(Thread.currentThread().getName()+":查询库存");
        confrimOrder();
    }

    public synchronized void confrimOrder(){
        System.out.println(Thread.currentThread().getName()+":确认订单");
    }

}

class Order2{
    Lock lock = new ReentrantLock();
    public  void searchLib(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+":查询库存");
            // 其实可重入锁就是保证在这个锁里面访问其他加锁的方法，和之前一样能够访问
            confrimOrder();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public  void confrimOrder(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+":确认订单");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class 可重入锁 {
    public static void main(String[] args) {
        // 可重入锁也叫递归锁
        //public synchronized void method1(){
        //      method2()
        // }
        //
        //public synchronized void method2(){
        //      //.........
        // }
        //
        // 可重入锁的意思是说，method1()是一个加锁的方法，method1()中调用了同样是加锁的方法method2()
        // method1()和method2()是同一把锁，都是method1()加的锁，其实就是外层的锁会传递给内部
        //
        //最大的作用就是避免死锁

        Order order = new Order();
        Order2 order1 = new Order2();

        // 同一个线程访问searchLib()这两个synchronized修饰的方法，这两个加的是一个锁
        new Thread(() -> {
            order1.searchLib();
        },"t1").start();

        new Thread(() -> {
            order1.searchLib();
        },"t2").start();
    }
}
