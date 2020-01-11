package chapter5;

public class ticketWindowRunnable implements Runnable {
    private int index = 1;
    private final static int maxNum = 50;
    private  final Object MONITOR = new Object();

    @Override
    public void run() {
        // synchronized保证每次访问这个对象MONITOR的时候，都是串行访问
        synchronized (MONITOR) {
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
}
