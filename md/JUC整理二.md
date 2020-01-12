# JUC整理二：线程创建与线程实战
#菜鸡实习路
# 线程创建的几种方式
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
class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(“Callable 也是创建线程的一种方式”);
        return 1099;
    }
}
```
优点在于call()方法可以自己决定返回值，而原来Runnable接口中的run()方法是void()的。
比如我们可以指定返回数字或者字符串，描述这个线程是否成功运行了


# 企业级案例一
这个案例的核心是*多个线程，访问同一个资源类，如何保证同步*

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


# 企业级案例二：
面试的时候可能会让你写一个::生产者和消费者的问题::
这种问题的实质：::使用Java提供的API方法，进行线程之间的通信、交互::

Java提供了两种方式：
## （1）wait()，notifyAll(), synchronized
显然企业里不会用这种东西的：），但是面试官可能会问。
这个其实就是操作系统中讲的原子性操作。

一个生产者，一个消费之，商品队列大小为1.

```java
public class Product_1 {
    private int count = 0;
	
    public synchronized void produce() {
        try {
            while (count != 0) {
                this.wait();
            }
            count++;
            System.out.println(Thread.currentThread().getName() + "生产，当前商品数：" + count);
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void consume() {
        try {
            while (count == 0) {
                this.wait();
            }
            count—-;
            System.out.println(Thread.currentThread().getName() + “消费，当前商品数：” + count);
			//这个notifyAll()方法会唤醒前面两个可能都堵塞(wait())住的生产者
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }}
```
Notes:
(1)  注意在使用wait(), notify() 这种方式进行::线程交互::,也是需要使用synchronized加锁的
（2）有一个大坑就是在判断能否执行的时候，只能用while来判断，不能用if,这个主要是notify()这个方法会通知前面所有阻塞住的线程，此时如果用if,就不会对判断条件进行再次判断，相当于加了两次，所以此时产品数量可能超过1.


## 新的方法：Lock和Condition
Lock 与 Condition 可以完全替代wait()  noftify() 的功能。
特别的还是可以扩展原有的功能：::指定线程执行的顺序::。
```java
public class Product3 {
    private int count = 0;
	//创建一个锁（Lock),通过ReentrantLock()实例化,
	//Lock的作用有点像synchronized，对这段代码加锁
	// 
    private Lock lock = new ReentrantLock();
	// condition就是通知器
	// 之前堵塞是wait(),现在是 condition.await()
	// 之前通知是 notifyAll(), 现在是conditon.signalAll()
    private Condition condition = lock.newCondition();

    public void produce(){
        lock.lock();
        try {
            while(count!=0){
                condition.await();
            }
            count++;
            System.out.println(Thread.currentThread().getName()+"生产，当前产品数"+count);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume(){
        lock.lock();
        try {
            while (count == 0) {
                condition.await();
            }
            count--;
            System.out.println(Thread.currentThread().getName()+"消费，当前产品数"+count);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}
```

# 企业级案例三
在正常使用的时候，我们线程::交互是有顺序的::，使用最新的Lock和Condition进行上述操作。
```java
public class Order1 {
//首先需要一个lock锁作为同步机制实现，就像synchronized一样
    private Lock lock = new ReentrantLock();

	// 以往我们wait(),notifyAll()都是对this对象操作
	//这种机制有一个问题，就是不能指定下面是哪个方法才能执行

	// 下面就有三个互相通知的变量
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

	//用来判断当前线程需不需要阻塞
    private int flag = 1; // 1：下订单 2：库存操作 3：确认订单

    public void pushOrder() {
        lock.lock();
        try {
            //  只有flag是1，才进行操作，否则等待
            while (flag != 1) {
                condition1.await();
            }
            System.out.println("下订单！");
            flag = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void searchLab(){
        lock.lock();
        try{
            while(flag!=2){
                condition2.await();
            }
            System.out.println("库存操作...");
            flag =3 ;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }

    public void confirmOrder(){
        lock.lock();
        try{
            while(flag!=3){
                condition3.await();
            }
            System.out.println(“确认订单”);
            flag = 1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

```





