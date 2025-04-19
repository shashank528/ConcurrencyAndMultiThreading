package Adavanced;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;

public class BarierSynchronizedDemo {

    static class Barier {
         final  Semaphore mutex= new Semaphore(1);
        final Semaphore bariers = new Semaphore(0);
        int parties;
        int count;
        public Barier(int parties,int count)
        {
            this.parties=parties;
            this.count=count;
        }
        public void await() throws InterruptedException {
            mutex.acquire();
            count--;
            if(count==0)
            {
                bariers.release(parties-1);
                count=parties;
                mutex.release();
            }
            else
            {
                mutex.release();
                bariers.acquire();
            }
        }

    }
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5, new ThreadFactory() {
            int counter=1;
            @Override
            public Thread newThread(Runnable r) {
                Thread t= new Thread(r,"Thread-"+counter);
                counter++;
                return t;
            }
        });
        Barier barier = new Barier(5,5);
        for(int i=0;i<5;i++)
        {
            executor.submit(()->{
                System.out.println("executing phase1 "+Thread.currentThread().getName());
                System.out.println("caling await for first time "+Thread.currentThread().getName());
                try {
                    barier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("executing phase2 "+Thread.currentThread().getName());
                System.out.println("caling await for second  time "+Thread.currentThread().getName());
                try {
                    barier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("executing phase3 "+Thread.currentThread().getName());
                System.out.println("caling await for third  time "+Thread.currentThread().getName());
            });
        }

    }
}
