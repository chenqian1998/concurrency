package 线程交互.PC2;

public class Product {
    private int count = 0;

    public  synchronized void increment() throws InterruptedException{
        if (count!=0){
            // 可能有两个Producer线程因为count!=0而堵塞在这里
            // 如果是if的话，notifyAll()方法唤醒之后就直接往下运行了，那么这个count++其实就是运行了两次
            // 如果用while就不会出现这个问题，因为每次wait()唤醒之后，都会检查一边count!=0
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
        //这个notifyAll()方法会唤醒前面两个可能都堵塞(wait())住的生产者
        this.notifyAll();

    }
}
