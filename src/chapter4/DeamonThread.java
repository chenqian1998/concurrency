package chapter4;

public class DeamonThread {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName()+"running");
                try {
                    Thread.sleep(10*1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"done");
            }
        };
        //设置thread和主线程main的生命周期相同，主线程退出以后，
        thread.setDaemon(true);
        thread.start();



        try{
            Thread.sleep(2*1000L);
            System.out.println(Thread.currentThread());
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        //上面你就会发现一个问题，main线程结束了之后（main会在thread之前停止)
        // thread还在等，这样其实不是很好，我们有一个期望：主线程结束之后，子线程希望也一并结束掉
    }
}
