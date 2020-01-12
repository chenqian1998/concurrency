package PC1;

public class Product_1 {
    private int count = 0;

    public synchronized void produce() {
        try {
            while (count != 0) {
                this.wait();
            }
            count++;
            System.out.println(Thread.currentThread().getName() + "生产，当前商品数：" + count);
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void consume() {
        try {
            while (count == 0) {
                this.wait();
            }
            count--;
            System.out.println(Thread.currentThread().getName() + "消费，当前商品数：" + count);
            this.notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
