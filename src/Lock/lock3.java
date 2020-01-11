package Lock;

public class lock3 {
    public static void main(String[] args) throws InterruptedException {
        activity a = new activity();
        activity b = new activity();

        //两个不同的对象，你锁住了另一个对象其实没关系

        new Thread(() -> {
            a.playBasketBall();
        },"A").start();
        Thread.sleep(200);
        new Thread(() -> {
            b.playFootball();
        },"B").start();

//        new Thread(() -> {
//            a.playPingPang();
//        },"name").start();
    }
}
