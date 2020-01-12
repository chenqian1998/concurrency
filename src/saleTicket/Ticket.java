package saleTicket;

public class Ticket {

    private int number = 10;

    public int getNumber(){
        return this.number;
    }

    public  void saleTicket(){
        System.out.println("当前是"+Thread.currentThread().getName()+"，卖出第："+(number--)+"张,"+"剩余"+number+"张");
    }

}
