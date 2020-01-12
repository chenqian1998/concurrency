package conllectionSafety;


import java.util.*;

public class ArrayListDemo {
    public static  void test(){
        //为什么ArrayList会在多线程中出现问题，list.add()写入出错是关键
        //上一个线程在往list中写入数据的时候，另外一个线程抢占了，但是它还没有写完，所以会出现ConcurrentModificationException问题

        List<String> list = new ArrayList<>();
        List<String> list2 = Collections.synchronizedList(new ArrayList<>());


        for(int i=0; i<3;i++){
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }


    public static void main(String[] args) {
        test();
    }
}
