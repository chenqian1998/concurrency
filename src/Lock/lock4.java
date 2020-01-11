package Lock;

import java.util.concurrent.TimeUnit;

class activity2{
    public static synchronized void playBasketBall() {
        //Thread.sleep(4000);
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("打篮球");
    }

    public static synchronized void playFootball(){
        System.out.println("踢足球");
    }
}

public class lock4 {

    public static void main(String[] args) throws InterruptedException {
        activity2 a = new activity2();

        //情况变成：都是被static synchronized修饰的方法，这哪个先访问
        //注意当方法被static,synchronize同时修饰的话，其实是对类模版加的锁，而不是对每个对象加的锁
        // 因为static是类共有的属性，是Class模版共有的

        new Thread(() -> {
            a.playBasketBall();
        },"A").start();
        Thread.sleep(200);
        new Thread(() -> {
            a.playFootball();
        },"B").start();

//        new Thread(() -> {
//            a.playPingPang();
//        },"name").start();
    }
}
