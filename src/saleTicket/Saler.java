package saleTicket;

public class Saler {
    /*
    经典企业级例子一：
    这个例子是用来说明：多个线程操纵资源类，如何做到同步。
    就像买票一样，不同的售卖者对票的处理应该是一致性的。
     */
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 这是通过传入一个runnable接口
        // runnable 通过内部类实现
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(ticket.getNumber()>0) {
                    ticket.saleTicket();
                }
            }
        },"saler2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(ticket.getNumber()>0) {
                    ticket.saleTicket();
                }
            }
        },"saler3").start();

        // java8中的新特性，借鉴了scala中的思想：
        // 一个类（比如Runnable接口)中如果只有一个方法，那么这其实就是一个函数式接口，就直接可以用lambda表达式更加简单使用
        new Thread(() ->{
            while (ticket.getNumber()>0){
                ticket.saleTicket();
            }
        },"saler3").start();

    }
}
