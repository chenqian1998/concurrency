package chapter4;

public class ThreadSimpleAPI {
    public static void main(String[] args) {
        Thread thread = new Thread("t1"){
            @Override
            public void run(){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100_0000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        System.out.println(thread.getId());
        System.out.println(thread.getPriority());
        thread.setPriority(Thread.MAX_PRIORITY);

    }
}
