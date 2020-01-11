package chapter4;

import java.beans.IntrospectionException;

public class API_stopThread {
    //需求就是优雅的结束一个thread
    public static class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    // 做些事情
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    //如果捕获到这个打断的信息，就直接break
                    break;
                }
            }
            // =======做其他事情

        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        worker.interrupt();

    }
}
