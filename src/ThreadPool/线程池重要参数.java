package ThreadPool;

import java.util.concurrent.*;

public class 线程池重要参数 {
    /*
        随着业务增长，线程池是怎么变化的？
        （1）corePoolSize常驻线程代表一开始就提供这么多线程执行任务
        （2）后面再来的任务被放在阻塞队列（BlockingQueue）中，等前面的任务执行完之后才能运行
        （3）如果又来了任务，但是这时候阻塞队列都已经满了，那此时会去扩增池子里的线程数，达到maxinumPoolSize,
            此时阻塞队列的任务会先进去
        （4）线程数扩到最大，而且阻塞队列也满了的情况下，再进来的线程就需要进行某种拒绝策略RejectedExecutionHandler（比如多长时间之后再次提交）
        （5）等到业务高峰过去了，如果一定时间（keepAliveTime）内都没有任务进来，线程数又会从达到maxinumPoolSize回到corePoolSize。
     */
    public static void main(String[] args) {
        //得到内核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        try{
            for(int i=0; i<10;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+":执行任务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
               threadPool.shutdown();
        }

    }
}
