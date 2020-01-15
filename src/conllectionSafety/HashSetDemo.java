package conllectionSafety;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class HashSetDemo {
    public static void main(String[] args) {
        // HashSet的底层是HashMap()实现的
        // 问题是 set.add(element),只要加一个参数，但是hashmap的add方法是要两个参数的，这是为啥？
        // 底层还是hashmap.add(e, 常数），只不过值是一个固定的常量
        Set<String> set = new HashSet<>();
        Set<String> set1 = Collections.synchronizedSet(new HashSet<>());
        Set<String> set2  = new CopyOnWriteArraySet<>();

        for(int i=0; i<100;i++){
            new Thread(() -> {
                set2.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set2);
            },String.valueOf(i)).start();
        }


    }
}
