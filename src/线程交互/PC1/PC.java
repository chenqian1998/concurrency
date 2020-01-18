package 线程交互.PC1;

public class PC {
    /*
        企业级多线程实战二：
        生产者与消费者问题：这个和前面多个线程并发去访问资源这种场景是不一样的，买票的场景中，
                        其实资源是固定的，多个线程去强访问资源。
                        而PC问题，强调的是交互问题，他的场景是这样，生产者生产了一个，只有被消费者消费完一个，他才会继续生产。
                        所以不同线程之间的交互要用到wait(),notifyAll()机制
        （1）一个生产者，一个消费者，商品池子的大小为1。这个最简单，主要用wait(),notifyAll()这些方法调度
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
                        p.decrement();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        },"Comsumer1").start();

    }
}
