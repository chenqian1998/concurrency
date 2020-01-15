package volatileDemo;

import java.util.concurrent.atomic.AtomicInteger;

class Data{
    volatile int count = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

      void addPlusPlus(){
        count++;
    }

    public void addAtomic(){
          atomicInteger.getAndIncrement();
    }

}

public class 原子性 {
    public static void main(String[] args) {
        Data data = new Data();

        for(int i=0; i<20;i++){
            new Thread(() -> {
                for(int j=0; j<1000;j++){
                    data.addPlusPlus();
                    data.addAtomic();
                }
            },String.valueOf(i)).start();
        }


        while(Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"最终数据:" + data.count);
        System.out.println(Thread.currentThread().getName()+"最终数据:" + data.atomicInteger);
    }

}
