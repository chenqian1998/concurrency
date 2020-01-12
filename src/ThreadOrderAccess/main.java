package ThreadOrderAccess;

public class main {
    public static void main(String[] args) {
        Order1 order = new Order1();

        new Thread(() -> {
            for(int i=0; i<10;i++){
                order.pushOrder();
            }
        },"pushOrder").start();

        new Thread(() -> {
            for(int i=0; i<10;i++){
                order.searchLab();
            }
        },"searchLab").start();

        new Thread(() -> {
            for(int i=0; i<10;i++){
                order.confirmOrder();
            }
        },"confirmOrder").start();




    }
}
