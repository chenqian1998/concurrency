# JUC整理
#菜鸡实习路
这两周学习了一些Java多线程方面的知识，梳理一下自己学过的知识点
# 一：什么是多线程
更加细粒度的进程，比如打开office word，整个程序就是一个进程，其中有很多功能，比如说拼写错误提醒，实时保存文本这些功能都是独立的线程去实现的。

这几天我学的多线程都是基于Java.util.concurrent这个包下面的方法，使用这些提供的工具类来进行多线程操作。

# 二：多线程的用处
（1）提高程序的并发性，提高CPU的利用率。
比如我们知道IO操作是比较耗时的，如果不用多线程，就得一直等他运行结束才能进行下面的操作。
（2）为了负载均衡
把一个大的任务切分成多个小的任务去执行，可以提高效率

# 三：初识多线程
*企业级案例一：多线程操作同一个资源类*
买票案例，一共300张票。三个窗口买票，如何实现线程同步，比如说票数是一个一个地减少的。
``` java
public class Ticket {

    private int number = 300;

    public int getNumber(){
        return this.number;
    }

    public synchronized void saleTicket(){
        System.out.println(“当前是”+Thread.currentThread().getName()+”，卖出第：”+(number--)+”张,”+”剩余”+number+”张”);
    }
}
```
我们现在已经知道synchronized关键字对这个对象加了锁，所以同时只能有一个进程可以持有该对象。

如果不加会出现什么情况？如下
当前是saler2，卖出第：1张,剩余0张
当前是saler1，卖出第：2张,剩余1张
可知如果有两个进程竞争访问这个类中方法与变量，可能线程A中的方法还没执行完就被线程B抢过去执行了，所以我们其实要求*如果这个方法被线程执行，一下子就要都执行完*，就需要加锁处理，*我这个线程访问的时候，其他线程都要等着*

# 四：线程创建的几种方式
*1* 
Thread有多个构造函数，但是最常用的就是在构造函数中传入一个Runnable接口。
```java
new Thread(() -> {
    while(ticket.getNumber()>0){
        ticket.saleTicket();
    }
},”saler1”).start();
```
*2*
通过Callable接口的方式，这个是比较新的一种方式，看过代码就知道Callable方法比Runnable好在哪里。
```java
class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(“Callable 也是创建线程的一种方式”);
        return 1099;
    }
}
```
