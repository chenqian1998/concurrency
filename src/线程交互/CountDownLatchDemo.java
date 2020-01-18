package 线程交互;

import com.sun.tools.corba.se.idl.constExpr.Or;

import java.util.concurrent.CountDownLatch;

class Order{
    public  void pushOrder(){
        System.out.println(Thread.currentThread().getName()+":下订单");
    }

    public  void searchLib(){
        System.out.println(Thread.currentThread().getName()+":查询库存");
    }

    public  void confrimOrder(){
        System.out.println(Thread.currentThread().getName()+":确认订单");
    }

}

public class CountDownLatchDemo {
    //背景问题：多线程执行有这样的一种关系，某个线程要在所有相关的线程执行完之后才执行。
    // 比如上面只有前面两个下订单、查询库存（假设这两个没有执行顺序要求）做完，才能做确认订单

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        Order order = new Order();
        for(int i=0; i<3;i++){
            new Thread(() -> {
                order.pushOrder();
                countDownLatch.countDown();
            },"t1").start();
        }

        for(int i=0; i<3;i++){
            new Thread(() -> {
                order.searchLib();
                countDownLatch.countDown();
            },"t2").start();
        }

        try {
            //阻塞main线程，直到值为0，才结束
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        order.confrimOrder();
    }


}
