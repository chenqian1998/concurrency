package ThreadOrderAccess;

public class ThreadOrder {
    /*
    企业级多线程实战三：
    背景：往往在企业中，多个线程之间是有顺序的，比如先下订单，然后库存查询，确认订单，这一套顺序是确定好的。
         所以多线程之间也要确认好顺序。
         我们的要求：多线程之间按顺序调用：下订单=》库存查询=》确认订单
     */
    public static void main(String[] args) {
        Order order = new Order();
        new Thread(() -> {
            for(int i=0;i<10;i++){
                order.pushOrder();
            }
        },"name").start();

        new Thread(() -> {
            for(int i=0; i<10;i++){
                order.checkLab();
            }

        },"name").start();

        new Thread(() -> {
            for(int i=0; i<10;i++){
                order.confirmOrder();
            }
        },"name").start();
    }
}
