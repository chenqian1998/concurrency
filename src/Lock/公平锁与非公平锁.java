package Lock;

import java.util.concurrent.locks.ReentrantLock;

public class 公平锁与非公平锁 {
    public static void main(String[] args) {
        // 开始探讨公平锁与非公平锁。
        // 每个锁都有一个等待队列
        // 公平锁的意思就是：每个线程在获取锁的时候会查看该锁维护的等待队列，加到队列最后，等待执行
        // 非公平锁 ： 上来直接就尝试占有锁，如果失败就转为公平锁的方式

        // ReentrantLock（）是默认的非公平锁，非公平锁优点是吞吐量比公平的大。
        // synchronized 也是一种非公平锁
        ReentrantLock reentrantLock = new ReentrantLock();
    }
}
