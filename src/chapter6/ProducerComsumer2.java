package chapter6;

import java.util.Locale;

public class ProducerComsumer2 {
    //运用了wait()与notify()方法
    //主要需求是：生产者与消费者实例，目前这个例子是，生产一个，消费一个的模式
    private final Object LOCK = new Object();
    private int pNum = 0;
    private int count = 0;

    public  void produce(){
        synchronized (LOCK){
            if(pNum==0){
                pNum+=1;
                count+=1;
                System.out.printf("生产1个商品,当前%s个商品，这是第%s个商品",pNum,count);
                System.out.println();
                LOCK.notify();
            }else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void consume() {
        synchronized (LOCK){
            if(pNum == 1){
                pNum -= 1;
                LOCK.notify();
                System.out.printf("消费一个商品,当前%s个商品",pNum);
                System.out.println();
            }else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        ProducerComsumer2 pc = new ProducerComsumer2();
        Thread t1 = new Thread(){
            @Override
            public void run(){
                while(true)
                    pc.produce();
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run(){
                while(true)
                    pc.consume();
            }
        };

        t1.start();
        t2.start();
    }
}
