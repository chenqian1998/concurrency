package chapter6;

public class ProducerComsumer {
    private int i = 0;

    private final Object LOCK = new Object();

    public void produce(){
        synchronized (LOCK){
            System.out.println("Produce ==>"+(i++));
        }
    }

    public void consume(){
        synchronized (LOCK){
            System.out.println("Comsume ==>"+i);
        }
    }

    public static void main(String[] args) {
        ProducerComsumer pc = new ProducerComsumer();

        Thread p = new Thread("p"){
            @Override
            public void run(){
                pc.produce();
            }
        };

        Thread c = new Thread("c"){
            @Override
            public void run(){
                pc.consume();
            }
        };

        p.start();
        c.start();

    }

}
