package saleTicket;

public class Saler {
    /*
    经典企业级例子一：
    这个例子是用来说明：多个线程操纵资源类，如何做到同步。
    就像买票一样，不同的售卖者对票的处理应该是一致性的。
     */
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 这是通过传入一个runnable
        // runnable 通过内部类实现
        new Thread(() -> {
            while(ticket.getNumber()>0){
                ticket.saleTicket();
            }
        },"saler1").start();

        new Thread(() -> {
            while(ticket.getNumber()>0){
                ticket.saleTicket();
            }
        },"saler2").start();


    }
}
