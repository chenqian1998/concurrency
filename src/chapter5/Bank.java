package chapter5;

public class Bank {
    public static void main(String[] args) {
        final ticketWindowRunnable t = new ticketWindowRunnable();
        // 这显然是不同的初始化方法
        Thread window1 = new Thread(t,"一号窗口");
        Thread window2 = new Thread(t,"二号窗口");
        Thread window3 = new Thread(t,"三号窗口");

        window1.start();
        window2.start();
        window3.start();



    }
}
