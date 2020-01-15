package volatileDemo;

import java.util.concurrent.TimeUnit;

class Product{
    // 不加volatile，JMM可见性是实现不了的
    // 通过加上volatile,这个值修改了，会通知其他线程
    private volatile int count = 0;

    public void add100(){
        count+=100;
    }

    public int getCount(){
        return count;
    }
}


public class JMM可见性 {
    public static void main(String[] args) throws InterruptedException {
        Product product = new Product();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始执行");
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e) {
                e.printStackTrace();
            }

            product.add100();
            System.out.println(Thread.currentThread().getName()+",修改之后为："+product.getCount());
        },"name").start();

        while(product.getCount() == 0){

        }
        System.out.println(Thread.currentThread().getName()+product.getCount());
    }
}
