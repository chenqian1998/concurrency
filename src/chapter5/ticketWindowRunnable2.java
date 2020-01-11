package chapter5;

public class ticketWindowRunnable2 implements Runnable {
    private int index = 1;
    private final static int maxNum = 50;
    private final Object MONITOR = new Object();

    @Override
    public synchronized void run() {
        // index 是要不断读取然后修改，所以加锁至少要全部包含index
        while (index <= maxNum) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //也就是
            System.out.println(Thread.currentThread() + "的号码是：" + (index++));
        }

    }
}
