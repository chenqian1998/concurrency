package 线程交互;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    // 可循环使用（Cyclic）的屏障（Barrier)

    public static void main(String[] args) {
        Order order = new Order();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2,()->{order.confrimOrder();});

        new Thread(() -> {
            order.pushOrder();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() -> {
            order.searchLib();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
