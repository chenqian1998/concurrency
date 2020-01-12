package PC3;

public class PC3 {
    public static void main(String[] args) {
        Product3 product = new Product3();

        new Thread(() -> {
            for(int i=0; i<10;i++){
                product.produce();
            }
        },"Producer1").start();
        new Thread(() -> {
            for(int i=0; i<10;i++){
                product.produce();
            }
        },"Producer2").start();

        new Thread(() -> {
            for(int i=0; i<10;i++){
                product.consume();
            }
        },"Consumer1").start();

        new Thread(() -> {
            for(int i=0; i<10;i++){
                product.consume();
            }
        },"Consumer2").start();
    }
}
