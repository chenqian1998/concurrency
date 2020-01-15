package conllectionSafety;


import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayListDemo {
    public static  void test(){
        //为什么ArrayList会在多线程中出现问题，list.add()写入出错是关键
        //上一个线程在往list中写入数据的时候，另外一个线程抢占了，但是它还没有写完
        // 所以会出现ConcurrentModificationException(并发修改异常）

        List<String> list = new ArrayList<>();
        // vector是多线程安全的,但是并发行不好
        List<String> vector = new Vector();
        // Collections提供的工具类
        List<String> list2 = Collections.synchronizedList(new ArrayList<>());
        // 写时复制list ==》 这个是要说出来的关键
        // 看底层的源码就知道了，这个list的实现是读写分离的，读的时候不加锁，写的时候用ReentrantLock锁住
        // 比如在add(E)方法中，它是先将原来的数组拷贝一份，扩容添加一个元素，然后再写回去
        List<String> list3 = new CopyOnWriteArrayList<>();


        for(int i=0; i<3;i++){
            new Thread(() -> {
                list3.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list3);
            },String.valueOf(i)).start();
        }
    }


    public static void main(String[] args) {
        test();
    }
}
