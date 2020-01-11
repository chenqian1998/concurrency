package callable;

import java.util.concurrent.Callable;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable 也是创建线程的一种方式");
        return 1099;
    }
}


public class CallableDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();

    }
}
