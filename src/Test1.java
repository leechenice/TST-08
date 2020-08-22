import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {

    private static int count = 0;
    private static final int FULL = 10;
    private static String lock = "lock";

    static class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    while(count == FULL) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+"生产者生产，目前有" + ++count);
                    lock.notifyAll();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    while (count == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName()+"消费者消费，目前有" + --count);
                    lock.notifyAll();
                }
            }

        }


    }
    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10);
        service.execute(new Producer());
        service.execute(new Consumer());
        service.execute(new Producer());
        service.execute(new Consumer());
        service.execute(new Producer());
        service.execute(new Consumer());
        service.execute(new Producer());
        service.execute(new Consumer());
        service.execute(new Producer());
        service.execute(new Consumer());

    }
}
