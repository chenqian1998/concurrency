package 线程交互.PC2;

public class PC2 {
    /*
        企业级多线程实战二：
        生产者与消费者问题：（2）两个生产者，两个个消费者，商品池子的大小为1。
                            这个例子主要阐述问题：多线程交互中的，防止虚假唤醒。
                            下面4个线程交互，两个进程要生产，其实还是要遵守商品池子的大小为1的要求，
                            应该是两个线程中的一个生产一个商品，然后要等消费之后才能再生产。
                            但是实际情况却不是这样的，会出现超过一个商品的情况，即商品池中商品生产的数量超过1
                            谁来背这个锅？我们加了synchronized的，每次都是一个进程进去操作的
                            起因是wait(),notifyAll()这个机制,然后是if这个问题
                            比如说如果两个生产者都wait(),然后我们notifyAll(),他们被唤醒，就继续往下运行了，然后count++执行了两次
   */
    public static void main(String[] args) {
        Product p = new Product();
        new Thread(() -> {
            for(int i=0;i<100;i++){
                try {
                    p.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Producer1").start();

        new Thread(() -> {
            for(int i=0;i<100;i++){
                try {
                    p.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Producer2").start();

        new Thread(() -> {
            for(int i=0;i<100;i++){
                try {
                    p.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Comsumer1").start();

        new Thread(() -> {
            for(int i=0;i<100;i++){
                try {
                    p.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Comsumer2").start();

    }
}
