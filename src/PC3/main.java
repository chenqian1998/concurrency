package PC3;

public class main {
    public static void main(String[] args) {
        Product p = new Product();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                p.increment();
            }
        }, "Producer1").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                p.increment();
            }
        }, "Producer2").start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                p.decrement();
            }
        }, "Comsumer1").start();


        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                p.decrement();
            }
        }, "Comsumer2").start();
    }
}
