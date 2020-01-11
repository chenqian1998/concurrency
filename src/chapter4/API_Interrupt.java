package chapter4;

public class API_Interrupt {
    public static Object monitor = new Object();
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run(){
                while(true){
                    synchronized (API_Interrupt.class){
                        try {
                            wait(10);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        // 这里创建了一个线程并且启动
        t.start();

        System.out.println(t.isInterrupted());
        //  打断这个线程
        // 上面这个thread方法run()中使用了一个class.wait(),该方法被下面这个打断，由于没有执行完，所以就爆出错误
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
