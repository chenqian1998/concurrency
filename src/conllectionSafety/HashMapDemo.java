package conllectionSafety;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapDemo {
    public static void main(String[] args) {
       Map<String,String> map = new HashMap<>();
       Map<String,String> map2 = new ConcurrentHashMap<>();

       for(int i=0; i<10;i++){
           new Thread(() -> {
               map2.put(UUID.randomUUID().toString().substring(0,4),UUID.randomUUID().toString().substring(0,4));
               System.out.println(map2);
               },String.valueOf(i)).start();
       }
    }

    private static void HashMapTest() {
        Map<String,String> map = new HashMap<>();
        map.put("111","3");
        map.put("222","6");
        map.put("333","9");

        System.out.println(map);
        System.out.println(map.containsKey("111"));
        System.out.println(map.get("222"));
        System.out.println(map.size());
        System.out.println(map);
    }
}
