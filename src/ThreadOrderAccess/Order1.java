package ThreadOrderAccess;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Order1 {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int flag = 1; // 1：下订单 2：库存操作 3：确认订单

    public void pushOrder() {
        lock.lock();
        try {
            //  只有flag是1，才进行操作，否则等待
            while (flag != 1) {
                condition1.await();
            }
            System.out.println("下订单！");
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void searchLab(){
        lock.lock();
        try{
            while(flag!=2){
                condition2.await();
            }
            System.out.println("库存操作...");
            flag =3 ;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    public void confirmOrder(){
        lock.lock();
        try{
            while(flag!=3){
                condition3.await();
            }
            System.out.println("确认订单");
            flag = 1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
