package ThreadOrderAccess;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Order {
    private int flag = 1;// 标志位：1：下订单 2、库存查询 3、确认订单
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    public void pushOrder() {
        lock.lock();
        try {
            while (flag != 1) {
                condition1.await();
            }
            System.out.println("顾客下订单了！");
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void checkLab() {
        lock.lock();
        try {
            while(flag!=2){
                condition2.await();
            }
            System.out.println("查询仓库余量");
            flag = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void confirmOrder(){
        lock.lock();
        try {
            while (flag!=3){
                condition3.await();
            }
            System.out.println("订单已确认！");
            flag = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
