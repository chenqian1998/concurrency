package CAS;

import java.util.concurrent.atomic.AtomicStampedReference;

public class ABASolution {
    static AtomicStampedReference<Integer> value = new AtomicStampedReference<>(5,0);

    public static void main(String[] args) {
        /*
        ABA的解决策略：Java中的
         */

        new Thread(() -> {
            int stamp = value.getStamp();
            System.out.println(Thread.currentThread().getName()+"第一次的版本号："+stamp);
            try{ Thread.sleep(1); }catch (Exception e){ e.printStackTrace(); }
            value.compareAndSet(5,9,value.getStamp(),value.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"第二次的版本号："+value.getStamp());

            value.compareAndSet(9,5,value.getStamp(),value.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"第三次的版本号："+value.getStamp());
        },"t1").start();

        new Thread(() -> {
            int stamp = value.getStamp();
            System.out.println(Thread.currentThread().getName()+"的版本号："+stamp);
            try{ Thread.sleep(3); }catch (Exception e){ e.printStackTrace(); }

            boolean result = value.compareAndSet(5,100,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"修改成功否"+result+",其实最新值是："+value.getReference());
        },"t2").start();
    }
}
