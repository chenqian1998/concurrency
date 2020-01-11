package chapter4;

public class API_join {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run(){
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName()+"==>"+i);
                }
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i=0;i<10;i++){
                    System.out.println(Thread.currentThread().getName()+"==>"+i);
                }
            }
        };

        thread.start();
        thread2.start();

        // 线程（thread,thread2) join 当前的父线程（main)，一旦使用该方法
        // thread,thread2 一定会执行完之后，才执行main线程

        // join(num) 这个num指定这个线程会等num这个时间，如果过了的话，就会执行下面这个for循环
        thread.join(100);
        thread2.join();


        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+"==>"+i);
        }

    }



}
