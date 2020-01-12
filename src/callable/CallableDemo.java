package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable 也是创建线程的一种方式");
        return 1099;
    }
}


public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        // 这个Callable调用的方法还是值得注意的
        // FutureTask实现的是RunnableFuture()接口的方法，RunnableFuture()又是Runnable的实现，
        // 所以Futuretask实际上就是Runnable的实现，
        // 所以在new Thread（Runnable ..）时候可以把这个传入，这就是Java多态的思想
        // 而FutureTask这个类的构造方法中可以传入一个Callable，所以
        FutureTask futureTask = new FutureTask(myThread);
        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());

    }
}
