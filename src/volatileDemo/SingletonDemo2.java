package volatileDemo;

public class SingletonDemo2 {
    // 企业级怎么构造
    // volatile 不允许指令重排==》这个还是很有说法的，也不知道到时候会不会问到
    // 主要就是 new SingletonDemo()这个Java字符码：
    //memory=allocate();//1.分配对象内存空间
    //instance(memory);//2.初始化对象
    //instance=memory;//3.设置instance的指向刚分配的内存地址,此时instance!=null 
    private static volatile SingletonDemo2  instance = null;

    private SingletonDemo2(){
        System.out.println("init...");
    }

    // 双端检测机制（double check lock)，先检测一次这个没有实例化
    // 然后这个肯定是要加锁的，区别是这个不是给整个方法加锁，而是部分代码段加锁，这个粒度更小，更好
    public static SingletonDemo2 getInstance(){
        if(instance == null){
            synchronized (SingletonDemo2.class){
                if(instance == null){
                    instance = new SingletonDemo2();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for(int i=0; i<10;i++){
            new Thread(() -> {
                SingletonDemo2.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
