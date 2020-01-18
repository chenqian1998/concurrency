package Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{
    // 可见性保证：volatile
    private volatile Map<String, Object> map= new HashMap<>();
    //private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key , String value){
        rwLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在写入：" + key);
            map.put(key,value);
            try{ Thread.sleep(1000); }catch (Exception e){ e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           rwLock.writeLock().unlock();
        }

    }


    public void get(String key){
        rwLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t 正在读取：" + key);
            Object result = map.get(key);
            try{ Thread.sleep(1000); }catch (Exception e){ e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t 读取完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
               rwLock.readLock().unlock();
        }

    }

}


public class ReadWriteLockDemo {
    /*
    多个线程同时读一个资源类没有问题，但是为了满足并发性，我们对读取有了更高的要求。
    （1）读-读可以共存
    （2）写-写不可以共存
    （3）写-读也是不可以的（就是有些线程去写，那就不能共存）

    所谓的共存，就和前面说的加锁是一个意思。其实整个多线程很多关键词，
    synchronized, lock(ReentrantLock)他们实现的都是一个概念，
    这段代码如果要执行，那么只能由一个线程去执行，他执行完了，其他线程才能上，不然都给我等着
    这个其实就是所说的原子性！！！
    所以为什么volatile没有原子性，之前的代码验证，20个线程，每个线程做累加。最后小于2w,
    这显然是因为number++ 这个是线程不安全的，number++其实底层字节码可以分成三个指令，getfield,iadd,putfield
    线程1得到原来为1，然后自己加1，准备把1写回内存的时候，被打断了，然后线程二同样加1，然后线程1又执行写会主内存，
    现在主内存number是1，还没来得及通知线程二现在的值是1了，线程二又写回，所以number还是1。

    所以AtomicInteger就避免了number++出现的问题，你要加，就得给我把这个加的命令都执行完，其他线程才能再使用。

     */


    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        // 10个线程来写
        for(int i=0; i<10;i++){
            int temp = i;
            new Thread(() -> {
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }

        // 10个线程来读
        for(int i=0; i<10;i++){
            int temp = i;
            new Thread(() -> {
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }


    }
}
