package Lock;

import java.util.concurrent.TimeUnit;

class activity{
    public synchronized void playBasketBall() {
        //Thread.sleep(4000);
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("打篮球");
    }

    public synchronized void playFootball(){
        System.out.println("踢足球");
    }

    public void playPingPang(){
        System.out.println("打乒乓球啊！");
    }
}


public class lock1 {
    public static void main(String[] args) throws InterruptedException {
        activity a = new activity();
        activity b = new activity();

        // 同一个对象，访问他的两个synchronized方法
        //这个其实就关系到synchronized的本质了，本质是一种加锁处理，
        //线程访问一个synchronized修饰的方法，就给当前对象加锁，直到它运行结束，
        // 其他线程都不能进入当前对象的所有synchronized方法，应为这个对象已经被锁住了

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
