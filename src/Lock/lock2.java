package Lock;

import java.util.concurrent.TimeUnit;

public class lock2 {
    public static void main(String[] args) throws InterruptedException {
        activity a = new activity();
        activity b = new activity();

        //与上次不同，现在访问一个不被synchronized修饰的方法
        //所以普通方法不会竞争资源类i

        new Thread(() -> {
            a.playBasketBall();
        },"A").start();
        Thread.sleep(200);
        new Thread(() -> {
            a.playPingPang();
        },"B").start();

//        new Thread(() -> {
//            a.playPingPang();
//        },"name").start();
    }
}

