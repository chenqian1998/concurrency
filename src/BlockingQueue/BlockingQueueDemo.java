package BlockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    /*
    阻塞队列到底是干什么的？？？？
    经典理解：把资源放在这个队列里，对资源进行控制。阻塞队列为空，试图获取的线程就阻塞，队列满的时候试图去添加的话就会被阻塞
     这不就和生产者、消费者很类似了吗，都是有一个确定大小的产品产品队列，满了不能加，空的不能取值。

    以前这种阻塞队列、唤醒队列的操作，都是需要用wait(),notify()【或者condition.await(), condition.signal()】
     PC问题中，产品队列为1的时候还是比较简单的，问题是多个的时候，用上述问题操作就比较复杂了。

     所以阻塞队列最大的好处就是：可以方便控制什么时候阻塞线程，什么时候唤醒线程。
     */

    public static void main(String[] args) throws InterruptedException {

        // 这个就和ArrayList
        // add（），remove（）,element（）这些方法会抛出异常
        List list = new ArrayList();
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("chen"));
        System.out.println(blockingQueue.add("qian"));
        System.out.println(blockingQueue.add("niu"));
        System.out.println(blockingQueue.remove("chen"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue);


        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue(3);
        blockingQueue2.offer("真实");
        blockingQueue2.offer("伤害");
        blockingQueue2.offer("akl");
        System.out.println(blockingQueue2.offer("!"));
        System.out.println(blockingQueue2);
        System.out.println(blockingQueue2.peek());
        System.out.println(blockingQueue2);
        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2);
        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll());

        BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<String>(3);
        blockingQueue3.put("aaa");
        blockingQueue3.put("bbb");
        blockingQueue3.put("ccc");
        System.out.println("===========");
        // 队列已经满了，再往里面加会阻塞住，直到队列不满
        //blockingQueue3.put("ddd");
        System.out.println(blockingQueue3);
        System.out.println(blockingQueue3.take());
        System.out.println(blockingQueue3);

        // 最好的其实是这种形式
        BlockingQueue<String> blockingQueue4 = new ArrayBlockingQueue<>(3);
        blockingQueue4.offer("aaa",2, TimeUnit.SECONDS);
        blockingQueue4.offer("bbb",2L,TimeUnit.SECONDS);
        blockingQueue4.offer("ccc",2L,TimeUnit.SECONDS);
        System.out.println(blockingQueue4);
        // 这个时候队列已经满了，再向里面添加元素,会等待两秒，如果还是满的，返回false，但是不会报异常。
        System.out.println(blockingQueue4.offer("ccc", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue4.poll());
    }
}
