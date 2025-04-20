package Adavanced;

import java.util.concurrent.Semaphore;

public class ReadWriterLoack {
    private static int readerCount=0;
    private static final Semaphore mutex = new Semaphore(1);
    private static final Semaphore wrt = new Semaphore(1);
    public static void lockRead() throws InterruptedException {
        mutex.acquire();
        readerCount++;
        if(readerCount==1)
        {
            wrt.acquire();
        }
        mutex.release();
    }
    public static void unlockRead() throws InterruptedException {
        mutex.acquire();
        readerCount--;
        if(readerCount==0)
        {
            wrt.release();
        }
        mutex.release();

    }
    public static void lockWrite() throws InterruptedException {
        wrt.acquire();
        mutex.acquire();
        wrt.release();

    }
    public static void unlockWrite() throws InterruptedException {
        wrt.acquire();
        mutex.release();
        wrt.release();
    }

    public static void main(String[] args) {
        Runnable readTask  = new Runnable() {
            @Override
            public void run() {
                try {
                    lockRead();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("started reading "+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("finished reading "+Thread.currentThread().getName());
                try {
                    unlockRead();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                try {
                    lockWrite();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("started writing "+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("finished writing "+Thread.currentThread().getName());
                try {
                    unlockWrite();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(readTask,"Reader-1");
        Thread t2 = new Thread(readTask,"Reader-2");
        Thread t3 = new Thread(writeTask,"Writer-1");
        t1.start();
        t2.start();
        t3.start();
    }
}
