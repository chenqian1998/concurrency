package PC1;

public class PC1 {
    public static void main(String[] args) {
        Product_1 product = new Product_1();
        new Thread(() -> {
            for(int i=0; i<10;i++){
                product.produce();
            }
        },"producer1").start();

        new Thread(() -> {
            for(int i=0; i<10;i++){
                product.produce();
            }
        },"producer2").start();

        new Thread(() -> {
            for(int i=0; i<10;i++){
                product.consume();
            }
        },"consumer1").start();

        new Thread(() -> {
            for(int i=0; i<10;i++){
                product.consume();
            }
        },"consumer2").start();

    }
}
