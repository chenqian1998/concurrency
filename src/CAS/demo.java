package CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class demo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        // 就是在改回主内存的时候，先比较，是不是期望的值，
        // 是的话就可以修改，如果不是期望的值，就修改失败
        System.out.println(atomicInteger.compareAndSet(5, 2019)+",current data:"+atomicInteger.get());

        // 这一步修改失败，最新的值还是2019
        System.out.println(atomicInteger.compareAndSet(111, 123)+",current data:"+atomicInteger.get());


        // 了解API调用CAS，那么CAS的底层原理是什么？？？
        // 所谓的CAS就是Compare-And-Swap,它是一条CPU并发原语，它是系统判断内存每个位置的值是否是预期的值，如果是就更改，否则不改

        // 所以说

        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.get());

    }
}
