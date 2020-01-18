package 线程交互.PC1;

public class Product {
    private int count = 0;

    public  synchronized void increment() throws InterruptedException{
        if (count!=0){
            this.wait();
        }
        this.count++;
        System.out.println(Thread.currentThread().getName()+" 生产，产品数："+count);
        this.notifyAll();
    }

    public  synchronized void decrement() throws InterruptedException{
        if(count<1){
            this.wait();
        }
        this.count--;
        System.out.println(Thread.currentThread().getName()+" 消费，产品数："+count);
        this.notifyAll();
    }
}
