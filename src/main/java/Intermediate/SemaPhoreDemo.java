package Intermediate;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaPhoreDemo {
    public static Semaphore semaphore = new Semaphore(1);
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r,"Shashank"+"-"+atomicInteger.getAndIncrement());
                return t;
            }
        });
        for(int i=0;i<5;i++)
        {
            executorService.submit(()->{
                function();
            });
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                System.err.println("Timeout occurred while waiting for threads to finish.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
    public static void function()
    {

            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100); // Introduce a 100 millisecond delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                semaphore.release();
            }


    }
}
